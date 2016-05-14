package com.fir.deer.service;

import com.fir.deer.ErrorCode;
import com.fir.deer.Service;
import com.fir.deer.db.redis.RedisClient;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.UserDao;
import com.fir.deer.entity.dao.cache.UserCacheDao;
import com.fir.deer.message.Message;
import com.fir.deer.message.MessageHelper;
import com.fir.deer.server.WorldManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class UserLoginService extends Service {
    @Override
    public void filter(Map map) throws Exception {
        String userName=(String)map.get("userName");
        String userPwd=(String)map.get("userPwd");
        String version=(String)map.get("version");
        String channel=(String)map.get("channel");

        if(userName==""){
            write(MessageHelper.cmd_error("user_login", false, ErrorCode.USER_NOT_EXIST));
            return;
        }

        WorldManager worldManager = WorldManager.getInstance();
        UserDao userDao = worldManager.dbFactory().userDao();
        User user = userDao.getUser(userName);
        if (user == null || !user.passwd.equals(userPwd)) {
            write(MessageHelper.cmd_error("user_login", false, ErrorCode.PASS_ERROR));
            return;
        }
        long curTime = System.currentTimeMillis()/1000;
        user.logintime = curTime+"";
        RedisClient.set("l_" + user.id, user.id + "," + user.logintime);
        write(user_login("user_login",user));
    }

    public static Message user_login(String cmd,User user) {
        HashMap<String, Serializable> msg_Obj = new HashMap<String, Serializable>();
        msg_Obj.put("cmd", cmd);
        msg_Obj.put("isSuccess", true);
        msg_Obj.put("erroeCode", 0);
        HashMap<String, Serializable> userInfo = new HashMap<String, Serializable>();
        if(user!=null){
            userInfo.put("userid",user.id);
            userInfo.put("username",user.name);
            userInfo.put("passwd",user.passwd);
            userInfo.put("registertime",user.registertime);
            userInfo.put("logintime",user.logintime);
            userInfo.put("userstate",user.userstate);
            userInfo.put("channel",user.channel);
            userInfo.put("unlocktime",user.unlocktime);
        }
        msg_Obj.put("userInfo",userInfo);
        return  Message.newInstance(cmd,msg_Obj);
    }
}
