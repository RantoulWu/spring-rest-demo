package com.mercury.SpringBootRESTDemo.service;

import com.mercury.SpringBootRESTDemo.bean.User;
import com.mercury.SpringBootRESTDemo.bean.UserDetail;
import com.mercury.SpringBootRESTDemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
//business logic, data process, transaction,DAO
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    // spring transaction management -- if any exception occurs,
    // it will automatically rollback and commit!
    public boolean register(User user) {
        try {
            User existing = userDao.findByUsername(user.getUsername());
            if (existing != null) {
                return false;
            } else {
                if (user.getUserDetail() != null) {
                    user.getUserDetail().setUser(user); // associate user detail with user!
                }
                // save user's hashed password
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userDao.save(user);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // JPA transaction 不推荐使用 每个都要写autowired 和 entityManager 太麻烦！！
    //    @Autowired
    //    EntityManager entityManager; // Hibernate Session
    //    public boolean register1(User user) {
    //        EntityTransaction transaction = entityManager.getTransaction();
    //        try {
    //            transaction.begin();
    //            User existing = userDao.findByUsername(user.getUsername());
    //            if (existing != null) {
    //                transaction.rollback();
    //                return false;
    //            } else {
    //                userDao.save(user);
    //                transaction.commit();
    //                return true;
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            transaction.rollback();
    //            return false;
    //        }
    //    }
    @Transactional
    public boolean deleteUserById(long id) {
        try {
            userDao.deleteById(id); // existById()
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean updateUser(long id, User updateUser) {
        try {
            User exist = userDao.findById(id).orElse(null); // persistent status
            if (exist == null) {
                return false;
            } else {
                // replace all fields of current user with update user
                exist.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                userDao.save(exist);
                // userDao.save(user); -> user is transient , will do insert rather than update!
                //update userDetail
                UserDetail curuserDetail = exist.getUserDetail();
                curuserDetail.setPhone(updateUser.getUserDetail().getPhone());

                //update profile
                exist.setProfiles(updateUser.getProfiles());
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    // provide a method for spring security to get User from DB by userDao!
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
