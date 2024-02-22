package com.recetas.services;

import com.recetas.model.User;

public interface UserService {

    public User findUserById(Long id)throws Exception;
}
