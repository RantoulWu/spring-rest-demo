package com.mercury.SpringBootRESTDemo.dao;

import com.mercury.SpringBootRESTDemo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
