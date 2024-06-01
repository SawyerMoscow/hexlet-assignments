package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.daytime.Daytime ;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    private Daytime dt;

    @GetMapping(path = "/welcome")
    String home() {
        return "It is " + dt.getName() + " now! Welcome to Spring!";
    }
}
// END
