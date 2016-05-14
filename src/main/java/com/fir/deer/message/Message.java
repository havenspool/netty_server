package com.fir.deer.message;

import io.netty.channel.Channel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class Message implements Serializable {
    public int code;
    public String cmd;
    public Channel channel;
    public Map data;
    /**
     * 对象转数组
     * @param obj
     * @return
     */
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public static Message newInstance(final String cmd,final Map data) {
        Message msg = new Message();
        msg.cmd = cmd;
        msg.data=data;
        return msg;
    }

    public static void main(String[] args) throws Exception {
        Message msg = new Message();
        msg.cmd = "time_check";
        msg.data= new HashMap();
        msg.data.put("ctime",System.currentTimeMillis()/1000);
        System.out.println(msg.data);

        System.out.println(MessageHelper.time_check());
    }



}
