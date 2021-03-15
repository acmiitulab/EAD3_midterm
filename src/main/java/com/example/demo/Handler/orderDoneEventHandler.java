package com.example.demo.Handler;

import com.example.demo.Event.orderDoneEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class orderDoneEventHandler implements ApplicationListener<orderDoneEvent> {

    @Override
    public void onApplicationEvent(orderDoneEvent orderDoneEvent) {
        System.out.println("\norderDoneEventHandler.onApplicationEvent");
        System.out.println("Order : " + orderDoneEvent.getOrder().getId());
        System.out.println("Pizzas: " );
        for (String i : orderDoneEvent.getOrder().getPizzas()) {
            System.out.println(" " + i );
        }
        System.out.println("to pay:    " + orderDoneEvent.getOrder().getPrice() + "\n");
    }
}
