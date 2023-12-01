package com.Nreal.itheima.netty.demo01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;

public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("127.0.0.1", 9527)
                .sync()//阻塞方法，直到连接建立
                .channel()
                .writeAndFlush(new Date() + ": hello world!");




//        Channel channel = new Bootstrap()
//                .group(new NioEventLoopGroup(1))
//                .handler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        System.out.println("init...");
//                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
//                    }
//                })
//                .channel(NioSocketChannel.class).connect("localhost", 8080)
//                .sync()
//                .channel();
//
//        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("wangwu".getBytes()));
//        Thread.sleep(2000);
//        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("wangwu".getBytes()));
    }
}
