package com.flab.lemonticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatLayoutAddRequest {
    private String section;
    private int row;
    private int seatsCount;
    private boolean isStanding;
    private double price;
}
