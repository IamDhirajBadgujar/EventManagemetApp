package com.Dhiraj.eventManagemetApp.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.Dhiraj.eventManagemetApp.entity.EventDTO;
import com.Dhiraj.eventManagemetApp.entity.User;
import com.Dhiraj.eventManagemetApp.entity.UserDTO;
import com.Dhiraj.eventManagemetApp.repository.UserRepository;
import com.Dhiraj.eventManagemetApp.services.EventService;
import com.Dhiraj.eventManagemetApp.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("userDto")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    private EventService eventService; // Assuming you have a service layer
    @Autowired
    private UserService UserService;
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) String sortBy,
                            @RequestParam(required = false) String location,
                            Model model,
                            @AuthenticationPrincipal UserDetails userDetails) {

        model.addAttribute("userDto", new UserDTO());
        model.addAttribute("Joined", false);
        model.addAttribute("eventDTO", new EventDTO());

        if (userDetails != null) {
            User loggedInUser = UserService.findUserbyUsername(userDetails.getUsername());
            model.addAttribute("loggedInUserId", loggedInUser.getId());

            // Get all events created by user
            List<EventDTO> events = eventService.getAllEvents(loggedInUser);

            // Filter by location
            if (location != null && !location.equalsIgnoreCase("All") && !location.isBlank()) {
                events = events.stream()
                        .filter(e -> location.equalsIgnoreCase(e.getLocation()))
                        .collect(Collectors.toList());
            }
            model.addAttribute("selectedLocation", location);

            // Sort by name or date
            if (sortBy != null) {
                switch (sortBy) {
                    case "name_asc" -> events.sort(Comparator.comparing(EventDTO::getName));
                    case "name_desc" -> events.sort(Comparator.comparing(EventDTO::getName).reversed());
                    case "date_asc" -> events.sort(Comparator.comparing(EventDTO::getEventDate));
                    case "date_desc" -> events.sort(Comparator.comparing(EventDTO::getEventDate).reversed());
                }
            }
            
            // Extract distinct locations for the dropdown
            Set<String> locations = eventService.getAllEvents(loggedInUser).stream()
                    .map(EventDTO::getLocation)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(TreeSet::new));

            model.addAttribute("events", events);
            model.addAttribute("locations", locations); // for dropdown
            model.addAttribute("success", false);
        } else {
            model.addAttribute("loggedInUserId", null);
        }

        return "dashboard";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDTO());
        model.addAttribute("success", false); // Fixed attribute name
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("userDto") UserDTO userDto, BindingResult result,  @RequestParam Map<String, String> allParams) {
        System.out.println("Form Parameters: " + allParams); // Debugging
        System.out.println("DTO Object: " + userDto); 
        // Validate password confirmation
        if (!userDto.getPassword().equals(userDto.getConfirmpassword())) {
            result.addError(new FieldError("userDto", "confirmpassword", "Password and Confirm Password must match!"));
            logger.warn("Password and Confirm Password do not match.");
        }

        // Validate email uniqueness
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            result.addError(new FieldError("userDto", "email", "Email already used. Try logging in or use a different email!"));
            logger.warn("User with email {} already exists.", userDto.getEmail());
        }

        // If validation errors exist, return to registration form
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto); 
            logger.error("Registration failed due to validation errors: {}", result.getFieldErrors());
            return "register";
        }

        try {
            // Create new user
            User newUser = new User();
            newUser.setUsername(userDto.getUsername());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); 
            newUser.setEmail(userDto.getEmail());
            newUser.setLocation(userDto.getLocation());
            newUser.setPhone(userDto.getPhone());
            newUser.setDateCreated(new Date()); // Fixed method name
            newUser.setRole("USER");
            newUser.setDob(userDto.getDob());

            userRepository.save(newUser);
            logger.info("User registered successfully: {}", newUser);

            // Reset form after successful registration
            model.addAttribute("userDto", new UserDTO());
            model.addAttribute("success", true);

        } catch (Exception e) {
            logger.error("Error occurred during user registration: {}", e.getMessage(), e);
            result.addError(new FieldError("userDto", "username", "An error occurred. Please try again!"));
            model.addAttribute("userDto", userDto);
            return "register";
        }

        return "register";
    }
}
