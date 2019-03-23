package com.mycompany.weather.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.weather.forecast.exception.CityDataNotFound;
import com.mycompany.weather.forecast.owm.controller.OWMFacade;
import com.mycompany.weather.forecast.owm.json.entity.WeatherForecastRes;

@RestController
@Validated
@RequestMapping("/api/v1")
public class WeatherController {

	@Autowired
	private OWMFacade owmFacade;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody WeatherForecastRes data(@RequestParam("city") String city) throws CityDataNotFound {
		return owmFacade.getWeatherForecastByCity(city);
	}

}
