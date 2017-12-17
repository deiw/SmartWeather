package pl.majorczyk.weatherapp.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.model.WeatherDetails;

@Component
@Parser(type = Parser.ParserType.WEATHER)
public class JsonWeatherParser implements DataParser<Weather> {
    @Override
    public Weather parse(String response) {
        Weather weather=null;
        if(response!=null){
            JSONParser parser=new JSONParser();
            try {
                JSONObject data=(JSONObject)parser.parse(response);
                JSONArray basicArray=(JSONArray) data.get("weather");
                JSONObject basic=(JSONObject)basicArray.get(0);
                JSONObject main=(JSONObject) data.get("main");
                JSONObject wind=(JSONObject) data.get("wind");
                JSONObject clouds=(JSONObject) data.get("clouds");

                weather=new Weather();
                WeatherDetails details=new WeatherDetails();

                weather.setMain(basic.get("main").toString());
                weather.setTemp(main.get("temp").toString());
                weather.setPressure(main.get("pressure").toString());
                weather.setVisibility(data.get("visibility").toString());

                details.setDescription(basic.get("description").toString());
                details.setHumidity(main.get("humidity").toString());
                details.setTempMin(main.get("temp_min").toString());
                details.setTempMax(main.get("temp_max").toString());
                details.setWindSpeed(wind.get("speed").toString());
                details.setClouds(clouds.get("all").toString());

                weather.setDetails(details);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return weather;
    }
}
