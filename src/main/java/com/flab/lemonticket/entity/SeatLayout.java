package com.flab.lemonticket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name ="seat_layout")
@Setter
public class SeatLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatLayoutId;

    private Long eventId;
    private Long eventTimeId;
    private String section;

    private int seatsRow;
    private int seatsCount;
    private byte[] bmSeats;
    private boolean isStanding;
    private double price;
}
