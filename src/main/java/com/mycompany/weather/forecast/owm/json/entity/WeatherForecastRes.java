package com.mycompany.weather.forecast.owm.json.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WeatherForecastRes {
	public double dayForecastAvgTempOf3Days_Celsius;
	public double nightForecastAvgTempOf3Days_Celsius;
	public double foreCastPressureAvgOf3Days_hPA;
}
