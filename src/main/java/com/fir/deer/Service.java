package com.fir.deer;

import com.fir.deer.server.Server;
import io.netty.channel.Channel;

import java.io.IOException;
import java.util.Map;

import com.fir.deer.message.Message;

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

    protected void write(Message msg) throws IOException {
        channel.writeAndFlush(msg);
    }
    public final void service(final Map map) throws Exception {
        if (beforeFilter(map)) {
            filter(map);
            afterFilter();
        }
    }

    public void create(Server server) throws Exception {
    }

    public boolean beforeFilter(final Map map) throws Exception {
        return true;
    }

    public void afterFilter() throws Exception {
    }

    public abstract void filter(Map map) throws Exception;
}
