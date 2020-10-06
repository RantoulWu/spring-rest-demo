package com.mercury.SpringBootRESTDemo.bean;

import javax.persistence.*;
import java.util.Set;

// add data source to check if table and columns are right

@Entity
@Table(name = "MSI_PRODUCT")
public class Product {
    @Id
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCK")
    private int stock;
    @Column(name = "IMAGE")
    private String image;

//    @OneToMany(mappedBy = "product")
//    private Set<Purchase> purchases;

    public Product() {
    }

    public Product(Long id, String name, String brand, int price, int stock, String image) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
