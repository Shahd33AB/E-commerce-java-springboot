package com.example.demo.Components.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Components.*;
import com.example.demo.Components.Services.CartServices;
import com.example.demo.Components.Services.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("")
    public List<Cart> getCarts() {
        return cartServices.getCarts();
    }

    @PostMapping("create/{id}")
    public String createCart(@PathVariable Integer id) {
        User user = userServices.getUser(id);
        Cart cart = new Cart(user);
        cartServices.addCart(cart);
        return "Cart Added Successfully";
    }

    @PutMapping("{id}/update")
    public String inactiveCart(@PathVariable Integer id) {
        Cart cart = cartServices.getCart(id);
        cart.setActive(false);
        Cart newCart = new Cart(cart.getUser(), true);
        cartServices.updateCart(newCart);
        return "Cart has been deactivated and a new one has been created";
    }
    
    @DeleteMapping("{id}/delete")
    public String deleteCart(@PathVariable Integer id) {
        if(cartServices.deleteCart(id)){
            return "Cart Deleted Succeessfully";
        }
        return "Error in Deletion";

    }

    @GetMapping("byUser/{id}")
    public Cart getCartByUser(@PathVariable Integer id) {
        Cart cart = cartServices.getCartByUserId(id);
        return cart;
    }

    @GetMapping("activeCart/{id}")
    public Cart getCartByUserActive(@PathVariable Integer id) {
        Cart cart = cartServices.getCartByUserIdActive(id);
        return cart;
    }
    
}
