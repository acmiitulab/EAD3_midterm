package com.example.demo.Service;

import com.example.demo.Models.Orders;
import com.example.demo.Repositories.OrderRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRep orderRep;


    @Override
    public List<Orders> getAll() {
        return orderRep.findAll();
    }

    @Override
    public List<Orders> getByUser(String username) {
        List<Orders> orders = orderRep.findAll();
        ArrayList<Orders> response = new ArrayList<>();
        for (Orders i : orders) {
            if (i.getUsername().equals(username)) {
                response.add(i);
            }
        }
        return response;

    }

    @Override
    public Orders getByID(int id) {
        return orderRep.getById(id);
    }

    @Override
    public void DeleteByID(long id) {
        orderRep.deleteById(id);
    }

    @Override
    public Orders update(Orders orders) {
        return orderRep.saveAndFlush(orders);
    }

}
