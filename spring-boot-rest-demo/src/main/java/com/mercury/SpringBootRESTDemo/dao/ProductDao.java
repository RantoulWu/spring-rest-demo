package com.mercury.SpringBootRESTDemo.dao;

import com.mercury.SpringBootRESTDemo.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JpaRepository<T,R> use generic to generate different Dao for different class
// findById(R id) need primary key type R
// spring data jpa will create below class
// @Repository
// class ProductDaoImpl implements ProductDao
public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findByName(String name); //jpa自动解析生成方法！

    List<Product> findByPriceLessThan(int price);

    //how to find product that its price > 100 and stock = 300
    //custom query : JPQL -> SQL
    @Query("select p from Product p where p.price > :minPrice and p.stock = :stock")
    List<Product> getProducts(@Param("minPrice") int minPrice, @Param("stock") int stock);

}
