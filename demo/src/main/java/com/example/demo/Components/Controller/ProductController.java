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
@RequestMapping("product")
public class ProductController {
     @Autowired
    private  ProductServices productService;  //bean
    @Autowired
    private  CategoryServices categoryService; 

    @CrossOrigin(origins = "*")
    @GetMapping("")
    //localhost:8080/topic
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("create")
    //localhost:8080/topic/create
    public String saveProduct(@RequestBody Map <String, String> request) {
        Product productObj = new Product( request.get("name"),
         request.get("description"),Integer.parseInt(request.get("quantity")),
         request.get("image"),Integer.parseInt(request.get("price")));
         
        productService.addProduct(productObj);
        return "done";
    }
    @CrossOrigin(origins = "*")
    @PostMapping("createByCategory")
    public String saveProductById(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String description = request.get("description");
        int quantity = Integer.parseInt(request.get("quantity"));
       int categoryId = Integer.parseInt(request.get("categoryId")); // Assuming categoryId is provided in the request
    
        // Retrieve the category from the database using its ID
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            return "Category not found";
        }
        Product productObj = new Product();
        productObj.setName(name);
        productObj.setDescription(description);
        productObj.setQuantity(quantity);
        productObj.setPrice(Integer.parseInt(request.get("price")));
        productObj.setImage(request.get("image"));
        
        // Set the category obtained from the database
        productObj.setCategory(category);
    
        // Save the product
        productService.addProduct(productObj);
    
        return "done";
    }

    @PutMapping("/update") 
    //localhost:8080/topic/2/update
    public Product updateProduct(@RequestBody Map <String, String> request) {
        Category category = categoryService.getCategoryById(Integer.parseInt(request.get("categoryId")));
        Product productObj = new Product( Integer.parseInt(request.get("id")), request.get("name"), request.get("description"),Integer.parseInt(request.get("quantity")),category,request.get("image"),Integer.parseInt(request.get("price")));
        Product result = productService.updateProduct(productObj);
        return result;
    }


    @DeleteMapping("{id}/delete")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "Deleted successfully";
    }


    @GetMapping("{id}/view")
    public Product findProduct(@PathVariable Integer id) {
        return productService.findProduct(id);
    }

    @GetMapping("category/{id}")
    public List<Product> getByCategiry(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        return productService.getByCategory(category);
    }
    
}
