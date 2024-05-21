package com.comarch.szkolenia.rest.api.controllers;

import com.comarch.szkolenia.rest.api.model.Order;
import com.comarch.szkolenia.rest.api.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private IOrderRepository orderRepository;

    @GetMapping("")
    public List<Order> getAllOrGetByPrice(@RequestParam(value = "price_from", required = false) Double priceFrom) {
        if(priceFrom == null) {
            return this.orderRepository.getAll();
        }
        return this.orderRepository.getByPriceFrom(priceFrom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") int id) {
        Optional<Order> orderBox = this.orderRepository.getById(id);
        if(orderBox.isPresent()) {
            return ResponseEntity.ok(orderBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.orderRepository.delete(id);
    }
}
