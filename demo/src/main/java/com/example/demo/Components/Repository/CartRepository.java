package com.example.demo.Components.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Components.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{
    Cart findByUser_id(int user_id);

    @Query(value = "Select * from cart where user_id = ?1 AND active = 1" , nativeQuery = true)
    Cart findByUser_idAndActive(int user_id);
}
