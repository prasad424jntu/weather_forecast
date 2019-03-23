Weather Forecast Case Study

This app give avg forecast of day temperature in celsius,night temperature in celsius, air pressure in hPa for next 3 days forecast data taken from 
https://openweathermap.org


 Tech Stack

1) Java 1.8

2) Spring boot

3) Lambok

4) Swagger

# Setup help:
	If you want to run from an IDE please install lombok as below:
  # IDE LOMBOK setup:

1) Exit IDE(if it is open) and download jar from https://projectlombok.org/download

2) execute command: java -jar lombok.jar

3) This command will open a window.Give the IDE installed location(For eclipse path to eclipse.exe).

4) Restart IDE.

6) If IDE is eclipse verify installation as below:
		Go to Eclipse --> About Eclipse --> check 'Lombok v1.18.6 "Envious Ferret" is installed. https://projectlombok.org/'
		
# Swagger

Click here [Swagger](http://localhost:8080/swagger-ui.html) for swagger documentation.

# Integration test

Added a single integration test case(TestWeatherController::testADataForBerlin) to test the response for city Berlin.

# Usage

end point url:

[http://localhost:8080/api/v1/data?city=munich](http://localhost:8080/api/v1/data?city=munich)

response will be like this: 

{
    "dayForecastAvgTempOf3Days_Celsius": 7.54,
    "nightForecastAvgTempOf3Days_Celsius": 3.3,
    "foreCastPressureAvgOf3Days_hPA": 1025.3
}

# Add Ons:

If time given can enable SSL.

HATEOS also can be implemented.
