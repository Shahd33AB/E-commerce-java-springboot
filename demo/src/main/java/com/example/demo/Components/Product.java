package com.example.demo.Components;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
public class Product {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotNull
        private int id;
        @NotNull
        @NotBlank
        private String name;
        @NotNull
        @NotBlank
        private String description;
        @NotNull
        private int quantity;
        @NotNull
        @NotBlank
        private String image;
        @NotNull
        private float price;
        @ManyToOne
        @JoinColumn(name = "category_id") 
        private Category category;
        
        public Product(){
    
    
        }
    
        public Product( String name, String description,int quantity,String image,float price){
            this.description = description;
            this.name = name;
            this.quantity = quantity;
            this.image = image;
            this.price = price;
        }
       
        public Product( Integer id, String name, String description,int quantity,String image,float price){
            this.id = id;
            this.description = description;
            this.name = name;
            this.quantity = quantity;
            this.image = image;
            this.price = price;
        }
        public Product( Integer id, String name, String description,int quantity,Category category,String image,float price){
            this.id = id;
            this.description = description;
            this.name = name;
            this.quantity = quantity;
            this.category=category;
            this.image = image;
            this.price = price;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setCategory(Category category) {
            this.category = category;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public String getDescription() {
            return description;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getQuantity() {
            return quantity;
        }
        public Category getCategory() {
            return category;
        }

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}
    
}