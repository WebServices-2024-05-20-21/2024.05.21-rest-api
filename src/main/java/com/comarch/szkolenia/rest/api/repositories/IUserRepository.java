package com.comarch.szkolenia.rest.api.repositories;

import com.comarch.szkolenia.rest.api.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<User> getAll();
    Optional<User> getById(int id);
    Optional<User> getByLogin(String login);
    Optional<User> update(User user);
    void delete(int id);
    Optional<User> create(User user);
}
