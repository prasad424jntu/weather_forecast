package com.mycompany.weather.forecast.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.mycompany.weather.forecast.owm.controller.OWMFacade;
import com.mycompany.weather.forecast.owm.json.entity.WeatherForecastRes;
import com.mycompany.weather.forecast.util.Util;
import com.mycompany.weather.forecast.util.WeatherForecastUtil;

import junit.framework.Assert;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WeatherController.class,OWMFacade.class,RestTemplate.class,WeatherForecastUtil.class,Util.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestWeatherController {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testADataForBerlin() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>("berlin", headers);
		ResponseEntity<WeatherForecastRes> response = restTemplate.exchange(getRootUrl() + "/api/v1/data",
		HttpMethod.POST, entity, WeatherForecastRes.class);
		WeatherForecastRes weatherForecastResponse =response.getBody();
		System.out.println("Weather Forecast response for Berlin: "+weatherForecastResponse.toString());
		Assert.assertTrue(response.getBody() instanceof WeatherForecastRes);
	}
}
