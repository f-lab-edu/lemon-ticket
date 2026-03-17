package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.Venue;
import com.flab.lemonticket.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    @Transactional
    public Venue addVenue(Venue venue){
        return venueRepository.save(venue);
    }
}
