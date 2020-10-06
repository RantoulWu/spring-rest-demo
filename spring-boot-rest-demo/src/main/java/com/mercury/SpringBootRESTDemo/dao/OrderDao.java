package com.mercury.SpringBootRESTDemo.dao;

import com.mercury.SpringBootRESTDemo.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
