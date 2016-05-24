package com.fir.deer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by havens on 16-5-24.
 */
public class TestClient {
    public static void main(String[] args) throws Exception {
        Socket server = new Socket();
        server.connect(new InetSocketAddress("119.29.254.14", 8090));
        server.setKeepAlive(true);
        OutputStream out = server.getOutputStream();
        ByteBuffer header = ByteBuffer.allocate(4);
        String sendMsg="{\"userName\":\"asan\",\"userPwd\":\"123456\",\"cmd\":\"user_login\"}";
        byte[] msg=sendMsg.getBytes();
        header.putInt(msg.length);
        out.write(header.array());
        out.write(msg);
        out.flush();
        InputStream in = server.getInputStream();
        byte[] buff = new byte[4096];
        int readed = in.read(buff);
        if(readed > 0){
            String str = new String(buff,4,readed);
            System.out.println("client received msg from server:"+str);
        }
        out.close();
    }
}
