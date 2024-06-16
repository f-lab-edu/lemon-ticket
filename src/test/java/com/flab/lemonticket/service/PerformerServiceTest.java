package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.Performer;
import com.flab.lemonticket.repository.PerformerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PerformerServiceTest {

    @Mock
    private PerformerRepository performerRepository;

    @InjectMocks
    private PerformerService performerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("addPerformer 메서드가 새로운 공연자 정보를 성공적으로 추가하는지 테스트")
    void testAddPerformer() {
        Performer performer = new Performer();
        performer.setPerformerId(1L);
        performer.setName("Test Performer");
        performer.setDescription("Test Description");

        when(performerRepository.save(any(Performer.class))).thenReturn(performer);

        Performer savedPerformer = performerService.addPerformer(performer);

        assertNotNull(savedPerformer);
        assertEquals(performer.getPerformerId(), savedPerformer.getPerformerId());
        assertEquals(performer.getName(), savedPerformer.getName());
        assertEquals(performer.getDescription(), savedPerformer.getDescription());
        verify(performerRepository, times(1)).save(any(Performer.class));
    }
}