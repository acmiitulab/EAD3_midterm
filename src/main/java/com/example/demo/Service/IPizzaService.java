package com.example.demo.Service;

import com.example.demo.Models.Pizza;

import java.util.List;

public interface IPizzaService {

    List<Pizza> getALl();
    Pizza getByName (String name);
    void delete(Pizza pizza);
    Pizza update(Pizza pizza);


}
