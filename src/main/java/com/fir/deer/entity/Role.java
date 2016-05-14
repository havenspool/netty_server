package com.fir.deer.entity;

import com.fir.deer.Controller.UserController;
import com.fir.deer.db.DBObject;

/**
 * Created by havens on 15-8-11.
 */
public class Role extends DBObject {
    public String table_name="roles";
    public int id;
    public long userid;
    public int serverid;
    public String rolename;
    public int level;
    public int exp;
    public int gold;
    public String createtime;
    public String logintime;
    public int consecutivedays;
    public byte rolestate;
    public int headimage;

    public UserController userCtrl;
}
