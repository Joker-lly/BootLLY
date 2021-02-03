package com.lly.splitpacket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServerHandler extends SimpleChannelInboundHandler<MyMessageProtocol> {

    // 全局事件执行器 是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageProtocol msg) throws Exception {
        Channel channel = ctx.channel();
        SocketAddress socketAddress = channel.remoteAddress();
        System.out.println("[客户端] ：" + socketAddress + " 说 ：" + msg + "\n");
        channelGroup.forEach(ch -> {
            if (ch == channel) {
                ch.writeAndFlush("自己说 ：" + msg + "\n");
            } else {
                //ch.writeAndFlush("[客户端] ：" + socketAddress + " 说 ：" + msg + "\n");
                ch.writeAndFlush(msg );
            }
        });
        System.out.println("=============");
        System.out.println("长度 " +msg.getLen());
        System.out.println("内容 "+new String(msg.getContent(), CharsetUtil.UTF_8));
    }

    // 表示 channel 处于就绪状态 ，提示上下线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        // 向所有的 channelGroup 中的所有的 channel 提示 上线信息 排除自己
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 上线了 " +sdf.format(new Date()) + "\n");
        channelGroup.add(channel);
        System.out.println(ctx.channel().remoteAddress() + " 上线了 " + "\n");
    }

    // 提示下线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 下线了 " +sdf.format(new Date()) + "\n");
        System.out.println(ctx.channel().remoteAddress() + " 下线了 " + "\n");
    }
}
