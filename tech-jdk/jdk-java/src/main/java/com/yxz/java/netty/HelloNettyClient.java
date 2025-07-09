package com.yxz.java.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @Description TODO
 * @Date 2025-05-27
 * @Created by Yolo
 */
public class HelloNettyClient {

    public static void main(String[] args) throws InterruptedException {

        // 1、启动类
        new Bootstrap()
                // 2、添加 EventLoop
                .group(new NioEventLoopGroup())
                // 3、选择客户端 channel 实现
                .channel(NioSocketChannel.class)
                // 4、添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5、链接到服务器
                .connect(new InetSocketAddress("localhost",8080))
                .sync()
                .channel()
                // 6、向服务器发送数据
                .writeAndFlush("hello world");

    }

}
