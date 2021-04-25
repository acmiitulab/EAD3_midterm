package com.example.demo.Controller;


import com.example.demo.Models.User;
import com.example.demo.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsersBy();
    }

    @GetMapping("/{id}")
    public User getAllUsers(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable("id") long id) {
         userService.DeleteByID(id);
    }


    @PostMapping("")
    public void createUser( @RequestBody User user) {

        userService.update(user);

    }

    @PutMapping("")
    public void updateUser( @RequestBody User user) {

        userService.update(user);

    }



}
