package com.mycompany.weather.forecast.owm.json.entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails {
	public Instant dt;
	public Main main;
	
} 
