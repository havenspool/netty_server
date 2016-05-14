package com.fir.deer.entity.dao;

import com.fir.deer.entity.User;

/**
 * Created by havens on 15-8-13.
 */
public interface UserDao {
    User getUser(int id);
    User getUser(String name);
    void insert(User user);
    void update(User user);
    void delete(User user);
    void deleteById(int id);
}
