package com.Dhiraj.eventManagemetApp.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dhiraj.eventManagemetApp.entity.Event;
import com.Dhiraj.eventManagemetApp.repository.EventRepository;

@Service
public class EventSchedulerService {

    private final EventRepository eventRepository;

    public EventSchedulerService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Runs every hour (cron: second, minute, hour, day, month, weekday)
    @Scheduled(cron = "0 * * * * *") // ⏰ Every hour
    @Transactional
    public void updateExpiredEvents() {
        List<Event> events = eventRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
       // boolean shouldBeExpired = event.getEventDate().isBefore(now);


        for (Event event : events) {
            boolean shouldBeExpired = event.getEventDate().isBefore(now);
            if (event.isExpired() != shouldBeExpired) {
                event.setExpired(shouldBeExpired);
                eventRepository.save(event); // Save only if status changed
            }
        }
    }
}
