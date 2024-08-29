package com.example.demo.Components.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Components.CartProduct;


public interface CartProductRepository extends CrudRepository<CartProduct, Integer>{

    CartProduct findByCart_idAndProduct_id(int cart_id, int product_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_product WHERE cart_id = ?1 AND product_id = ?2" , nativeQuery = true)
    void deleteByCart_idAndProduct_id(int cart_id, int product_id);

    List<CartProduct> findByCart_id(int cart_id);
}
