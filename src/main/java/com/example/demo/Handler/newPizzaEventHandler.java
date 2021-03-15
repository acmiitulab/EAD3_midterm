package com.example.demo.Handler;

import com.example.demo.Event.newPizza;
import com.example.demo.Event.orderDoneEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class newPizzaEventHandler implements ApplicationListener<newPizza> {
    @Override
    public void onApplicationEvent(newPizza newPizza) {
        System.out.println("\nnewPizzaEventHandler.onApplicationEvent");
        System.out.println("We have new pizza!");
        System.out.println("Pizza: " + newPizza.getNewPizza().getName());
        System.out.println("Price : " + newPizza.getNewPizza().getPrice() + "\n");
    }
}
