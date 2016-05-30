package com.fir.deer.entity.dao;

import com.fir.deer.db.DBException;
import com.fir.deer.entity.User;

/**
 * Created by havens on 15-8-13.
 */
public abstract interface UserDao {
    User getUser(int id);
    User getUser(String name);
    void insert(User user) throws DBException;
    void update(User user) throws DBException;
    void delete(User user) throws DBException;
    void deleteById(int id) throws DBException;
}
