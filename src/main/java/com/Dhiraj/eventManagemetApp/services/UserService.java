package com.Dhiraj.eventManagemetApp.services;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Dhiraj.eventManagemetApp.entity.User;
import com.Dhiraj.eventManagemetApp.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(identifier); // Try email if username is not found
            if (user==null) {
            	System.out.println("User is null");
            	return null;
            }

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
    
    
    
    public UserDetails loadUserByUserEmail (String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(identifier); // Try email if username is not found
            if (user==null) {
            	System.out.println("User is null");
            	return null;
            }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
    
    
    public User findUserbyUsername(String username) {
    	System.out.println("Inside the UserService : findUserbyUsername ");
    	User user = userRepository.findByUsername(username); 
    	return user;
    }


}
