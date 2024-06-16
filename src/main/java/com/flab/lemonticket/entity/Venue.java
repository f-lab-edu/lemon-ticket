package com.flab.lemonticket.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name ="venue")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;

    private String name;
    private String address;
    private String locationType;
    private String telNumber;
    private String website;
    private String venueInfo;
}
