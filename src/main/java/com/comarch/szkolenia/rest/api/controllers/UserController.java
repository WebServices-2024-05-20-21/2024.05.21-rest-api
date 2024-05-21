package com.comarch.szkolenia.rest.api.controllers;

import com.comarch.szkolenia.rest.api.model.User;
import com.comarch.szkolenia.rest.api.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("")
    public ResponseEntity getAllOrByLogin(@RequestParam(name = "login", required = false) String login) {
        if(login == null) {
            List<User> users = this.userRepository.getAll();
            users.forEach(u -> {
                u.add(linkTo(UserController.class).slash(u.getId()).withSelfRel());
                u.add(linkTo(OrderController.class).slash(10).withRel("order"));
            });
            CollectionModel<User> collectionModel = CollectionModel.of(users);
            collectionModel.add(linkTo(UserController.class).withSelfRel());
            collectionModel.add(linkTo(OrderController.class).withRel("orders"));
            return ResponseEntity.ok(collectionModel);
        } else {
            Optional<User> userBox = this.userRepository.getByLogin(login);
            if(userBox.isPresent()) {
                return ResponseEntity.ok(userBox.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getById(@PathVariable("id") int id) {
        Optional<User> userBox = this.userRepository.getById(id);
        if(userBox.isPresent()) {
            User user = userBox.get();
            user.add(linkTo(UserController.class).slash(id).withSelfRel());
            user.add(linkTo(OrderController.class).slash(10).withRel("order"));
            return ResponseEntity.ok(EntityModel.of(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("id") int id) {
        user.setId(id);
        Optional<User> userBox = this.userRepository.update(user);
        if(userBox.isPresent()) {
            return ResponseEntity.ok(userBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.userRepository.delete(id);
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        Optional<User> userBox = this.userRepository.create(user);
        if(userBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userBox.get());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
