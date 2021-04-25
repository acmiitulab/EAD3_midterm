package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRep userRep;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean checkByLoginAndPassword(String login, String password) {
        return userRep.existsUserByUsernameAndPassword(login, password);
    }

    public User getById (int id) {
        return userRep.getById(id);
    }

    public Boolean isAdmin (String username, String pasw) {
        User user = userRep.getByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(pasw) && user.isAdmin();

    }

    public Boolean isUser (String username, String pasw) {
        User user = userRep.getByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(pasw) ;
    }

    public User getByUsername(String username) {
        return userRep.getByUsername(username);
    }

    public void update (User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.saveAndFlush(user);
    }

    public List<User> getAllUsersBy() {
        return userRep.getUsersBy();
    }

    public void DeleteByID(long id) {
        userRep.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<User> member = userRep.findAll();
        User user = null;
        
        for (User usr : member) {
            if (usr.getUsername().equals(login)) {
                user = usr;
            }
        }
 
        if (user == null) {
            throw new UsernameNotFoundException("User by login=" + login + " not found!");
        }
        return user;
    }


}