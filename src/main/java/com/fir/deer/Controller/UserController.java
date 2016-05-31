package com.fir.deer.Controller;

import com.fir.deer.db.DBException;
import com.fir.deer.entity.Role;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.DBFactory;
import com.fir.deer.entity.dao.RoleDao;
import com.fir.deer.entity.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by havens on 15-8-13.
 */
public class UserController {
    public User user;
    private List<Role> roles = new ArrayList();

    public UserController(User user) {
        this.user = user;
    }

    private DBFactory dbFactory;
    private RoleDao roleDao = null;
    private UserDao userDao = null;

    public void initDAO(DBFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    public UserDao userDao() {
        if (userDao == null) {
            userDao = dbFactory.userDao();
        }
        return userDao;
    }

    public RoleDao roleDao() {
        if (roleDao == null) {
            roleDao = dbFactory.roleDao();
        }
        return roleDao;
    }

    public List<Role> getRoles(int userId){
        List<Role> list=null;
        try {
            list=roleDao.getRoles(userId);
        } catch (DBException e) {
            e.printStackTrace();
            list=new ArrayList<Role>();
        }
        return list;
    }

    public void roles(List<Role> roles){
        this.roles=roles;
    }

}
