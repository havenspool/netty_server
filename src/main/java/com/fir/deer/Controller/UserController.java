package com.fir.deer.Controller;

import com.fir.deer.entity.Role;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.DBFactory;
import com.fir.deer.entity.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by havens on 15-8-13.
 */
public class UserController {
    public User user;
    public UserController(User user) {
        this.user = user;
    }

    private DBFactory dbFactory;

    public void initDAO(DBFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    private UserDao userDao = null;
    public UserDao userDao() {
        if (userDao == null) {
            userDao = dbFactory.userDao();
        }
        return userDao;
    }

    public List<Role> getRoles(int serverId){
        List<Role> roleList=new ArrayList<Role>();

        return roleList;
    }

}
