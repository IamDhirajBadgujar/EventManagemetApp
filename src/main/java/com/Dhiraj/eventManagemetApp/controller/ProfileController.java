package com.Dhiraj.eventManagemetApp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Dhiraj.eventManagemetApp.entity.Event;
import com.Dhiraj.eventManagemetApp.entity.EventDTO;
import com.Dhiraj.eventManagemetApp.entity.User;
import com.Dhiraj.eventManagemetApp.entity.UserDTO;
import com.Dhiraj.eventManagemetApp.services.EventService;
import com.Dhiraj.eventManagemetApp.services.UserService;

@Controller
public class ProfileController {
	    @Autowired
	    private  EventService eventService;
		 @Autowired
	    private  UserService UserService;
		 
		 
		   
		    @GetMapping("/my-events")
		    public String myEvents(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		    	User loogeduser=UserService.findUserbyUsername(userDetails.getUsername());
		       List<Event> myCreatedEvents=eventService.getEventsByUser(loogeduser.getId());
		       System.out.println("Inside the my-evtn Mapping");
		       model.addAttribute("myCreatedEvents", myCreatedEvents);
		       
		       System.out.println("Loop Started");
		       for(Event e : myCreatedEvents) {
		       System.out.println(e);
		       }
		       System.out.println("Loop Completed");
		       
		       model.addAttribute("userDto", new UserDTO());
		        model.addAttribute("Joined", false);
		        model.addAttribute("eventDTO", new EventDTO());

		       
		       
		        return "my-events";
		    }
		    
		    @GetMapping("/joined-events")
		    public String joinedEvents(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		    	User loogeduser=UserService.findUserbyUsername(userDetails.getUsername());
		    	System.out.println("Inside the my-evtn Mapping:-"+loogeduser.getUsername()+loogeduser.getId() );
		       List<Event> joinedEvents=eventService.findEventJoinedByUser
		    		   (loogeduser.getId());
		       
		       model.addAttribute("joinedEvents", joinedEvents);
		       model.addAttribute("loogeduser", loogeduser);
		       System.out.println("Loop Started");
		       for(Event e : joinedEvents) {
		       System.out.println(e);
		       }
		       System.out.println("Loop Completed");
		       
		       model.addAttribute("userDto", new UserDTO());
		        model.addAttribute("Joined", false);
		        model.addAttribute("eventDTO", new EventDTO());

		       
		       
		        return "Joined-event";
		    }

		 
		 @GetMapping("/user/{userId}")
		    public ResponseEntity<List<Event>> getUserEvents(@PathVariable Long userId) {
		        List<Event> events = eventService.getEventsByUser(userId);
		        return ResponseEntity.ok(events);
		    }
		 
		 
		 @GetMapping("/profile")
		 public String getUserProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
			 User loogeduser=UserService.findUserbyUsername(userDetails.getUsername());
			 model.addAttribute("user", loogeduser);
			 model.addAttribute("canEdit", true);
			 return"profile";
		 }
		 


}
