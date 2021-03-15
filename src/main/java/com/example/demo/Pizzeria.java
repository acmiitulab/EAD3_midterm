package com.example.demo;

import com.example.demo.Collections.*;
import com.example.demo.Event.newPizza;
import com.example.demo.Event.orderDoneEvent;
import com.example.demo.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Scanner;

@Component("pizzeria")
public class Pizzeria {

    private OrdersCollection ordersCollection;
    private PizzasCollection pizzasCollection;
    private UsersCollection usersCollection;
    private ApplicationEventPublisher orderPublisher;
    private ApplicationEventPublisher newPizza;
    private User user;

    @Autowired
    public Pizzeria(OrdersCollection ordersCollection, PizzasCollection pizzasCollection, UsersCollection usersCollection, ApplicationEventPublisher orderPublisher, ApplicationEventPublisher newPizza) {
        this.ordersCollection = ordersCollection;
        this.pizzasCollection = pizzasCollection;
        this.usersCollection = usersCollection;
        this.orderPublisher = orderPublisher;
        this.newPizza = newPizza;
    }



    public void login(String username, String password) {
        User user = usersCollection.getUser(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                this.user = user;
            }
        }
    }


    public String MakeOrder(ArrayList<String> pizzas) {
        if (user == null) {
            return "You didn't auth";
        }
        Double price = 0.0;
        for (String i : pizzas) {
            Pizza pizza = pizzasCollection.getPizza(i);
            price += pizza.getPrice();
        }
        Order order = new Order(this.user.getUsername(), pizzas, price);
        ordersCollection.insert(order);
        ArrayList<Order> orders = ordersCollection.getOrdersByUsername(order.getUsername());
        int id = orders.get(orders.size() - 1).getId();
        order.setId(id);

        ordersCollection.insertPizzas(order);

        return "Done";
    }

    public String doOrder() {
        if (user == null) {
            return "You didn't auth";
        }
        if (!user.isAdmin()) {
            return "You are not admin";
        }
        ArrayList<Order> orders = ordersCollection.getOrders();
        for (Order i : orders) {
            if (i.isDone()) continue;
            else {
                ordersCollection.doOrder(i);
                Order order = ordersCollection.getOrderByID(i.getId());
                this.orderPublisher.publishEvent(new orderDoneEvent(this, order));
                return "Order" + i.getId() + " is done!";

            }
        }
        return "No orders!";
    }

    public String checkOrder () {
        ArrayList<Order> orders = ordersCollection.getOrdersByUsername(user.getUsername());

        if (orders.size() == 0) {
            return "You dont have any orders";
        } else {
            String str = "Your order(s):";
            for (Order i : orders) {
                str += i.getId() + " ";
                for (String pizza : i.getPizzas()) {
                    str += pizza + " ";
                }
                str += i.getPrice() + " ";
                if (i.isDone()) {
                    str += "is done!";
                }
                str += "\n";
            }
            return str;
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {

            while (user == null) {
                sc = new Scanner(System.in);
                System.out.println("Please write username");
                String username = sc.nextLine();
                System.out.println("Please write password");
                sc = new Scanner(System.in);
                String password = sc.nextLine();
                login(username, password);
            }

            if (user.isAdmin()) {
                Integer choise = -1;
                while (choise != 0) {

                    System.out.println("Please enter 1 to execute order");
                    System.out.println("Please enter 2 to create new pizza");
                    System.out.println("Please enter 3 to update new pizza");
                    System.out.println("Please enter 0 to exit");

                    choise = sc.nextInt();

                    switch (choise) {
                        case 1:
                            System.out.println(doOrder());
                            break;
                        case 2:
                            System.out.println("Please enter name of new pizza");
                            sc = new Scanner(System.in);
                            String name = sc.nextLine();
                            System.out.println("Please enter price");
                            Double price = sc.nextDouble();
                            Pizza newPiz = new Pizza(name, price);
                            pizzasCollection.insert(newPiz);

                            newPizza.publishEvent(new newPizza(this, newPiz));
                            break;
                        case 3:
                            ArrayList<Pizza> pizzas = pizzasCollection.getPizzas();
                            for (Pizza i: pizzas) {
                                System.out.println(i.getName() + " price: " + i.getPrice() + "\n" );
                            }
                            System.out.println("Please enter name of pizza which you should update");
                            sc = new Scanner(System.in);
                            name = sc.nextLine();
                            System.out.println("Please enter new price");
                            price = sc.nextDouble();
                            pizzasCollection.update( new Pizza(name, price));
                            break;
                    }
                }

            } else {

                Integer choise = -1;
                while (choise != 0) {

                    System.out.println("Please enter 1 to make order");
                    System.out.println("Please enter 2 to check order status");
                    System.out.println("Please enter 0 to exit");
                    choise = sc.nextInt();

                    switch (choise) {
                        case 1:
                            ArrayList<Pizza> pizzas = pizzasCollection.getPizzas();
                            ArrayList<String> pizzaNames = new ArrayList<>();
                            for (Pizza i: pizzas) {
                                System.out.println(i.getName() + " price: " + i.getPrice() + "\n" );
                            }
                            String name = "start";
                            System.out.println("Please enter name of pizza ");
                            System.out.println("Write 0, if you finish");
                            while (!name.equals("0") ) {
                                sc = new Scanner(System.in);
                                name = sc.nextLine();
                                for (Pizza i: pizzas) {
                                    if (name.equals(i.getName())) {
                                        pizzaNames.add(i.getName());
                                    }
                                }
                            }
                            MakeOrder(pizzaNames);
                            break;

                        case 2:
                            System.out.println(checkOrder());
                            break;
                    }
                }
            }
            user = null;
        }
    }

    public User getUser() {
        return user;
    }

    public OrdersCollection getOrdersCollection() {
        return ordersCollection;
    }

    public PizzasCollection getPizzasCollection() {
        return pizzasCollection;
    }

    public UsersCollection getUsersCollection() {
        return usersCollection;
    }

}

















