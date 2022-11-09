package com.bkbn.eventmanagement.dto;

import com.bkbn.eventmanagement.model.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {
    private Weather main;
}