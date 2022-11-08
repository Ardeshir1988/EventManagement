package com.bkbn.eventmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String city;
    private String country;
    private Date date;
    private EventType eventType;
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Weather weather;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EventGuest> guestList;
}
