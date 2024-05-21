package com.comarch.szkolenia.rest.api.repositories;

import com.comarch.szkolenia.rest.api.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    List<Order> getAll();
    List<Order> getByPriceFrom(double from);
    Optional<Order> getById(int id);
    void delete(int id);
}
