package com.example.demo.Components.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Components.Repository.*;
import com.example.demo.Components.*;

@Service
public class CategoryServices {

    @Autowired
    private CategoryReprository category;

    public Category getCategoryById(int id) {
        Optional <Category> result =  category.findById(id);
        return result.orElse(null);
    }

    public List <Category> getCategories(){
        List<Category> result = new ArrayList<Category>();
        category.findAll().forEach(result::add);   //method reference
        return result;
    }

    public Category addCategory(Category categoryObj){
        category.save(categoryObj);
        return categoryObj;
    }

    public boolean deleteCategory(Integer id){
        category.deleteById(id);

        return true;
    }

    public Category updateCategory(Category categoryObj){
        return category.save(categoryObj);
    }

    public Category findCategory(Integer id){
        Optional <Category> result =  category.findById(id);
        return result.orElse(null);
    }
}
