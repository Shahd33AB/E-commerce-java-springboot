package com.example.demo.Components.Repository;

import com.example.demo.Components.*;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findByEmail(String email);

    User findByPhone(String phone);
}
