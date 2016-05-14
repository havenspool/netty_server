package com.fir.deer.entity.dao;

/**
 * Created by havens on 15-8-13.
 */
public interface DBFactory {
    void init();
    UserDao userDao();
    RoleDao roleDao();
}
