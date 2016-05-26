package com.fir.deer.server;

import com.fir.deer.Service;
import com.fir.deer.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by havens on 15-8-7.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

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
    public void channelRead(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
//        System.out.println("Client from" +s);
//        ByteBuf result = (ByteBuf) s;
//        byte[] result1 = new byte[result.readableBytes()];
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//        String resultStr = new String(result1);
//        // 释放资源，这行很关键
//        result.release();
        System.out.println("Client from" +s);
        byte[] result1 = (byte[])s;
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        String resultStr = new String(result1);
        System.out.println("Client said:" + resultStr);
        ctx.channel().writeAndFlush(resultStr);
        Message msg=new Message(resultStr);
        if (msg.jObject==null) {
            return;
        }
        msg.channel=ctx.channel();
        Service service=Server.service(msg.cmd);
        System.out.println("Client: cmd:"+msg.cmd);
        if(service!=null){
            service.setChannel(msg.channel);
            service.filter(msg.jObject);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        String sendMsg="{\"userName\":\"asan\",\"userPwd\":\"123456\",\"cmd\":\"user_login\"}";
        byte[] msg=sendMsg.getBytes("UTF-8");
        ctx.channel().writeAndFlush(msg);
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
