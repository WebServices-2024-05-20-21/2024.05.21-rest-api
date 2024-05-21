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
        return this.users;
    }

    @Override
    public Optional<User> getById(final int id) {
        return this.users.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public Optional<User> getByLogin(final String login) {
        return this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
    }

    @Override
    public Optional<User> update(final User user) {
        return this.users.stream().filter(u -> u.getId() == user.getId())
                .peek(u -> {
                    u.setLogin(user.getLogin());
                    u.setPassword(user.getPassword());
                    u.setName(user.getName());
                    u.setSurname(user.getSurname());
                }).findFirst();
    }

    @Override
    public void delete(final int id) {
        /*Iterator<User> iterator = this.users.iterator();
        while(iterator.hasNext()) {
            User u = iterator.next();
            if(u.getId() == id) {
                iterator.remove();
            }
        }*/

        this.users.removeIf(u -> u.getId() == id);
    }

    @Override
    public Optional<User> create(User user) {
        Optional<User> userWithLogin= this.users.stream()
                .filter(u -> u.getLogin().equals(user.getLogin())).findFirst();
        if(userWithLogin.isPresent()) {
            return Optional.empty();
        }
        Optional<Integer> maxIdBox = this.users.stream().map(u -> u.getId()).max((a,b) -> a - b);
        int newId = 1;
        if(maxIdBox.isPresent()) {
            newId = maxIdBox.get() + 1;
        }
        user.setId(newId);
        this.users.add(user);
        return Optional.of(user);
    }
}
