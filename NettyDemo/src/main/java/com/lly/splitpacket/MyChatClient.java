package com.lly.splitpacket;

import com.lly.chat.ChatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class MyChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

//                            pipeline.addLast(new StringEncoder());
//                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new MyMessageEncoder());
                            pipeline.addLast(new MyMessageDecoder());
                            //加入处理器
                            pipeline.addLast(new MyClientHandler());


                        }
                    });

            System.out.println("netty client start ....");
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();

            System.out.println("========" + channel.localAddress() + "=============");
            Scanner scanner = new Scanner(System.in);
         /*   while (scanner.hasNext()) {
                String s = scanner.nextLine();
                channel.writeAndFlush(s);
            }*/
            MyMessageProtocol myMessageProtocol = new MyMessageProtocol();
            myMessageProtocol.setLen("hello 大家好，我是Vae，这是我即将发表的自创专辑".getBytes().length);
            myMessageProtocol.setContent("hello 大家好，我是Vae，这是我即将发表的自创专辑".getBytes());
            channel.writeAndFlush(myMessageProtocol);


        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
