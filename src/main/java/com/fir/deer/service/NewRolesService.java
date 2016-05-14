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
    public void filter(Map map) throws Exception {
        int userID=(Integer) map.get("userID");
        int serverID=(Integer) map.get("serverID");
        String roleName=(String) map.get("roleName");
        int headImage=(Integer) map.get("headImage");

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
                map.put("createtime",role.createtime);
                map.put("logintime",role.logintime);
                map.put("consecutivedays",role.consecutivedays);
                map.put("rolestate",role.rolestate);
                map.put("headimage",role.headimage);
                roleList.add(map);
            }
        }
        msg_Obj.put("roleInfo",roleList.toArray());
        return  Message.newInstance(cmd,msg_Obj);
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
