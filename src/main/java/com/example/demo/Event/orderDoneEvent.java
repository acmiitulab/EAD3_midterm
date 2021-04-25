package com.example.demo.Event;

import com.example.demo.Models.Orders;
import org.springframework.context.ApplicationEvent;

public class orderDoneEvent extends ApplicationEvent {
    private Orders orders;

    public orderDoneEvent(Object source, Orders orders) {
        super(source);
        this.orders = orders;
    }

    public Orders getOrder() {
        return orders;
    }
}