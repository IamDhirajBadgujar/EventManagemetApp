package com.Dhiraj.eventManagemetApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dhiraj.eventManagemetApp.entity.Event;
import com.Dhiraj.eventManagemetApp.entity.EventParticipants;


@Repository
public interface EventParticipantsRepository extends JpaRepository<EventParticipants, Long> {
	
}
