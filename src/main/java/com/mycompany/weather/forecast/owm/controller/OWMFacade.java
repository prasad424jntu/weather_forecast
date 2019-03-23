package com.mycompany.weather.forecast.owm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycompany.weather.forecast.exception.CityDataNotFound;
import com.mycompany.weather.forecast.owm.json.entity.OWMWeatherForecast;
import com.mycompany.weather.forecast.owm.json.entity.WeatherForecastRes;
import com.mycompany.weather.forecast.owm.json.entity.WeatherDetails;
import com.mycompany.weather.forecast.util.WeatherForecastUtil;

@Service
public class OWMFacade{

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WeatherForecastUtil wfUtil;

	@Value("${owm.apiKey}")
	private String apiKey;

	@Value("${owm.apiUrl}")
	private String apiUrl;

	
	public WeatherForecastRes getWeatherForecastByCity(String city) throws CityDataNotFound {
		OWMWeatherForecast weatherForecast = new OWMWeatherForecast();
		String owmForecastCityUrl = getForecastUrlForCity(city);
		try {
			weatherForecast = restTemplate.getForObject(owmForecastCityUrl, OWMWeatherForecast.class);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new CityDataNotFound("data for the city: " + city + " not Found");
			}
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

		}

		WeatherForecastRes weatherForecastRes = new WeatherForecastRes();
		List<WeatherDetails> weatherDetails = weatherForecast.getList();
		weatherForecastRes.setDayForecastAvgTempOf3Days_Celsius(formatDoubleValue(wfUtil.getDayForecastAvgTempOf3Days_Celsius(weatherDetails)));
		weatherForecastRes.setNightForecastAvgTempOf3Days_Celsius(formatDoubleValue(wfUtil.getNightForecastAvgTempOf3Days_Celsius(weatherDetails)));
		weatherForecastRes.setForeCastPressureAvgOf3Days_hPA(formatDoubleValue(wfUtil.getForeCastPressureAvgOf3Days_hPA(weatherDetails)));
		return weatherForecastRes;
	}

	private String getForecastUrlForCity(String city) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl).queryParam("q", city).queryParam("units", "metric").queryParam("appId", apiKey);
		return builder.toUriString();
	}

	public double formatDoubleValue(double doubletoFormat) {
		return Math.round(doubletoFormat * 1000) / 1000D;
	}
}
