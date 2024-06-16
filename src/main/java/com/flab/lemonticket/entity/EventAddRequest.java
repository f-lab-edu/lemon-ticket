package com.flab.lemonticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventAddRequest {
    private String title;
    private String description;
    private Long organizerId;
    private String eventType;
    private String saleStatus;
    private Long venueId;
    private List<Long> performerIds;
    private List<EventTimeAddRequest> eventTimes;
    private List<SeatLayoutAddRequest> seatLayouts;
    private List<MultipartFile> imgFiles;
    private MultipartFile profileImg;
}
