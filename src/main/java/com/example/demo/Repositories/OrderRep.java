package com.example.demo.Repositories;

import com.example.demo.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRep extends JpaRepository<Orders, Long> {

    Orders getById (int id);


}
