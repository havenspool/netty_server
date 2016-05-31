package com.fir.deer.service;

import com.fir.deer.Controller.UserController;
import com.fir.deer.ErrorCode;
import com.fir.deer.Service;
import com.fir.deer.message.MessageHelper;
import com.fir.deer.server.Server;
import com.fir.deer.server.WorldManager;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by havens on 15-8-13.
 */
public abstract class UserService extends Service{
    public UserController userCtrl;
    protected WorldManager worldManager;

    @Override
    public void create(Server server) throws Exception {
        super.create(server);
        worldManager = WorldManager.getInstance(server);
    }

    public boolean beforeFilter(JSONObject jObject) throws Exception {
        int userId=(Integer) jObject.get("userId");
        if(userId==0){
            write(MessageHelper.cmd_error(cmd, false, ErrorCode.USER_NOT_EXIST));
            return false;
        }
        userCtrl=worldManager.onlineUser().getUnchecked(userId);
        return true;
    }

    public void afterFilter() throws Exception {
    }

}
//userCtrl = worldManager.onlineUser().getUnchecked(userId);