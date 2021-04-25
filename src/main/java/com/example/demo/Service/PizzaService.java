package com.example.demo.Service;

import com.example.demo.Models.Pizza;
import com.example.demo.Repositories.PizzaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PizzaService implements IPizzaService{

    @Autowired
    private PizzaRep  pizzaRep;

    @Override
    public List<Pizza> getALl() {
        return pizzaRep.findAll();
    }

    @Override
    public Pizza getByName(String name) {
        return pizzaRep.getByName(name);
    }

    @Override
    public void delete(Pizza pizza) {
        pizzaRep.delete(pizza);
    }

    @Override
    public Pizza update(Pizza pizza) {
        return pizzaRep.save(pizza);
    }
}
