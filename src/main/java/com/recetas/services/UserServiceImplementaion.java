package com.recetas.services;

import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementaion implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("usuario no encontrado");
    }
}
