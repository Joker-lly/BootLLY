package com.lly.heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatClientHandler extends SimpleChannelInboundHandler<String> {
    private static NettyClient nettyClient = new NettyClient("127.0.0.1", 9000);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(" client received :" + msg);
        if (msg != null && msg.equals("idle close")) {
            System.out.println(" 服务端关闭连接，客户端也关闭");
            ctx.channel().closeFuture();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(" 断开重连");
        nettyClient.connect();
    }
}
