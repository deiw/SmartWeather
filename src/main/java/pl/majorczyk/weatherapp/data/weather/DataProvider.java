package pl.majorczyk.weatherapp.data.weather;

import pl.majorczyk.weatherapp.model.Weather;

import java.io.IOException;

public interface DataProvider {
    Weather getWeather(String path) throws IOException;
}
