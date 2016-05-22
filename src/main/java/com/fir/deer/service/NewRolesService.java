package com.fir.deer.service;

import com.fir.deer.ErrorCode;
import com.fir.deer.db.DBException;
import com.fir.deer.dfa.DFAOutCallBack;
import com.fir.deer.dfa.DfaTool;
import com.fir.deer.entity.Role;
import com.fir.deer.entity.dao.RoleDao;
import com.fir.deer.message.Message;
import com.fir.deer.message.MessageHelper;
import com.fir.deer.server.WorldManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

/**
 * Created by havens on 16-5-14.
 */
public class NewRolesService extends UserService{
    public static class MyDFAOutCallBack implements DFAOutCallBack {
        public Set<String> words = new HashSet<String>();
        public void CallBack(String keyword) {
            words.add(keyword);
        }
    }

    @Override
    public void filter(JSONObject jObject) throws Exception {
        int userID=(Integer) jObject.get("userID");
        int serverID=(Integer) jObject.get("serverID");
        String roleName=(String) jObject.get("roleName");
        int headImage=(Integer) jObject.get("headImage");

        MyDFAOutCallBack myDFAOutCallBack = new MyDFAOutCallBack();
        DfaTool.removeKeyword.Search(roleName, myDFAOutCallBack);
        if (myDFAOutCallBack.words.size()>0) {
            write(MessageHelper.cmd_error("new_role", false, ErrorCode.CREATE_ROLE_NOT_PERMISSION));
            return;
        }

        WorldManager worldManager = WorldManager.getInstance();
        RoleDao roleDao = worldManager.dbFactory().roleDao();
        Role role = roleDao.createHero(userID, serverID,roleName, headImage);
        if(role==null){
            write(MessageHelper.cmd_error("new_role", false, ErrorCode.ROLENAME_EXIST));
            return;
        }

        role.userCtrl = userCtrl;
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


    public static void main(String[] args) {
        System.out.println("load...");
        DfaTool.loadJsonData();
        String mm = "来fuck";
        MyDFAOutCallBack myDFAOutCallBack = new MyDFAOutCallBack();
        DfaTool.removeKeyword.Search(mm, myDFAOutCallBack);
        System.out.println(myDFAOutCallBack.words.size());
        for (String keyword : myDFAOutCallBack.words) {
            System.out.println(mm);
            mm = mm.replaceAll(keyword, "*");
        }
        System.out.println(mm);
    }
}
