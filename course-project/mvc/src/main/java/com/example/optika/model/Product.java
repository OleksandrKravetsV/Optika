package com.example.optika.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "заповніть поле")
    private String productName;

    @NotNull(message = "заповніть поле")
    @Min(value = 0)
    private Integer price;

    @NotEmpty(message = "заповніть поле")
    private String description;

    private String filename;

    public Product() {
    }

    public Product(String productName, Integer price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
