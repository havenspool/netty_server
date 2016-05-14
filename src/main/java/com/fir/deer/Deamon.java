package com.fir.deer;

import com.fir.deer.server.Server;

/**
 * Created by havens on 15-8-7.
 */
public class Deamon{

    public static void main(String[] args) throws Exception {
        new Server().run();
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(LoginJob.getInstance());
    }
}
