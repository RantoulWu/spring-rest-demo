package com.mercury.SpringBootRESTDemo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "MSI_ORDER_PRODUCT")
public class Purchase {
    @Id
    private long id;
    @Column
    private long qty;
//    @Column
//    private long order_id;
//    @Column
//    private long product_id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore //avoid circular reference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Purchase() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

//    public long getOrder_id() {
//        return order_id;
//    }
//
//    public void setOrder_id(long order_id) {
//        this.order_id = order_id;
//    }
//
//    public long getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(long product_id) {
//        this.product_id = product_id;
//    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
