package com.yxz.java.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Description TODO
 * @Date 2025-05-27
 * @Created by Yolo
 */
public class HelloNettyServer {


    public static void main(String[] args) {


        // 1、启动器，负责组装 netty 组件，启动服务器
        new ServerBootstrap()
                // 2、BossEventLoop、WorkerEventLoop（selector、thread），group组
                .group(new NioEventLoopGroup())
                // 3、选择服务器的 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                // 4、boss负责处理连接，worker负责处理读写，决定 worker执行逻辑
                .childHandler(
                        // 5、 channel 代表和客户端进行数据读写的通道 Initializer初始化
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nsc) throws Exception {
                                // 6、添加具体 handler
                                nsc.pipeline().addLast(new StringDecoder()); // 将ByteBuf转换为字符串
                                nsc.pipeline().addLast(new ChannelInboundHandlerAdapter() {  // 自定义处理器
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        }
                )
                // 7、绑定监听端口
                .bind(8080);

    }

}
