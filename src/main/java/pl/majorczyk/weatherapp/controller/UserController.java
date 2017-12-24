package pl.majorczyk.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.majorczyk.weatherapp.model.user.User;
import pl.majorczyk.weatherapp.service.UserService;

@RestController
@Validated
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    path = "/register")
    public ResponseEntity registerUser(@RequestBody User user){
            service.addUser(user);
            return ResponseEntity.ok().build();
    }
}
