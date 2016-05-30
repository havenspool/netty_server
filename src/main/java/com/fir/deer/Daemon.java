package com.fir.deer;

import com.fir.deer.server.Server;
import com.fir.deer.server.WorldManager;
import com.fir.deer.utils.FileHelper;

import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Properties;

/**
 * Created by havens on 15-8-7.
 */
public class Daemon{
    public static final String SERVER_PROP = "server.properties";

    public static void setUp(){
        Properties config = FileHelper.getProperties(SERVER_PROP);
        Server.port = Integer.valueOf(config.getProperty("port", "8090")).intValue();
        Server.APP_HOME = config.getProperty("app_home", "/home/havens/Code/deer/");

        WorldManager.User_DB = config.getProperty("user_db", "deer_user");
        WorldManager.SERVER_ID = Integer.valueOf(config.getProperty("server_id", "10001")).intValue();
    }

    public static void main(String[] args) throws Exception {
        setUp();

        try{
            String pid = ManagementFactory.getRuntimeMXBean().getName();
            PrintWriter out = new PrintWriter(Server.APP_HOME + "pid.txt");
            out.println(pid.substring(0, pid.indexOf('@')));
            out.flush();
            out.close();
            System.out.println("pid:"+pid);
        }
        catch (Exception localException){
        }

        new Server().run();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(LoginJob.getInstance());
    }
}
