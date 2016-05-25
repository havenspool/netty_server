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
        server.connect(new InetSocketAddress("127.0.0.1", 8090));//119.29.254.14  127.0.0.1
        server.setKeepAlive(true);
        OutputStream out = server.getOutputStream();
        InputStream in = server.getInputStream();

        ByteBuffer header = ByteBuffer.allocate(4);
        String sendMsg="{\"userName\":\"asan\",\"userPwd\":\"123456\",\"cmd\":\"user_login\"}";
        byte[] msg=sendMsg.getBytes("UTF-8");
//        byte[] header=new byte[4];

        header.putInt(msg.length);
//        out.write(header.array());
        out.write(sendMsg.getBytes("UTF-8"));
        out.flush();

        byte[] buff = new byte[4096];
        int readed = in.read(buff);
        if(readed > 0){
            String str = new String(buff,4,readed);
            System.out.println("client received msg from server:"+str);
        }
        out.close();
    }
}
