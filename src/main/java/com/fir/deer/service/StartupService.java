package com.fir.deer.service;

import com.fir.deer.Service;
import com.fir.deer.dfa.DfaTool;
import com.fir.deer.server.Server;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by havens on 16-5-14.
 */
public class StartupService extends Service {

    public void create(Server server) throws Exception {
        // startup
        DfaTool.loadJsonData();
    }

    public void filter(JSONObject jObject) throws Exception {

    }
}
