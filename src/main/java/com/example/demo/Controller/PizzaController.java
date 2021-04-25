package com.example.demo.Controller;


import com.example.demo.Models.Pizza;
import com.example.demo.Models.User;
import com.example.demo.Service.IOrderService;
import com.example.demo.Service.IPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private IPizzaService pizzaService;


    @GetMapping("")
    public List<Pizza> getTakes() {
        return pizzaService.getALl();
    }

    @GetMapping("/{name}")
    public Pizza getPizzaByID (@PathVariable("name") String name) {
        return pizzaService.getByName(name);
    }

    @DeleteMapping("/delete")
    public void deleteTakesByID(@RequestBody Pizza pizza) {
        pizzaService.delete(pizza);
    }


    @PostMapping("/create")
    public void createTakes(@RequestBody Pizza pizza) {
        pizzaService.update(pizza);
    }

    @PutMapping("/update")

    public void updateTakes(@RequestBody Pizza pizza) {
        pizzaService.update(pizza);
    }


}
