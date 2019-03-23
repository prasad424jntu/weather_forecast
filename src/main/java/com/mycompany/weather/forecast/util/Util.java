package com.mycompany.weather.forecast.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	private LocalDateTime endDayOfForecast;
	
	public Util() {
		super();
		this.endDayOfForecast = getEndDayOfForecast();
	}
	
	public LocalDateTime getEndDayOfForecast() {
		LocalDateTime ldtLimit = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		ldtLimit = ldtLimit.plus(3, ChronoUnit.DAYS);
		return ldtLimit;
	}
	
	public boolean isDateInRange(Instant dt) {
		LocalDateTime dtForecast = LocalDateTime.ofInstant(dt, ZoneOffset.UTC);
		return dtForecast.getDayOfYear() <= endDayOfForecast.getDayOfYear();
	}

	public boolean isDayTimeForecast(Instant dt) {
		LocalDateTime dtForecast = LocalDateTime.ofInstant(dt, ZoneOffset.UTC);
		return dtForecast.getHour() >= 6 && dtForecast.getHour() <= 18;
	}
	
	
}