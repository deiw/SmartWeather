package pl.majorczyk.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.majorczyk.weatherapp.locator.Locator;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.service.WeatherService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

     /*
        @response 401: no location founded for ip
        @response 402: no weather founded for location
     */

@RestController
@RequestMapping("/")
public class WeatherController {

    private WeatherService service;
    private Locator locator;

    @Autowired
    public WeatherController(WeatherService service, Locator locator) {
        this.service = service;
        this.locator = locator;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weather> getLocationWeather(HttpServletRequest request){
        //String ip=request.getHeader("for");
        String city=locator.locate("83.145.158.238");
        if(city==null){
           return ResponseEntity.status(401).build();
        }
        else {
            return checkCurrentWeather(city);
        }
    }
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            path = "/current/{city}")
    public ResponseEntity<Weather> getCurrentWeather(@PathVariable String city){
        return checkCurrentWeather(city);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            path = "/forecast/{city}")
    public ResponseEntity<List<Weather>> getForecastWeather(@PathVariable String city){
        List<Weather> list=service.getForecastWeather(city);
        if(list==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    private ResponseEntity<Weather> checkCurrentWeather(String city){
        Weather weather;
        weather=service.getCurrentWeather(city);
        if(weather==null){
            return ResponseEntity.status(402).build();
        }
        else return ResponseEntity.ok(weather);
    }
}
