package com.example.demo.Models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Username;

    @OneToMany(
            fetch = FetchType.EAGER
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

}
