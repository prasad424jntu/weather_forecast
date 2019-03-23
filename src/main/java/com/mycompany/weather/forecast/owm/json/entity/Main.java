package com.mycompany.weather.forecast.owm.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	public double temp;
	public double temp_min;
	public double temp_max;
	public double pressure;
	public double sea_level;
	public double grnd_level;
	public double humidity;
	public double temp_kf;
}
