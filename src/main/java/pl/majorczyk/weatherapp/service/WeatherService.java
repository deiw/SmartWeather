package pl.majorczyk.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.majorczyk.weatherapp.data.weather.DataProvider;
import pl.majorczyk.weatherapp.model.weather.Weather;
import pl.majorczyk.weatherapp.parser.WeatherParser;
import pl.majorczyk.weatherapp.utils.CitiesProvider;

import java.util.List;
import java.util.Locale;

@Service
public class WeatherService {

    private static final String URL="https://api.openweathermap.org/data/2.5/";
    private static final String KEY="&APPID=bcfd9a60534615d2ddb8f9399201680e";

    private DataProvider weatherProvider;
    private CitiesProvider cityProvider;
    private WeatherParser parser;

    @Autowired
    public WeatherService(DataProvider weatherProvider, CitiesProvider cityProvider, WeatherParser parser) {
        this.weatherProvider = weatherProvider;
        this.cityProvider = cityProvider;
        this.parser=parser;
    }

    public Weather getCurrentWeather(String city){
        return parser.parse(getWeather(city,"weather"));
    }
    public List<Weather>getForecastWeather(String city){
        return parser.parseList(getWeather(city,"forecast"));
    }

    private String getWeather(String city,String type){
        String lang= Locale.getDefault().toString().substring(0,2);
        String path=URL+type+"?id="+cityProvider.getCityId(city)+KEY+"&units=metric&lang="+lang;
        return weatherProvider.getData(path);
    }

}
