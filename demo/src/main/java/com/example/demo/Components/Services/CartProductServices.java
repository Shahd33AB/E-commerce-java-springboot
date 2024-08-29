package com.example.demo.Components.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Components.CartProduct;
import com.example.demo.Components.Repository.CartProductRepository;

@Service
public class CartProductServices {
@Autowired
    private CartProductRepository cartProductRepository;

    public List <CartProduct> getCartProducts(){
        List<CartProduct> result = new ArrayList<CartProduct>();
        cartProductRepository.findAll().forEach(result::add);
        return result;
    }

    public CartProduct addCartProduct(CartProduct cartProduct){
        cartProductRepository.save(cartProduct);
        return cartProduct;
    }

    public CartProduct updateCartProduct(CartProduct cartProduct){
        return cartProductRepository.save(cartProduct);
    }

    public boolean deleteCartProduct(Integer id){
        cartProductRepository.deleteById(id);
        return true;
    }

    public CartProduct getCartProduct(Integer id){
        Optional <CartProduct> result = cartProductRepository.findById(id);
        return result.orElse(null);
    }

    public CartProduct getCartProductByCartidAndProductid(Integer cid, Integer pid) {
        return cartProductRepository.findByCart_idAndProduct_id(cid, pid);
    }

    public boolean deleteByCart_idAndProduct_id(Integer cid, Integer pid){
        cartProductRepository.deleteByCart_idAndProduct_id(cid, pid);
        return true;
    }

    public List<CartProduct> getByCart_id(Integer cart_id){
        return cartProductRepository.findByCart_id(cart_id);
    }
}
