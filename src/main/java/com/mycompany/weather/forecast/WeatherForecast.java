package com.mycompany.weather.forecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class WeatherForecast {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecast.class, args);
	}

}

