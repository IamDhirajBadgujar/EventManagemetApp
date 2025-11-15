package com.Dhiraj.eventManagemetApp.repository;

import com.Dhiraj.eventManagemetApp.entity.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<EventLocation, Long> {
}
