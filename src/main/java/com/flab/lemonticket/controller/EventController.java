package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.*;
import com.flab.lemonticket.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEvent(
            @RequestPart("event") Event event,
            @RequestPart("eventTimes") List eventTimes,
            @RequestPart("seatLayouts") List seatLayouts,
            @RequestPart("performerEvents") List performerEvents,
            @RequestPart("eventImgs") List eventImgs,
            @RequestPart(value = "profileImgFile", required = false) MultipartFile profileImgFile) {

        Map<String, Object> response = eventService.addEvent(event, eventTimes, seatLayouts, performerEvents, eventImgs, profileImgFile);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @PutMapping("/modify")
    public ResponseEntity<Map<String, Object>> modifyEvent(
            @RequestPart("event") Event event,
            @RequestPart("eventTimes") List<EventTime> eventTimes,
            @RequestPart("seatLayouts") List<SeatLayout> seatLayouts,
            @RequestPart("performerEvents") List<PerformerEvent> performerEvents,
            @RequestPart("eventImgs") List<MultipartFile> eventImgs,
            @RequestPart(value = "profileImgFile", required = false) MultipartFile profileImgFile) {

        Map<String, Object> response = eventService.modifyEvent(event, eventTimes, seatLayouts, performerEvents, eventImgs, profileImgFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Event>> searchEvents(
            @RequestParam(required = false) String performerName,
            @RequestParam(required = false) String title,
            Pageable pageable) {
        Page<Event> events = eventService.searchEvents(performerName, title, pageable);
        return ResponseEntity.ok(events);
    }
}
