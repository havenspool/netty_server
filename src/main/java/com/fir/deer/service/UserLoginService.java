package com.fir.deer.service;

import com.fir.deer.ErrorCode;
import com.fir.deer.Service;
import com.fir.deer.db.redis.RedisClient;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.UserDao;
import com.fir.deer.message.Message;
import com.fir.deer.message.MessageHelper;
import com.fir.deer.server.WorldManager;
import org.json.JSONObject;

/**
 * Created by havens on 15-8-11.
 */
public class UserLoginService extends Service {
    @Override
    public void filter(JSONObject jObject) throws Exception {
        String userName=(String)jObject.get("userName");
        String userPwd=(String)jObject.get("userPwd");
        String version=(String)jObject.get("version");
        String channel=(String)jObject.get("channel");

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
        user.logintime = curTime;
        RedisClient.set("l_" + user.id, user.id + "," + user.logintime);
        write(user_login("user_login",user));
    }

    public static String user_login(String cmd,User user) {
        JSONObject jObject=new JSONObject();
        jObject.put("cmd", cmd);
        jObject.put("isSuccess", true);
        jObject.put("erroeCode", 0);
        JSONObject userInfo=new JSONObject();
        if(user!=null){
            userInfo.put("userid",user.id);
            userInfo.put("username",user.name);
            userInfo.put("passwd",user.passwd);
            userInfo.put("registertime",user.registertime+"");
            userInfo.put("logintime",user.logintime+"");
            userInfo.put("userstate",user.userstate);
            userInfo.put("channel",user.channel);
            userInfo.put("unlocktime",user.unlocktime+"");
        }
        jObject.put("userInfo",userInfo);
        return  Message.newInstance(cmd,jObject);
    }
}
