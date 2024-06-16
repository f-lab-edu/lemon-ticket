package com.flab.lemonticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTimeAddRequest {
    private LocalDateTime dateTime;
    private int durationTime;
    private Long venueId;
}