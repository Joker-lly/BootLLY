package com.lly.basechat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 描述 ： netty入门版聊天室
 *
 * @author Joker-lly
 * @since 2021-01-30
 */
public class BaseChatServer {

    public static void main(String[] args) {

        //创建两个线程组bossGroup和workerGroup, 含有的子线程NioEventLoop的个数默认为cpu核数的两倍
        // bossGroup只是处理连接请求 ,真正的和客户端业务处理，会交给workerGroup完成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务端启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程配置参数
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的,所以同一时间只能处理一个客户端连接。
                    // 多个客户端同时来的时候,服务端将不能处理的客户端连接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向 pipeline 添加 解码器 顺序要求？
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向 pipeline 添加 编码器
                            pipeline.addLast("encoder", new StringEncoder());

                            // 实现心跳连接
                           // pipeline.addLast(new IdleStateHandler(3,0,0, TimeUnit.MILLISECONDS ));
                            //对workerGroup的SocketChannel设置处理器
                            pipeline.addLast(new BaseChatServerHandler());
                        }
                    });

            System.out.println("聊天室 start......");

            ChannelFuture cf = bootstrap.bind(9000).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
