package com.example.demo.Collections;

import com.example.demo.Models.Order;
import com.example.demo.Models.Pizza;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
@Component("pizza_order")
public class Pizzas_toOrder {


    public Pizzas_toOrder() {

    }

    public ArrayList<String>  getPizzasInOrder(int OrderID, Connection conn) {
        ArrayList<String> pizzas = new ArrayList<>();
        String sql = "SELECT * FROM pizza_order where  order_id = ? ";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, OrderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String pizza = resultSet.getString(2);
                pizzas.add(pizza);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return pizzas;
    }

    public int insert(Order order, Connection conn) {
        for (String q: order.getPizzas() ) {
            String sql = "INSERT INTO pizza_order (order_id, pizzaName) Values (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setString(2, q);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return 0;
    }


}
