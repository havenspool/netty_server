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
        System.out.println(jObject.toString());
        String userName=(String)jObject.get("userName");
        String userPwd=(String)jObject.get("userPwd");
//        int version=(Integer) jObject.get("version");
        String channel=(String)jObject.get("channel");

        if(userName==""){
            write(MessageHelper.cmd_error("user_login", false, ErrorCode.USER_NOT_EXIST));
            return;
        }

        WorldManager worldManager = WorldManager.getInstance();
        UserDao userDao = worldManager.dbFactory().userDao();
        User user = userDao.getUser(userName);
        long curTime = System.currentTimeMillis()/1000;
        if(user == null){
            user=new User();
            user.name=userName;
            user.passwd=userPwd;
            user.channel=channel;
            user.registerTime=curTime;
            user.loginTime=curTime;
            user.userState=1;
            user.unlockTime=0;
            userDao.insert(user);
        }else{
            if (userPwd==null||(!userPwd.equals(user.passwd))) {
                write(MessageHelper.cmd_error("user_login", false, ErrorCode.PASS_ERROR));
                return;
            }
            user.loginTime = curTime;
            System.out.println(user);
//            userDao.update(user);
        }

        RedisClient.set("l_" + user.id, user.id + "," + user.loginTime);
        write(user_login("user_login",user));
    }

    public static String user_login(String cmd,User user) {
        JSONObject jObject=new JSONObject();
        jObject.put("cmd", cmd);
        jObject.put("isSuccess", true);
        jObject.put("erroeCode", 0);
        JSONObject userInfo=new JSONObject();
        if(user!=null){
            userInfo.put("userId",user.id);
            userInfo.put("userName",user.name);
            userInfo.put("passwd",user.passwd);
            userInfo.put("registerTime",user.registerTime);
            userInfo.put("loginTime",user.loginTime);
            userInfo.put("userState",user.userState);
            userInfo.put("channel",user.channel);
            userInfo.put("unlockTime",user.unlockTime);
        }
        jObject.put("userInfo",userInfo);
        return  Message.newInstance(cmd,jObject);
    }
}
