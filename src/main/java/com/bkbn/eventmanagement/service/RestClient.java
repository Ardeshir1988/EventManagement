package com.bkbn.eventmanagement.service;

import com.bkbn.eventmanagement.dto.OpenWeatherResponse;

public interface RestClient {
    OpenWeatherResponse getWeatherData(String city);
}
