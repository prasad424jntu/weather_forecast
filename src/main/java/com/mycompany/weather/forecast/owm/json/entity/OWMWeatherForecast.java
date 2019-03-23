package com.mycompany.weather.forecast.owm.json.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OWMWeatherForecast {
	public List<WeatherDetails> list;
}

