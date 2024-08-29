package com.example.demo.Components.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Components.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

    List<Order> findByUser_id(int user_id);

}
