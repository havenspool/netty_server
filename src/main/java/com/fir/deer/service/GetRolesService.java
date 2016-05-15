package com.fir.deer.service;

import com.fir.deer.entity.Role;
import com.fir.deer.message.Message;

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
    public void filter(Map map) throws Exception {
        int serverID=(Integer) map.get("serverID");
        write(get_roles("get_roles",userCtrl.getRoles(serverID)));
    }

    public static Message get_roles(String cmd, List<Role> roles) {
        HashMap<String, Serializable> msg_Obj = new HashMap<String, Serializable>();
        msg_Obj.put("cmd", cmd);
        msg_Obj.put("isSuccess", true);
        msg_Obj.put("erroeCode", 0);
        ArrayList<HashMap<String, Serializable>> roleList = new ArrayList<HashMap<String, Serializable>>();
        for(Role role: roles){
            HashMap<String, Serializable> map = new HashMap<String, Serializable>();
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
                roleList.add(map);
            }
        }
        msg_Obj.put("roleInfo",roleList.toArray());
        return  Message.newInstance(cmd,msg_Obj);
    }
}