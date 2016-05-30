package com.fir.deer.entity;

import com.fir.deer.Controller.UserController;
import com.fir.deer.db.DBObject;
import com.fir.deer.db.IdGenerator;

/**
 * Created by havens on 15-8-11.
 */
public class Role extends DBObject {
    public String table_name="roles";
    public int id;
    public long userId;
    public int serverId;
    public String roleName;
    public int level;
    public int exp;
    public int gold;
    public long createTime;
    public long loginTime;
    public int consecutiveDays;
    public int roleState;
    public int headImage;

    public UserController userCtrl;
}
