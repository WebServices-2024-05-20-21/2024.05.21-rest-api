package com.comarch.szkolenia.rest.api.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private LocalDateTime date;
    private double sum;
    private State state;
    private User user;

    public Order(int id, LocalDateTime date, double sum, State state, User user) {
        this.id = id;
        this.date = date;
        this.sum = sum;
        this.state = state;
        this.user = user;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum State {
        NEW,
        PAID,
        SENT,
        DONE
    }
}
