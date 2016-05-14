package com.fir.deer.client;

import com.fir.deer.entity.User;
import com.fir.deer.message.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-7.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        User user=new User();
        user.name="havens";
        user.passwd="123456";

        Message msg2 = new Message();
        msg2.cmd = "login";
        Map data2 = new HashMap();
        data2.put("id",user.id);
        data2.put("name",user.name);
        data2.put("passwd",user.passwd);
        msg2.data=data2;
        incoming.writeAndFlush(msg2);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception {
        try {
            Message msg=(Message)s;
            if(msg.data!=null) {
                System.out.println("data from server:" + msg.data);
                Thread.sleep(10000);
                Message result = new Message();
                result.cmd = "time_check";
                result.data= new HashMap();
                if("login".equals(msg.cmd)||"time_check".equals(msg.cmd)){
                    ctx.channel().writeAndFlush(result);
                }
            }
        }catch (Exception e){
           // e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}