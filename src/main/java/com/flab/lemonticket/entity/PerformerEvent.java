package com.flab.lemonticket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="performer_event")
public class PerformerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performerEventId;

    private Long performerId;
    private Long eventId;
}
