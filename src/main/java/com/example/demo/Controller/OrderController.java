package com.example.demo.Controller;

import com.example.demo.Models.Orders;
import com.example.demo.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public List<Orders> getOrder() {
        return orderService.getAll();
    }

    @GetMapping("/id")
    public Orders getOrderByID (@PathVariable("id") int id) {
        return orderService.getByID(id);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@PathVariable("id") int id) {
        orderService.DeleteByID(id);
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody Orders orders) {
        orderService.update(orders);
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody Orders orders) {
        orderService.update(orders);
    }

}