package com.example.demo.Event;

import com.example.demo.Models.Order;
import com.example.demo.Models.Pizza;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class orderDoneEvent extends ApplicationEvent {
    private Order order;

    public orderDoneEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}