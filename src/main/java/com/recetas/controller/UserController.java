package com.recetas.controller;

import com.recetas.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public User creatUser(@RequestBody User user){
        return user;
    }
}
