package com.example.demo.Models;


import java.util.ArrayList;

public class Order {
    private int id;
    private String Username;
    private ArrayList<String> pizzas ;
    private Double price;
    private Boolean isDone;

    public Order(int id, String user, ArrayList<String> pizzas, Double price) {
        this.id = id;
        Username = user;
        this.pizzas = pizzas;
        this.price = price;
        isDone = false;
    }

    public Order( String user, ArrayList<String> pizzas, Double price) {
        Username = user;
        this.pizzas = pizzas;
        this.price = price;
        isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public ArrayList<String> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<String> pizzas) {
        this.pizzas = pizzas;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
