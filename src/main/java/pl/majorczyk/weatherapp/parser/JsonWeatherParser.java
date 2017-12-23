package pl.majorczyk.weatherapp.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import pl.majorczyk.weatherapp.model.ForecastWeather;
import pl.majorczyk.weatherapp.model.Weather;
import pl.majorczyk.weatherapp.model.WeatherDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class JsonWeatherParser implements WeatherParser {
    @Override
    public Weather parse(String response) {
        Weather weather=null;
        if(response!=null){
            JSONParser parser=new JSONParser();
            try {
                JSONObject data=(JSONObject)parser.parse(response);
                weather=new Weather();
                weather=getWeatherObject(weather,data);
                weather.setCity(data.get("name").toString());

            } catch (ParseException | NullPointerException e) {
                e.printStackTrace();
                weather=null;
            }
        }
        return weather;
    }

    @Override
    public List<Weather> parseList(String response) {
        List<Weather> list=null;
        if(response!=null){
            JSONParser parser=new JSONParser();
            try {
                JSONObject data=(JSONObject)parser.parse(response);
                JSONArray array=(JSONArray)data.get("list");
                JSONObject city=(JSONObject)data.get("city");
                String name=city.get("name").toString();
                list=new ArrayList<>();
                for (Object o : array) {
                    JSONObject obj = (JSONObject) o;

                    Weather weather = new ForecastWeather();
                    weather=getWeatherObject(weather,obj);
                    weather.setCity(name);

                    String stringDate =obj.get("dt_txt").toString();
                    ForecastWeather w=(ForecastWeather)weather;
                    Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
                    w.setDay(new SimpleDateFormat("EEEE", Locale.getDefault()).format(date));
                    w.setTime(new SimpleDateFormat("HH:mm").format(date));

                    list.add(w);
                }
            }catch (ParseException | java.text.ParseException | NullPointerException e){
                e.printStackTrace();
                list=null;
            }
        }
        return list;
    }
    private Weather getWeatherObject(Weather weather,JSONObject object) throws NullPointerException{
        JSONArray jsonArray=(JSONArray)object.get("weather");
        JSONObject main=(JSONObject)object.get("main");
        JSONObject clouds=(JSONObject)object.get("clouds");
        JSONObject wind=(JSONObject)object.get("wind");
        JSONObject basic=(JSONObject)jsonArray.get(0);

        WeatherDetails details=new WeatherDetails();

        weather.setMain(basic.get("main").toString());
        weather.setTemp(main.get("temp").toString());
        weather.setPressure(main.get("pressure").toString());

        details.setDescription(basic.get("description").toString());
        details.setHumidity(main.get("humidity").toString());
        details.setWindSpeed(wind.get("speed").toString());
        details.setClouds(clouds.get("all").toString());

        weather.setDetails(details);

        return weather;
    }
}
