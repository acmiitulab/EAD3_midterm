package com.example.demo.Models;

import javax.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double price;

    public Pizza(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}
