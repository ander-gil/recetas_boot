package com.recetas.controller;

import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import com.recetas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt)throws Exception{

        User user = userService.findUserByJwt(jwt);
        return user;
    }

    /*@PostMapping("/users")
    public User creatUser(@RequestBody User user) throws Exception {
        User emailExist = userRepository.findByEmail(user.getEmail());
        if(emailExist!=null){
            throw new Exception("Este correo ya se encuentra registrado " + user.getEmail());
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }*/

    /*@DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
    userRepository.deleteById(userId);
        return "usuario eliminado satisfactoriamente";
    }*/

    /*@GetMapping("/users")
    public List<User> getAllUsers(){
     List<User> users =  userRepository.findAll();
        return users;
    }*/

}
