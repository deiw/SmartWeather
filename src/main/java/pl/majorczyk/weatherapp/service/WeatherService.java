package pl.majorczyk.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.majorczyk.weatherapp.data.weather.DataProvider;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.utils.CitiesProvider;

@Service
public class WeatherService {

    private static final String URL="https://api.openweathermap.org/data/2.5/";
    private static final String KEY="&APPID=bcfd9a60534615d2ddb8f9399201680e";

    private DataProvider weatherProvider;
    private CitiesProvider cityProvider;

    @Autowired
    public WeatherService(DataProvider weatherProvider, CitiesProvider cityProvider) {
        this.weatherProvider = weatherProvider;
        this.cityProvider = cityProvider;
    }

    public Weather getWeather(String city, String type){
        String path=URL+type+"?id="+cityProvider.getCityId(city)+KEY;
        return weatherProvider.getWeather(path);
    }
}
