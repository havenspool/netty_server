package com.fir.deer.server;

import com.fir.deer.Service;
import com.fir.deer.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by havens on 15-8-7.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        channels.remove(ctx.channel());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        Message msg=(Message)s;
        if (msg == null||msg.data==null) {
            return;
        }
        msg.channel=ctx.channel();
        Service service=Server.service(msg.cmd);
        System.out.println("Client: cmd"+msg.cmd);
        if(service!=null){
            service.setChannel(msg.channel);
            service.filter(msg.data);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        System.out.println("Client:"+ctx.channel().remoteAddress()+"链接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        System.out.println("Client:"+ctx.channel().remoteAddress()+"掉线");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Client:"+ctx.channel().remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
