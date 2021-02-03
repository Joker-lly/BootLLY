package com.lly.heartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

public class NettyClient {
    private String ip;
    private int port;
    private  Bootstrap bootstrap ;
    private NioEventLoopGroup group;

    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        init();
    }

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 9000);
        nettyClient.connect();

    }
    private void init() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HeartBeatClientHandler());

                    }
                });
    }
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
                            System.out.println("开始重连。。。");
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
}
