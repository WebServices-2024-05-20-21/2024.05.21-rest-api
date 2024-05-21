package com.comarch.szkolenia.rest.api.controllers;

import com.comarch.szkolenia.rest.api.model.User;
import com.comarch.szkolenia.rest.api.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity getAllOrByLogin(@RequestParam(name = "login", required = false) String login) {
        if(login == null) {
            return ResponseEntity.ok(this.userRepository.getAll());
        } else {
            Optional<User> userBox = this.userRepository.getByLogin(login);
            if(userBox.isPresent()) {
                return ResponseEntity.ok(userBox.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        Optional<User> userBox = this.userRepository.getById(id);
        if(userBox.isPresent()) {
            return ResponseEntity.ok(userBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") int id) {
        user.setId(id);
        Optional<User> userBox = this.userRepository.update(user);
        if(userBox.isPresent()) {
            return ResponseEntity.ok(userBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") int id) {
        this.userRepository.delete(id);
    }

    @PostMapping("/user")
    public ResponseEntity<User> create(@RequestBody User user) {
        Optional<User> userBox = this.userRepository.create(user);
        if(userBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userBox.get());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
