package com.bkbn.eventmanagement.dto;

import com.bkbn.eventmanagement.model.EventGuest;
import com.bkbn.eventmanagement.model.EventType;
import com.bkbn.eventmanagement.model.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Integer id;
    @NotBlank(message = "Event name is mandatory")
    private String name;
    @NotBlank(message = "City is mandatory")
    private String city;
    private String country;
    private Date date;
    @NotNull(message = "Event type is mandatory")
    private EventType eventType;
    private Weather weather;
    private List<EventGuest> guestList;
}