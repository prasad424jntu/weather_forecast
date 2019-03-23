package com.mycompany.weather.forecast.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.weather.forecast.owm.json.entity.WeatherDetails;

@Component
public class WeatherForecastUtil {
	
	@Autowired
	Util dateUtil;

	public double getDayForecastAvgTempOf3Days_Celsius(List<WeatherDetails> weatherDetails) {
		return weatherDetails.stream()
				.filter(wd -> dateUtil.isDateInRange(wd.getDt()))
				.filter(wd -> dateUtil.isDayTimeForecast(wd.getDt()))
				.mapToDouble(wd -> wd.main.getTemp()).summaryStatistics().getAverage();
	}

	public double getNightForecastAvgTempOf3Days_Celsius(List<WeatherDetails> weatherDetails) {
		return weatherDetails.stream()
				.filter(wd -> dateUtil.isDateInRange(wd.getDt()))
				.filter(wd -> !dateUtil.isDayTimeForecast(wd.getDt()))
				.mapToDouble(wd -> wd.main.getTemp()).summaryStatistics().getAverage();
	}

	public double getForeCastPressureAvgOf3Days_hPA(List<WeatherDetails> weatherDetails) {
		return weatherDetails.stream()
				.filter(wd -> dateUtil.isDateInRange(wd.getDt()))
				.mapToDouble(wd -> wd.main.getPressure())
				.summaryStatistics().getAverage();
	}

}
