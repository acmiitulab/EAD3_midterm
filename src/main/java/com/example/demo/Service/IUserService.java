package com.example.demo.Service;

import com.example.demo.Models.User;

import java.util.List;

public interface IUserService {

    User getById(int id);

    User getByUsername(String username);

    Boolean isAdmin(String username, String pasw);

    Boolean isUser(String username, String pasw);

    void update(User user);

    List<User> getAllUsersBy();

    void DeleteByID(long id);

    boolean checkByLoginAndPassword(String login, String password);
}
