package pl.majorczyk.weatherapp.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Component
public class CitiesIdProvider {

    private HashMap<String,String> cities;

    CitiesIdProvider(){
        if(cities==null){
            load();
        }
    }
    private void load() {
            cities=new HashMap<>();
            ClassLoader loader=ClassLoader.getSystemClassLoader();
            JSONParser parser=new JSONParser();
            try {
                JSONArray array=(JSONArray)parser.parse(new FileReader(loader.getResource("city_list.json").getFile()));
                if(!array.isEmpty()){
                    for(Object o:array){
                        JSONObject obj=(JSONObject)o;
                        String id=obj.get("id").toString();
                        String city=obj.get("name").toString();
                        cities.put(city,id);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

    public String getCityId(String name) {
        return cities.get(name);
    }
}
