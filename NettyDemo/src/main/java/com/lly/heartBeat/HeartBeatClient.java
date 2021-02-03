package com.lly.heartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HeartBeatClient {

    public void connect() throws Exception {
        System.out.println("netty client start ");
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 9000);
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (! future.isSuccess()) {
                    // 重新交给后端线程执行
                    future.channel().eventLoop().schedule(() -> {
                        try {
                            connect();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    },3000, TimeUnit.MILLISECONDS);
                } else {
                    System.out.println("连接成功 ");
                }
            }
        });
        cf.channel().closeFuture().sync();
    }
    private static Bootstrap bootstrap = new Bootstrap();
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {

            //设置相关参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            //加入处理器
                            pipeline.addLast(new HeartBeatClientHandler());

                        }
                    });

            System.out.println("netty client start ....");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();

            System.out.println("========" + channel.localAddress() + "=============");
            String text = "Heartbeat Packet";
            Random random = new Random();
            while (channel.isActive()) {
                int num = random.nextInt(10);
                System.out.println("接下来休息 几秒钟 " + num);
                Thread.sleep(num * 1000);
                channel.writeAndFlush(text);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
