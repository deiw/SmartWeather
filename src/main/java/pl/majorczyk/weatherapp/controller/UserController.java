package pl.majorczyk.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.majorczyk.weatherapp.model.user.User;
import pl.majorczyk.weatherapp.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    path = "/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(),new HttpHeaders(), HttpStatus.PARTIAL_CONTENT);
        }
        else {
            service.addUser(user);
            return ResponseEntity.ok().build();
        }
    }
}
