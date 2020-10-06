package com.mercury.SpringBootRESTDemo.service;

import com.mercury.SpringBootRESTDemo.bean.Product;
//import com.mercury.SpringBootRESTDemo.dao.ProductDao;
import com.mercury.SpringBootRESTDemo.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

//@Conponent
@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> getProductsByName(String name) {
        return productDao.findByName(name);
    }

    //first time exe will hit DB and load products and save them
    // then later exe will directly return products from cache!
    @Cacheable(cacheNames = "products")
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public List<Product> getProductsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); //page start from 0
        return productDao.findAll(pageable).toList();
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public Product getProductById(long id) {
        // findById return Optional
        // getOne
        return productDao.findById(id).orElse(new Product());
    }

    //changes to product should update cache as well
    @CacheEvict(cacheNames = "products")
    public void deleteProduct() {

    }

    @CacheEvict(cacheNames = "products")
    public void addProduct() {
       // return productDao.addProduct();
    }

    @CacheEvict(cacheNames = "products")
    @CachePut(value = "product", key = "#product.id") // ??
    public void updateProduct() {

    }

}
