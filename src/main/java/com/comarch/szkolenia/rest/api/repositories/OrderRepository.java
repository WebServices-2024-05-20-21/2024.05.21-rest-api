package com.comarch.szkolenia.rest.api.repositories;

import com.comarch.szkolenia.rest.api.model.Order;
import com.comarch.szkolenia.rest.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Autowired
    private IUserRepository userRepository;

    public OrderRepository() {
        this.orders.add(new Order(1, LocalDateTime.now(), 400.99,
                Order.State.NEW, this.userRepository.getById(1).get()));
        this.orders.add(new Order(2, LocalDateTime.now(), 200.99,
                Order.State.PAID, this.userRepository.getById(2).get()));
        this.orders.add(new Order(3, LocalDateTime.now(), 700.00,
                Order.State.SENT, this.userRepository.getById(4).get()));
        this.orders.add(new Order(4, LocalDateTime.now(), 350.11,
                Order.State.DONE, this.userRepository.getById(1).get()));
        this.orders.add(new Order(5, LocalDateTime.now(), 50.55,
                Order.State.DONE, this.userRepository.getById(3).get()));
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getByPriceFrom(double from) {
        return null;
    }

    @Override
    public Optional<Order> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) {

    }
}
