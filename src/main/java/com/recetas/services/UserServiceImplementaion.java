package com.recetas.services;

import com.recetas.config.JwtProvider;
import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementaion implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("usuario no encontrado");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        if(email==null){
            throw new Exception("provide valid jwt token...");
        }
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("usuario no encontrado con email "+email);
        }
        return user;
    }
}
