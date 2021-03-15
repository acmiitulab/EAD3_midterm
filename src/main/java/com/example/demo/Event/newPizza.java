package com.example.demo.Event;

import com.example.demo.Models.Pizza;
import org.springframework.context.ApplicationEvent;

public class newPizza extends ApplicationEvent {
    private Pizza newPizza;

    public newPizza(Object source, Pizza pizza) {
        super(source);
        newPizza = pizza;
    }

    public Pizza getNewPizza() {
        return newPizza;
    }

}
