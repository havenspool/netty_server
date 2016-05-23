package com.fir.deer;

import com.fir.deer.server.Server;
import io.netty.channel.Channel;

import java.io.IOException;
import java.util.Map;

import com.fir.deer.message.Message;
import org.json.JSONObject;

/**
 * Created by havens on 15-8-11.
 */
public abstract class Service {
    protected String cmd;

    protected Channel channel;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public Channel getChannel() {
        return this.channel;
    }

    protected void write(String json) throws IOException {
        System.out.println("write:"+json);
        channel.writeAndFlush(json);
        System.out.println("write after:"+json);
    }
    public final void service(JSONObject jObject) throws Exception {
        if (beforeFilter(jObject)) {
            filter(jObject);
            afterFilter();
        }
    }

    public void create(Server server) throws Exception {
    }

    public boolean beforeFilter(final JSONObject jObject) throws Exception {
        return true;
    }

    public void afterFilter() throws Exception {
    }

    public abstract void filter(final JSONObject jObject) throws Exception;
}
