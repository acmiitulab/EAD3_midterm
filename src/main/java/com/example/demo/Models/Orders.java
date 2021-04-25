package com.example.demo.Models;


import javax.persistence.*;
import java.util.List;


@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Username;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "order_pizzas",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizzas;

    private Double price;
    private Boolean isDone;

    public Orders() {


        if (pizzas != null) {
            double price = 0;
            for (Pizza pizza : pizzas) {
                price += pizza.getPrice();
            }
            this.price = price;

        }



    }

    public Orders(int id, String user, List<Pizza> pizzas) {
        this.id = id;
        Username = user;
        this.pizzas = pizzas;
        double price = 0;
        for (Pizza pizza : pizzas) {
            price += pizza.getPrice();
        }
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

    public List<Pizza>  getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
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
