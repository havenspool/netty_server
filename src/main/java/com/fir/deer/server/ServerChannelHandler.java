package com.fir.deer.server;

import com.fir.deer.codec.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;


import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by havens on 15-8-7.
 */
public class ServerChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        ch.pipeline().addLast("decoder", new MessageDecoder());
//        ch.pipeline().addLast("encoder", new MessageEncoder());
        ch.pipeline().addLast("decoder", new StringDecoder());
        ch.pipeline().addLast("encoder", new StringEncoder());
//        ch.pipeline().addLast("decoder", MarshallingCodeCFactory.buildMarshallingDecoder());
//        ch.pipeline().addLast("encoder", MarshallingCodeCFactory.buildMarshallingEncoder());
//        //解码用
//        ch.pipeline().addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
//        //构造函数传递要解码成的类型
//        ch.pipeline().addLast("protobufDecoder", new ProtobufDecoder(LocalTimeProtocol.LocalTimes.getDefaultInstance()));
//        //编码用
//        ch.pipeline().addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
//        ch.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
        ch.pipeline().addLast(new ServerHandler());
        //System.out.println("Client:" + ch.remoteAddress() + "连接上");
    }
}
