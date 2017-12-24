package pl.majorczyk.weatherapp.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.majorczyk.weatherapp.utils.CitiesProvider;

import javax.validation.constraints.Size;

@RestController
@Validated
public class SearchController {

    private CitiesProvider provider;

    @Autowired
    public SearchController(CitiesProvider provider) {
        this.provider = provider;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    method = RequestMethod.GET,
                    path = "/search")
    public ResponseEntity search(@Size(min = 3) @NotEmpty @RequestParam String s){
        return ResponseEntity.ok(provider.getCities(s));
    }

}
