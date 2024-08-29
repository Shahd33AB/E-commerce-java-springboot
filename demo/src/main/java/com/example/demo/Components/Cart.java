package com.example.demo.Components;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Cart")
public class Cart {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean active = true;

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    public Cart(User user, boolean active) {
        this.user = user;
        this.active = active;
    }

    public Cart(int id, User user, boolean active) {
        this.id = id;
        this.user = user;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    

}