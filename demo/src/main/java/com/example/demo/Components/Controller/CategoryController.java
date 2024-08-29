package com.example.demo.Components.Controller;

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

import com.example.demo.Components.*;
import com.example.demo.Components.Services.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private  CategoryServices categoryService;  //bean

    @GetMapping("")
    //localhost:8080/category
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("create")
    //localhost:8080/category/create
    public String saveCategory(@RequestBody Map <String, String> request) {
        Category categoryObj = new Category( request.get("name"), request.get("description"));
        categoryService.addCategory(categoryObj);
        return "done";
    }
    @CrossOrigin(origins = "*")

    @PutMapping("/{id}/update")
    //localhost:8080/category/2/update
    public Category updateCategory(@PathVariable Integer id, @RequestBody Map <String, String> request) {
        Category topicObj = new Category( id, request.get("name"), request.get("description"));

        Category result = categoryService.updateCategory(topicObj);
        return result;
    }

    @CrossOrigin(origins = "*")

    @DeleteMapping("{id}/delete")
    public String deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return "Deleted successfully";
    }


    @GetMapping("{id}/view")
    public Category findCategory(@PathVariable Integer id) {
        return categoryService.findCategory(id);
    }

}
