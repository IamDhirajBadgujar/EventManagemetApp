package com.Dhiraj.eventManagemetApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Dhiraj.eventManagemetApp.entity.EventLocation;
import com.Dhiraj.eventManagemetApp.repository.LocationRepository;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    @ResponseBody
    public List<EventLocation> getLocations() {
        return locationRepository.findAll();
    }
}
