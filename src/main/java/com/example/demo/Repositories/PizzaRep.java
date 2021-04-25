package com.example.demo.Repositories;

import com.example.demo.Models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRep extends JpaRepository<Pizza, Long> {

    Pizza  getByName (String name);

}
