package pl.majorczyk.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.majorczyk.weatherapp.locator.Locator;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.service.WeatherService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class HomeController {

    private static final String CURRENT="weather";

    private WeatherService service;
    private Locator locator;

    @Autowired
    public HomeController(WeatherService service, Locator locator) {
        this.service = service;
        this.locator = locator;
    }
      /*
        @response 401: no location founded for ip
        @response 402: no weather founded for location
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weather> index(HttpServletRequest request){
        Weather weather;
        //String ip=request.getHeader("for");
        String city=locator.locate("153.19.108.41");
        if(city==null){
           return ResponseEntity.status(401).build();
        }
        else {
            weather=service.getWeather(city,CURRENT);
            if(weather==null){
                return ResponseEntity.status(402).build();
            }
            else return ResponseEntity.ok(weather);
        }
    }
}
