package pl.majorczyk.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.majorczyk.weatherapp.utils.CitiesProvider;

import java.util.List;

@RestController
public class SearchController {

    private CitiesProvider provider;

    @Autowired
    public SearchController(CitiesProvider provider) {
        this.provider = provider;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    path = "/search")
    public List<String> search(@RequestParam String s){
        return provider.getCities(s);
    }

}
