package com.Dhiraj.eventManagemetApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Dhiraj.eventManagemetApp.entity.Event;
import com.Dhiraj.eventManagemetApp.entity.EventDTO;
import com.Dhiraj.eventManagemetApp.entity.EventParticipants;
import com.Dhiraj.eventManagemetApp.entity.User;
import com.Dhiraj.eventManagemetApp.repository.EventParticipantsRepository;
import com.Dhiraj.eventManagemetApp.repository.EventRepository;
import com.Dhiraj.eventManagemetApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service  // Ensure this annotation is present
@RequiredArgsConstructor
public class EventService {
	@Autowired
    private EventRepository eventRepository;
   
	 @Autowired
	 private UserRepository userRepository;
	 @Autowired
	private EventParticipantsRepository eventParticipantsRepository;
	   
	 public void createEvent(EventDTO eventDTO, User user) {
	
		 Event event = new Event();
		 event.setName(eventDTO.getName());
		 event.setDescription(eventDTO.getDescription());
		 event.setLocation(eventDTO.getLocation());
		 event.setEventDate(eventDTO.getEventDate());
		 event.setCreatedAt(LocalDateTime.now());
		 event.setUpdatedAt(LocalDateTime.now());
		 event.setCreatedBy(user);
		 event.setParticipants(new HashSet<>());
		 event.setMaxParticipants(eventDTO.getMaxParticipants());
		 event.setCity(eventDTO.getCity());
		 event.setCountry(eventDTO.getCountry());
		 event.setImageName(eventDTO.getImageName());
		 
		 eventRepository.save(event);
		 
		   EventParticipants ep = new EventParticipants();
		    ep.setEvent(event);
		    ep.setUser(user);
		    ep.setJoinedDate(LocalDateTime.now());
		    ep.setStatus("CONFIRMED");
		    ep.setEventActive(true);
		    ep.setDateModified(LocalDateTime.now());
		    ep.setNowJoined(true);

		    // Save the event participant entry
		    eventParticipantsRepository.save(ep);

	    }

    public List<Event> getAllUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public boolean joinEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return false;
        
        event.getParticipants().add(user);
        eventRepository.save(event);
        return true;
    }
    
    public List<EventDTO> getAllEvents(User loggeduser) {
        return eventRepository.findAll().stream()
        		.filter(event -> !event.isExpired())
        		.filter(event -> !event.getCreatedBy().equals(loggeduser)) // 
                .map(event -> new EventDTO(event.getId(), event.getName(), event.getDescription(), event.getLocation(), event.getEventDate(),event.getCreatedBy().getId(),event.isExpired(),event.getImageName()))
                .collect(Collectors.toList());
    }

	public Event getEventById(Long id) {
		 return eventRepository.findById(id).orElse(null);
	}

	public void saveEvent(Event event) {
		 eventRepository.save(event);
		
	}

	public void joinEvent(Long eventId, Long userId) {
		 System.out.println("========================Inside the Join Event===========================");
	    Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
	    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    
	    //event.getParticipants().add(user);
	    eventRepository.save(event);
	    
	   
	    System.out.println("========================Saved Event===========================");

		   EventParticipants ep = new EventParticipants();
		   System.out.println("========================Created Participate Event===========================");
		    ep.setEvent(event);
		    ep.setUser(user);
		    ep.setJoinedDate(LocalDateTime.now());
		    ep.setStatus("CONFIRMED");
		    ep.setEventActive(true);
		    ep.setDateModified(LocalDateTime.now());
		    ep.setNowJoined(true);

		    // Save the event participant entry
		    eventParticipantsRepository.save(ep);
	}

	public void leaveEvent(Long eventId, Long userId) {
	    Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
	    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	    event.getParticipants().remove(user);
	    eventRepository.save(event);
	}

	public boolean isUserJoined(Long eventId, Long userId) {
	    Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
	    return event.getParticipants().stream().anyMatch(user -> user.getId().equals(userId));
	}

	public void deleteEvent(Long eventId) {
		 eventRepository.deleteById(eventId);
	}
	
	 public List<Event> getEventsByUser(Long userId) {
	        return eventRepository.findEventsByUser(userId);
	    }


	 public List<Event> findEventJoinedByUser(Long userId) {
		 System.out.println(" findEventJoinedByUser Fetcing data for user :-" + userId);
	        return eventRepository.findEventJoinedByUser(userId);
	    }
}
