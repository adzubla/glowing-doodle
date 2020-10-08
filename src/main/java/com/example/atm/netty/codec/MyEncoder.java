package com.example.atm.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {

        int length = msg.readableBytes() + 1;
        ByteBuf result = Unpooled.buffer(length);

        length--;
        result.writeByte('*');

        while (length-- > 0) {
            char c = (char) msg.readByte();
            result.writeByte(c);
        }

        out.writeBytes(result);
    }

}
