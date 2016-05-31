package com.fir.deer.entity.dao;

import com.fir.deer.db.DBException;
import com.fir.deer.entity.User;

/**
 * Created by havens on 15-8-13.
 */
public abstract interface UserDao {
    User getUser(int id);
    User getUser(String name);
    void insertUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
    void deleteUser(User user) throws DBException;
    void deleteUserById(int id) throws DBException;
}
