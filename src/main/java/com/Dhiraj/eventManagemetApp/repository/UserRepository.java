package com.Dhiraj.eventManagemetApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dhiraj.eventManagemetApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
