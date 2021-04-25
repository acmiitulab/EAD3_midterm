package com.example.demo.Service;

import com.example.demo.Models.Orders;

import java.util.List;

public interface IOrderService {

    List<Orders> getAll();

    List<Orders> getByUser(String username);

    Orders getByID(int id);

    void DeleteByID(long id);

    Orders update(Orders orders);

}
