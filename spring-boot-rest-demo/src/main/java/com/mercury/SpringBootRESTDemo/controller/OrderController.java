package com.mercury.SpringBootRESTDemo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mercury.SpringBootRESTDemo.bean.Order;
import com.mercury.SpringBootRESTDemo.dao.OrderDao;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    OrderDao orderDao;

    // @RequestMapping(method = RequestMethod.GET)
    // 对于添加了PreAuthorize 注释的代码，Spring
    // security 会添加一些额外的功能！ --- 使用了AOP！
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<Order> getOrders() {
        return orderDao.findAll();
    }

}
