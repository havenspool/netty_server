package com.fir.deer.server;

import com.fir.deer.Constants;
import com.fir.deer.Controller.UserController;
import com.fir.deer.entity.User;
import com.fir.deer.entity.dao.DBFactory;
import com.fir.deer.entity.dao.cache.DBFactoryCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by havens on 15-8-13.
 */
public class WorldManager {
    public static String User_DB;  //UserDB

    private DBFactory dbFactory;
    private static WorldManager god;

    private WorldManager(Server server){
        dbFactory = new DBFactoryCache();
        dbFactory.init();
    }

    private void buildUpTheWorld() {

    }

    public static WorldManager getInstance(Server server) {
        if (god == null) {
            god = new WorldManager(server);
            god.buildUpTheWorld();
        }
        return god;
    }

    public static WorldManager getInstance() {
        return god;
    }

    private UserController getUserController(int userId) {
        User user = new User();
        user.id = userId;
        UserController userCtrl = new UserController(user);
        userCtrl.initDAO(dbFactory);
        return userCtrl;
    }

    public DBFactory dbFactory() {
        return this.dbFactory;
    }

    private LoadingCache<Integer, UserController> onlineUser = CacheBuilder.newBuilder()
            .maximumSize(Constants.MAX_CACHE_ONLINE_USER_SIZE)
            .build(new CacheLoader<Integer, UserController>() {
                @Override
                public UserController load(Integer userId) throws Exception {
                    return getUserController(userId);
                }
            });

    public LoadingCache<Integer, UserController> onlineUser() {
        return this.onlineUser;
    }
}
