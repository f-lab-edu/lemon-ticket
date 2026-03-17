package com.flab.lemonticket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="event_img")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventImgId;

    private Long eventId;
    private String imgPath;

}
