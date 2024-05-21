package com.comarch.szkolenia.rest.api.controllers;

import com.comarch.szkolenia.rest.api.model.User;
import com.comarch.szkolenia.rest.api.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/user")
    public List<User> getUsers() {
        return this.userRepository.getAll();
    }
}
