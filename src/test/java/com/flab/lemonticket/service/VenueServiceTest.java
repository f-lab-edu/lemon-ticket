package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.Venue;
import com.flab.lemonticket.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueService venueService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("addVenue 메소드가 공연장 정보를 성공적으로 추가하는지 테스트")
    void testAddVenue() throws IOException{
        Venue venue = new Venue();
        venue.setVenueId(1L);
        venue.setVenueInfo("Test Venue Info");
        venue.setAddress("Seoul...");
        venue.setWebsite("http://venue.xyz");
        venue.setLocationType("SEOUL");
        venue.setTelNumber("82-02-1234-1234");
        venue.setName("공연장 1");

        when(venueRepository.save(any(Venue.class))).thenReturn(venue);

        Venue savedVenue = venueService.addVenue(venue);

        assertNotNull(savedVenue);
        assertEquals(venue.getVenueId(), savedVenue.getVenueId());
        assertEquals(venue.getName(), savedVenue.getName());
        assertEquals(venue.getLocationType(), savedVenue.getLocationType());
        verify(venueRepository, times(1)).save(any(Venue.class));
    }
}
