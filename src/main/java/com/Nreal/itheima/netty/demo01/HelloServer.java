package com.Nreal.itheima.netty.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {
    public static void main(String[] args) {
        //1.启动器，服务组装netty组件，启动服务器
        new ServerBootstrap()
                //2.boss,worker组
                .group(new NioEventLoopGroup())
                //3.选择服务器ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                //4.boss负责处理连接，worker负责处理读写，决定了worker能执行哪些操作(handler)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());//将bytebuf转换为字符串
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(9527);



//        new ServerBootstrap()
//                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//                            @Override
//                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
//                                ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
//                                if (byteBuf != null) {
//                                    byte[] buf = new byte[16];
//                                    ByteBuf len = byteBuf.readBytes(buf, 0, byteBuf.readableBytes());
//                                    log.debug(new String(buf));
//                                }
//                            }
//                        });
//                    }
//                }).bind(8080).sync();
    }
}
