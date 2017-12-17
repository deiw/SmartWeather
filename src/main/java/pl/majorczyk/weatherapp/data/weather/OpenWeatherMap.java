package pl.majorczyk.weatherapp.data.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.parser.DataParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class OpenWeatherMap implements DataProvider {

    private DataParser<Weather> parser;

    @Autowired
    public OpenWeatherMap(DataParser<Weather> parser) {
        this.parser = parser;
    }

    @Override
    public Weather getWeather(String path) throws IOException {
        Weather weather=null;
        URL url=new URL(path);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.connect();
        int responseCode=connection.getResponseCode();
        if(responseCode==200){
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            weather=parser.parse(sb.toString());
        }

        return weather;
    }
}
