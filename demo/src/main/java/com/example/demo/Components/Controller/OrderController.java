package com.example.demo.Components.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Components.Cart;
import com.example.demo.Components.CartProduct;
import com.example.demo.Components.Order;
import com.example.demo.Components.Product;
import com.example.demo.Components.User;
import com.example.demo.Components.Services.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

     @Autowired
    private OrderServices orderService;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private UserServices userServices;

    @Autowired 
    private CartProductServices cartProduct;

    @Autowired
    private CartController cartController;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public List<Order> getOrdersByUser(@PathVariable Integer id) {
        return orderService.getByUserId(id);
    }

    @PostMapping("/{userId}/create")
    // http://localhost:8080/orders/{userId}/create
    public String saveOrder(@PathVariable Integer userId) {
        LocalDate orderDate = LocalDate.now();
        float totalAmount = 0;

        Cart cart = cartServices.getCartByUserIdActive(userId);

        List<CartProduct> CartProducts = cartProduct.getByCart_id(cart.getId());

        for (CartProduct cartProduct : CartProducts) {
            Product prod = cartProduct.getProduct();
            totalAmount += (cartProduct.getQuantity()*prod.getPrice());
        }

        if (totalAmount>1000) {
            totalAmount = totalAmount - (totalAmount/10);
        }

        User user = userServices.getUser(userId);

        if (user == null) {
            return "User not found with ID: " + userId;
        }


        Order order = new Order(orderDate, totalAmount, cart, user);
        orderService.createOrder(order);

        String cartMessage = cartController.inactiveCart(cart.getId());

        return "Order created successfully " + cartMessage;
    }

    @PutMapping("/{id}/update")
    // http://localhost:8080/orders/{id}/update
    public String updateOrder(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder != null) {
            existingOrder.setOrderDate(LocalDate.parse(request.get("orderDate")));
            existingOrder.setTotalAmount(Float.parseFloat(request.get("totalAmount")));

            orderService.updateOrder(existingOrder);
            return "Order updated successfully";
        } else {
            return "Order not found";
        }
    }

    @DeleteMapping("/{id}/delete")
    // http://localhost:8080/orders/{id}/delete
    public String deleteOrder(@PathVariable Integer id) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder != null) {
            orderService.deleteOrder(id);
            return "Order deleted successfully";
        } else {
            return "Order not found";
        }
    }

    @GetMapping("/{id}/view")
    // http://localhost:8080/orders/{id}/view
    public Order findOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
}
