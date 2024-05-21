package com.comarch.szkolenia.rest.api.repositories;

import com.comarch.szkolenia.rest.api.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users.add(new User(1, "janusz", "janusz123", "Janusz", "Kowalski"));
        this.users.add(new User(2, "wiesiek", "wiesiek123", "Wiesiek", "Mainowski"));
        this.users.add(new User(3, "zbyszek", "zbyszek123", "Zbyszek", "Kowalski"));
        this.users.add(new User(4, "mateusz", "mateusz123", "Mateusz", "Bereda"));
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<User> create(User user) {
        return Optional.empty();
    }
}
