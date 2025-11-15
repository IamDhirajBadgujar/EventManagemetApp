package com.Dhiraj.eventManagemetApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dhiraj.eventManagemetApp.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    // Fix: Define a custom query for finding upcoming events
    @Query("SELECT e FROM Event e WHERE e.eventDate >= :now ORDER BY e.eventDate ASC")
    List<Event> findUpcomingEvents(LocalDateTime now);
    
    @Query("SELECT e FROM Event e WHERE e.createdBy.id= :userId")
    List<Event> findEventsByUser(@Param("userId") Long userId);
    
    @Query("SELECT e FROM Event e WHERE e.id IN (SELECT ep.event.id FROM EventParticipants ep WHERE ep.user.id = :userId)")
    List<Event> findEventJoinedByUser(@Param("userId") Long userId);

    
    
}
