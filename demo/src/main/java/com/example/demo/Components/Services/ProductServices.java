package com.example.demo.Components.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Components.Repository.*;
import com.example.demo.Components.*;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductBYId(Integer id){
        Optional <Product> result = productRepository.findById(id);
        return result.orElse(null);
    }

    public List <Product> getProducts(){
        List<Product> result = new ArrayList<Product>();
        productRepository.findAll().forEach(result::add);   //method reference
        return result;  
    }

    public Product addProduct(Product productObj){
        productRepository.save(productObj);
        return productObj;
    }
    public boolean deleteProduct(Integer id){
        productRepository.deleteById(id);
    
        return true;
    }

    public Product updateProduct(Product productObj){
        return productRepository.save(productObj);
    }

    public Product findProduct(Integer id){
        Optional <Product> result =  productRepository.findById(id);
        return result.orElse(null);
    }

    public List<Product> getByCategory(Category category){
        List<Product> result = new ArrayList<Product>();
        productRepository.findByCategory(category).forEach(result::add);  
        return result;  
    }
}
