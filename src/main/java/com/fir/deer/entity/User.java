package com.fir.deer.entity;

import com.fir.deer.db.DBObject;

/**
 * Created by havens on 15-8-10.
 */
public class User extends DBObject {
    public String table_name="users";
    public long id;
    public String name;
    public String passwd;

    public long registertime;
    public long logintime;
    public int userstate;
    public String channel;
    public long unlocktime;

    public static void main(String[] args) {
        User user=new User();
        user.id=10001;
        user.name="havens";
        user.passwd="123456";
        System.out.println(user.toString());
    }

}
