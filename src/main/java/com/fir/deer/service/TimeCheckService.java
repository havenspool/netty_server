package com.fir.deer.service;

import com.fir.deer.Service;
import com.fir.deer.message.MessageHelper;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by havens on 15-8-11.
 */
public class TimeCheckService extends Service {

    @Override
    public void filter(JSONObject jObject) throws Exception {
        write(MessageHelper.time_check());
    }
}
