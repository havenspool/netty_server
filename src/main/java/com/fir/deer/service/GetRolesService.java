package com.fir.deer.service;

import com.fir.deer.entity.Role;
import com.fir.deer.message.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by havens on 16-5-14.
 */
public class GetRolesService extends UserService{
    @Override
    public void filter(JSONObject jObject) throws Exception {
        int serverID=(Integer) jObject.get("serverID");
        write(get_roles("get_roles",userCtrl.getRoles(serverID)));
    }

    public static String get_roles(String cmd, List<Role> roles) {
        JSONObject jObject=new JSONObject();
        jObject.put("cmd", cmd);
        jObject.put("isSuccess", true);
        jObject.put("erroeCode", 0);
        JSONArray roleList = new JSONArray();
        for(Role role: roles){
            JSONObject map=new JSONObject();
            if(role!=null){
                map.put("id",role.id);
                map.put("userid",role.userid);
                map.put("rolename",role.rolename);
                map.put("level",role.level);
                map.put("exp",role.exp);
                map.put("gold",role.gold);
                map.put("createtime",role.createtime+"");
                map.put("logintime",role.logintime+"");
                map.put("consecutivedays",role.consecutivedays);
                map.put("rolestate",role.rolestate);
                map.put("headimage",role.headimage);
                roleList.put(map);
            }
        }
        jObject.put("roleInfo",roleList.toString());
        return  Message.newInstance(cmd,jObject);
    }
}