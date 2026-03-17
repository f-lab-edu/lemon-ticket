package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.*;
import com.flab.lemonticket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventImgRepository eventImgRepository;

    @Autowired
    private EventTimeRepository eventTimeRepository;

    @Autowired
    private SeatLayoutRepository seatLayoutRepository;

    @Autowired
    private PerformerEventRepository performerEventRepository;

    @Autowired
    private FileUploadService fileUploadService;


    @Transactional
    public Map<String, Object> addEvent(Event event, List<EventTime> eventTimes, List<SeatLayout> seatLayouts, List<PerformerEvent> performerEvents, List<MultipartFile> eventImgs, MultipartFile profileImgFile) {
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());

        if (profileImgFile != null && !profileImgFile.isEmpty()) {
            try {
                String profileImgPath = fileUploadService.saveFile(profileImgFile, event.getEventId());
                event.setProfileImg(profileImgPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Event savedEvent = eventRepository.save(event);

        eventTimes.forEach(eventTime -> {
            eventTime.setEventId(savedEvent.getEventId());
            eventTimeRepository.save(eventTime);
        });

        seatLayouts.forEach(seatLayout -> {
            seatLayout.setEventId(savedEvent.getEventId());
            seatLayoutRepository.save(seatLayout);
        });

        performerEvents.forEach(performerEvent -> {
            performerEvent.setEventId(savedEvent.getEventId());
            performerEventRepository.save(performerEvent);
        });

        eventImgs.forEach(file -> {
            try {
                String filePath = fileUploadService.saveFile(file, savedEvent.getEventId());
                EventImg eventImg = new EventImg();
                eventImg.setEventId(savedEvent.getEventId());
                eventImg.setImgPath(filePath);
                eventImgRepository.save(eventImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Map<String, Object> response = new HashMap<>();
        response.put("event", savedEvent);
        return response;
    }

    @Transactional
    public Map<String, Object> modifyEvent(Event event, List<EventTime> eventTimes, List<SeatLayout> seatLayouts, List<PerformerEvent> performerEvents, List<MultipartFile> eventImgs, MultipartFile profileImgFile) {
        event.setUpdatedAt(LocalDateTime.now());

        if (profileImgFile != null && !profileImgFile.isEmpty()) {
            try {
                String profileImgPath = fileUploadService.saveFile(profileImgFile, event.getEventId());
                event.setProfileImg(profileImgPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Event updatedEvent = eventRepository.save(event);

        eventTimeRepository.deleteByEventId(updatedEvent.getEventId());
        eventTimes.forEach(eventTime -> {
            eventTime.setEventId(updatedEvent.getEventId());
            eventTimeRepository.save(eventTime);
        });

        seatLayoutRepository.deleteByEventId(updatedEvent.getEventId());
        seatLayouts.forEach(seatLayout -> {
            seatLayout.setEventId(updatedEvent.getEventId());
            seatLayoutRepository.save(seatLayout);
        });

        performerEventRepository.deleteByEventId(updatedEvent.getEventId());
        performerEvents.forEach(performerEvent -> {
            performerEvent.setEventId(updatedEvent.getEventId());
            performerEventRepository.save(performerEvent);
        });

        eventImgRepository.deleteByEventId(updatedEvent.getEventId());
        eventImgs.forEach(file -> {
            try {
                String filePath = fileUploadService.saveFile(file, updatedEvent.getEventId());
                EventImg eventImg = new EventImg();
                eventImg.setEventId(updatedEvent.getEventId());
                eventImg.setImgPath(filePath);
                eventImgRepository.save(eventImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Map<String, Object> response = new HashMap<>();
        response.put("event", updatedEvent);
        return response;
    }


    public Page<Event> searchEvents(String performerName, String title, Pageable pageable) {
        if (performerName != null && !performerName.isEmpty()) {
            return eventRepository.findByPerformerNameContainingIgnoreCase(performerName, pageable);
        } else if (title != null && !title.isEmpty()) {
            return eventRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            return eventRepository.findAll(pageable);
        }
    }


}
