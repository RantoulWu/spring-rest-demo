package com.mercury.SpringBootRESTDemo.controller;

import com.mercury.SpringBootRESTDemo.bean.Product;
//import com.mercury.SpringBootRESTDemo.dao.ProductDao;
import com.mercury.SpringBootRESTDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController  //@RestController =  @ResponseBody + @Controller
@RequestMapping(value = "/products")

public class ProductController {
    @Autowired
    // ProductDao productDao;
            ProductService productService;


    //@RequestMapping(method = RequestMethod.GET)
    //GET/products
    //GET/products?name=iPhone5   从路径匹配的角度是一样的！
    //GET/products?minPrice=100&stock =300
    //Get/products?page=2&size=3
    // request query parameter
    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {
        if (name != null) {
            return productService.getProductsByName(name);
        }
        if (page != null && size != null) {
            return productService.getProductsByPage(page, size);
        }
        return productService.getAllProducts();
    }

    //GET/products/6
    @GetMapping(path = "/{id}")   //{variable_name}
    //@PathVariable("variable_name") long methodParam
    // get the "variable_name" from path and assign it to methodParam
    public Product getProduct(@PathVariable("id") long id) {

        return productService.getProductById(id);
    }
    @PostMapping
    public boolean putProduct(){
       // return productService.addProduct();
        return false;
    }


}
