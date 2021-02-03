package com.lly.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyMessageDecoder extends ByteToMessageDecoder {

    int length = 0;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes()>4) {
            if (length == 0) {
                length = in.readInt();
            }
            if (in.readableBytes() < length) {
                System.out.println("当前数据长度不够，继续等待");
                return;
            }
            byte[] content = new byte[length];

            if (in.readableBytes() >= length) {
                in.readBytes(content);
                //将Mymessage封装成 对象
                MyMessageProtocol myMessageProtocol = new MyMessageProtocol();
                myMessageProtocol.setLen(length);
                myMessageProtocol.setContent(content);
                out.add(myMessageProtocol);
            }
            length = 0;
        }
    }
}
