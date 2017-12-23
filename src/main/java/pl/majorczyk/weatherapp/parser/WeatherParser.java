package pl.majorczyk.weatherapp.parser;

import pl.majorczyk.weatherapp.model.Weather;

import java.util.List;

public interface WeatherParser{
    Weather parse(String response);
    List<Weather> parseList(String response);
}
