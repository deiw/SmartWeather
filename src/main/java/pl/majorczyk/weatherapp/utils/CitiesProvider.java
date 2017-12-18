package pl.majorczyk.weatherapp.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CitiesProvider {

    private Map<String,String> cities;

    CitiesProvider(){
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

    public List<String> getCities(String search){
        List<String> cityList=new ArrayList<>();
        for(String s:cities.keySet()){
            if(s.toLowerCase().contains(search.toLowerCase())){
                cityList.add(s);
            }
        }
        return cityList;
    }
}
