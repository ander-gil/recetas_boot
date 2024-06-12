package com.recetas.controller;

import com.recetas.config.JwtProvider;
import com.recetas.model.User;
import com.recetas.repository.UserRepository;
import com.recetas.request.LoginRequest;
import com.recetas.response.AuthRespose;
import com.recetas.services.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")

    public AuthRespose createUser(@RequestBody User user) {
        try {
            String email = user.getEmail();
            String password = user.getPassword();
            String fullName = user.getFullName();

            User isExistEmail = userRepository.findByEmail(email);
            if (isExistEmail != null) {
                throw new Exception("El usuario con este correo ya se encuentra registrado");
            }

            User createdUser = new User();
            createdUser.setFullName(fullName);
            createdUser.setEmail(email);
            createdUser.setPassword(passwordEncoder.encode(password));

            User savedUser = userRepository.save(createdUser);

            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtProvider.generateToken(authentication);

            AuthRespose resp = new AuthRespose();
            resp.setJwt(token);
            resp.setMessage("signup seccess");

            return resp;
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprime la excepción en la consola para propósitos de depuración
            AuthRespose resp = new AuthRespose();
            resp.setMessage("Error en la creación del usuario: " + e.getMessage()); // Mensaje de error para el cliente
            return resp;
        }
    }


    @PostMapping("/signin")
    public AuthRespose signinHandler(@RequestBody LoginRequest loginRequest){
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);


        AuthRespose resp = new AuthRespose();
        resp.setJwt(token);
        resp.setMessage("signup seccess");

        return resp;
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);
        if (userDetails==null){
            throw new BadCredentialsException("usuario no encontrado");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("contraseña invalida");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
