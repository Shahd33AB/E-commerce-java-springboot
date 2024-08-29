package com.example.demo.Components.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Components.*;
import com.example.demo.Components.Repository.CartRepository;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;

    public List <Cart> getCarts(){
        List<Cart> result = new ArrayList<Cart>();
        cartRepository.findAll().forEach(result::add);
        return result;
    }

    public Cart addCart(Cart cart){
        cartRepository.save(cart);
        return cart;
    }

    public Cart updateCart(Cart cart){
        return cartRepository.save(cart);
    }

    public boolean deleteCart(Integer id){
        cartRepository.deleteById(id);
        return true;
    }

    public Cart getCart(Integer id){
        Optional <Cart> result = cartRepository.findById(id);
        return result.orElse(null);
    }

    public Cart getCartByUserId(Integer id){
        Cart result = cartRepository.findByUser_id(id);
        return result;
    }

    public Cart getCartByUserIdActive(Integer id){
        Cart result = cartRepository.findByUser_idAndActive(id);
        return result;
    }
}