package com.flab.lemonticket.service;

import com.flab.lemonticket.controller.EventController;
import com.flab.lemonticket.entity.Event;
import com.flab.lemonticket.entity.EventTime;
import com.flab.lemonticket.entity.PerformerEvent;
import com.flab.lemonticket.entity.SeatLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EventServiceTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("addEvent 메소드가 공연 정보를 성공적으로 추가하는지 테스트")
    void testAddEvent() throws IOException {
        Event event = new Event();
        event.setEventId(1L);
        event.setTitle("Test Event");
        event.setCreatedAt(now());
        event.setUpdatedAt(now());

        List<EventTime> eventTimes = Collections.singletonList(new EventTime());
        List<SeatLayout> seatLayouts = Collections.singletonList(new SeatLayout());
        List<PerformerEvent> performerEvents = Collections.singletonList(new PerformerEvent());
        List<MultipartFile> eventImgs = Collections.emptyList();
        MultipartFile profileImgFile = mock(MultipartFile.class);

        when(eventService.addEvent(any(Event.class), anyList(), anyList(), anyList(), anyList(), any(MultipartFile.class)))
                .thenReturn(Map.of("event", event));

        Map<String, Object> response = eventService.addEvent(event, eventTimes, seatLayouts, performerEvents, eventImgs, profileImgFile);

        assertNotNull(response);
        assertEquals(event, response.get("event"));
        verify(eventService, times(1)).addEvent(any(Event.class), anyList(), anyList(), anyList(), anyList(), any(MultipartFile.class));
    }

    @Test
    @DisplayName("modifyEvent 메소드가 공연 정보를 성공적으로 수정하는지 테스트")
    void testModifyEvent() throws IOException {
        Event event = new Event();
        event.setEventId(1L);
        event.setTitle("Updated Event");
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());

        List<EventTime> eventTimes = Collections.singletonList(new EventTime());
        List<SeatLayout> seatLayouts = Collections.singletonList(new SeatLayout());
        List<PerformerEvent> performerEvents = Collections.singletonList(new PerformerEvent());
        List<MultipartFile> eventImgs = Collections.emptyList();
        MultipartFile profileImgFile = mock(MultipartFile.class);

        when(eventService.modifyEvent(any(Event.class), anyList(), anyList(), anyList(), anyList(), any(MultipartFile.class)))
                .thenReturn(Map.of("event", event));

        Map<String, Object> response = eventService.modifyEvent(event, eventTimes, seatLayouts, performerEvents, eventImgs, profileImgFile);

        assertNotNull(response);
        assertEquals(event, response.get("event"));
        verify(eventService, times(1)).modifyEvent(any(Event.class), anyList(), anyList(), anyList(), anyList(), any(MultipartFile.class));
    }

    @Test
    @DisplayName("searchEvents 메소드가 공연 정보를 성공적으로 검색하는지 테스트")
    void testSearchEvents() {
        Pageable pageable = PageRequest.of(0, 10);
        Event event = new Event();
        event.setEventId(1L);
        event.setTitle("Search Event");

        Page<Event> eventsPage = new PageImpl<>(Collections.singletonList(event));
        when(eventService.searchEvents(anyString(), anyString(), any(Pageable.class)))
                .thenReturn(eventsPage);

        Page<Event> result = eventService.searchEvents("performerName", "title", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(event, result.getContent().get(0));
        verify(eventService, times(1)).searchEvents(anyString(), anyString(), any(Pageable.class));
    }
}
