package com.fir.deer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by havens on 16-5-24.
 */
public class TestClient {
    static Socket server;

    public static void main(String[] args) throws Exception {
        server = new Socket("127.0.0.1", 8090);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        out.println("{\"userName\":\"asan\",\"userPwd\":\"123456\",\"cmd\":\"user_login\"}");
        out.flush();
//        while (true) {
//            String str = wt.readLine();
//            out.println(str);
//            System.out.println(str);
//            out.flush();
//            if (str.equals("end")) {
//                break;
//            }
//        }
        while (true){
            System.out.println(in.readLine());
        }
//        server.close();
    }
}
