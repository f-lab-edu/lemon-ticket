package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.Venue;
import com.flab.lemonticket.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping("/add")
    public Venue addVenue(@RequestBody Venue venue) {
        return venueService.addVenue(venue);
    }
}
