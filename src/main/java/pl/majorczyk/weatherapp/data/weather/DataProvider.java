package pl.majorczyk.weatherapp.data.weather;

import pl.majorczyk.weatherapp.model.Weather;

public interface DataProvider {
    Weather getWeather(String path);
}
