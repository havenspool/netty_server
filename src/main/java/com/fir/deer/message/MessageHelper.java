package com.fir.deer.message;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by havens on 15-8-11.
 */
public class MessageHelper {

    public static Message cmd_error(String cmd, boolean isSuccess, int erroeCode) {
        HashMap<String, Serializable> msg_Obj = new HashMap<String, Serializable>();
        msg_Obj.put("cmd", cmd);
        msg_Obj.put("isSuccess", isSuccess);
        msg_Obj.put("erroeCode", erroeCode);
        return  Message.newInstance(cmd,msg_Obj);
    }

    public static Message time_check(){
        Message TIME_CHECK=new Message();
        TIME_CHECK.cmd="time_check";
        TIME_CHECK.data=new HashMap();
        TIME_CHECK.data.put("ctime",System.currentTimeMillis()/1000);
        return TIME_CHECK;
    }
}
