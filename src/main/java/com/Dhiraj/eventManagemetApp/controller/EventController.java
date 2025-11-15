package com.Dhiraj.eventManagemetApp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Dhiraj.eventManagemetApp.entity.Event;
import com.Dhiraj.eventManagemetApp.entity.EventDTO;
import com.Dhiraj.eventManagemetApp.entity.User;
import com.Dhiraj.eventManagemetApp.entity.UserDTO;
import com.Dhiraj.eventManagemetApp.services.EventService;
import com.Dhiraj.eventManagemetApp.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller  // Changed from @RestController to @Controller for Thymeleaf
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
	  @Autowired
    private  EventService eventService;
	  @Autowired
    private  UserService userService;

    @PostMapping("/create")
    public String createEvent(@ModelAttribute EventDTO eventDTO, @AuthenticationPrincipal UserDetails userDetails) {
    	String username=userDetails.getUsername();
        User user = userService.findUserbyUsername(username);
        System.out.println("I side the CreateEvent Post Controller");
        System.out.println(user+"----"+eventDTO+"---"+userDetails);
        eventService.createEvent(eventDTO,user);
        return "redirect:/dashboard"; // Redirect to events page after creation
    }
    
    @GetMapping("/event")
    public String createEventpage(@ModelAttribute EventDTO eventDTO, @AuthenticationPrincipal UserDetails userDetails) {
    	String username=userDetails.getUsername();
        User user = userService.findUserbyUsername(username);
        System.out.println("In side the CreateEvent get Controller");
        System.out.println(user+"----"+eventDTO+"---"+userDetails);
       // eventService.createEvent(eventDTO,user);
        return "event"; // Redirect to events page after creation
    }


    @GetMapping("/view") // Changed mapping to avoid conflict
    public String showEventsPage(Model model , @AuthenticationPrincipal UserDetails userDetails) {
       //List<Event> events = eventService.getAllUpcomingEvents();
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        //model.addAttribute("eventDTO", events);
        model.addAttribute("eventDTO", new EventDTO()); // Empty event object for form
        model.addAttribute("eventDTO", new EventDTO());
        return "event"; // Make sure Thymeleaf template is named "event.html"
    }
    
    @PostMapping("/update")
    public String updateEvent(@ModelAttribute Event event) {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        Event existingEvent = eventService.getEventById(event.getId());
        System.out.println("User Logged in : "+ loggedInUsername + "Event Owner : "+existingEvent.getCreatedBy().getUsername());
        
       
        System.out.println(existingEvent);
        if (existingEvent != null && existingEvent.getCreatedBy().getUsername().equals(loggedInUsername)) {
            // Update event details
        	System.out.println("Updateing Event -------------");
            existingEvent.setName(event.getName());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setEventDate(event.getEventDate());
            existingEvent.setUpdatedAt(LocalDateTime.now());

            // Save updated event
            eventService.saveEvent(existingEvent);
        }

        return "redirect:/dashboard"; // Redirect to dashboard after update
    }
    
    
    @GetMapping("/{eventId}")
    public String viewEventDetails(@PathVariable Long eventId, Model model, Principal principal) {
        Event event = eventService.getEventById(eventId);
        System.out.println("Inside the event view Controller : created by :--> "+event.getCreatedBy().getId());
        // Get logged-in user
        String loggedInUsername = principal.getName();
        System.out.println("User name :-" +loggedInUsername);
        User loggedInUser = userService.findUserbyUsername(loggedInUsername);
        System.out.println(loggedInUser);
        // Check if the user has already joined the event
        boolean isJoined = eventService.isUserJoined(eventId, loggedInUser.getId());

        model.addAttribute("event", event);
        model.addAttribute("isJoined", isJoined);
        model.addAttribute("loggedInUserId", loggedInUser.getId());
        model.addAttribute("createdById", event.getCreatedBy().getId());
        model.addAttribute("isExpired", event.isExpired());
        
        model.addAttribute("userDto", new UserDTO());
        model.addAttribute("Joined", false);
        model.addAttribute("eventDTO", new EventDTO());
        
        return "event-details"; // Renders event-details.html
    }

    
    
    @PostMapping("/join")
    public String joinEvent(@RequestParam Long eventId ,Principal principal) {
    	
    	System.out.println("Inside Join Event controller ");
        String username = principal.getName();
        System.out.println("Inside Join Event controller :- USER :-" + username);
        User user = userService.findUserbyUsername(username);
        System.out.println("Inside Join Event controller :- USER :-" + user);
        eventService.joinEvent(eventId, user.getId());
        
        return "redirect:/events/" + eventId;
    }

    @PostMapping("/leave")
    public String leaveEvent(@RequestParam Long eventId, Principal principal) {
        String username = principal.getName();
        User user = userService.findUserbyUsername(username);

        eventService.leaveEvent(eventId, user.getId());

        return "redirect:/events/" + eventId;
    }
    
    @DeleteMapping("/{eventId}")
    public String createEvent(@PathVariable Long eventId , Principal principal) {
    	Event event = eventService.getEventById(eventId);
        System.out.println("Inside the delete event Controller : created by :--> "+event.getCreatedBy().getId());
        // Get logged-in user
        String loggedInUsername = principal.getName();
        System.out.println("User name :-" +loggedInUsername);
        User loggedInUser = userService.findUserbyUsername(loggedInUsername);
        System.out.println(loggedInUser);
        
        if (!event.getCreatedBy().getId().equals(loggedInUser.getId())) {
            return "redirect:/dashboard?error=unauthorized"; // Prevent unauthorized deletion
        }
        System.out.println(event);
        eventService.deleteEvent(eventId); // Delete event
      // Redirect after deletion
        
        return "redirect:/dashboard"; // Redirect to events page after creation
    }



}
