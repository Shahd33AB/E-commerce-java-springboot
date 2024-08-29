package com.example.demo.Components.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Components.Cart;
import com.example.demo.Components.CartProduct;
import com.example.demo.Components.Services.CartProductServices;
import com.example.demo.Components.Services.CartServices;
import com.example.demo.Components.*;
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
@RequestMapping("cartProduct")
public class CartProductController {

    @Autowired
    private CartProductServices cartProductServices;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private ProductServices productServices;

    @GetMapping("")
    public List<CartProduct> geCartProducts()
    {
        return cartProductServices.getCartProducts();
    }

    @GetMapping("cart/{id}")
    public List<CartProduct> getByCartId(@PathVariable Integer id){
        return cartProductServices.getByCart_id(id);
    }

    @PostMapping("{uid}/add/{pid}")
    public String addCartProduct(@PathVariable Integer uid,
                                @PathVariable Integer pid) {
        Cart cart = cartServices.getCartByUserIdActive(uid);
        Product product = productServices.getProductBYId(pid);
        Integer q = 1;
        CartProduct cartProduct = new CartProduct(cart, product, q);
        cartProductServices.addCartProduct(cartProduct);
        return "Product Added Successfully To Cart";
    }

    @PutMapping("{cid}/increase/{pid}")
    public String icreaseQuantity(@PathVariable Integer cid ,@PathVariable Integer pid ) {
        CartProduct cartProduct = cartProductServices.getCartProductByCartidAndProductid(cid, pid);
        int q = cartProduct.getQuantity();
        q++;
        cartProduct.setQuantity(q);
        cartProductServices.updateCartProduct(cartProduct);
        return "Quantity Increased By 1";
    }
    
    @PutMapping("{cid}/decrease/{pid}")
    public String decreaseQuantity(@PathVariable Integer cid, @PathVariable Integer pid) {
        CartProduct cartProduct = cartProductServices.getCartProductByCartidAndProductid(cid, pid);
        int q = cartProduct.getQuantity();
        q--;
        cartProduct.setQuantity(q);
        cartProductServices.updateCartProduct(cartProduct);
        return "Quantity Decreased By 1";
    }

    @DeleteMapping("{cid}/delete/{pid}")
    public String deleteFromCart(@PathVariable Integer cid, @PathVariable Integer pid){
        cartProductServices.deleteByCart_idAndProduct_id(cid, pid);
        return "Deleted Successfully";
    }
}
