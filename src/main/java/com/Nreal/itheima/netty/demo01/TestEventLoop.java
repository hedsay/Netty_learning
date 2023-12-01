package com.Nreal.itheima.netty.demo01;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

public class TestEventLoop {
    public static void main(String[] args) {
        //1.创建事件循环组
        EventLoopGroup group = new NioEventLoopGroup(2);//io事件，普通任务，定时任务
//        EventLoopGroup group = new DefaultEventLoopGroup();//普通任务，定时任务
        //2.获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        //3.执行普通任务
        group.next().submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //4.执行定时任务
        group.next().scheduleAtFixedRate(()->{
            System.out.println("ok");
        },0,1, TimeUnit.SECONDS);
    }
}
