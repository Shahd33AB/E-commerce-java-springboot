package com.example.demo.Components.Repository;

import com.example.demo.Components.*;


import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Integer>{

    List<Product> findByCategory(Category category);
}
