package com.flab.lemonticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "event_time")
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventTimeId;

    private Long eventId;
    private LocalDateTime dateTime;
    private int durationTime;
    private Long venueId;
}
