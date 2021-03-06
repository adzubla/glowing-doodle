package com.example.atm.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<ByteBuf> {

    private final Character theChar;

    public MyEncoder(Character theChar) {
        this.theChar = theChar;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {

        int length = msg.readableBytes();
        ByteBuf result = Unpooled.buffer(length);

        result.writeByte(theChar);

        while (length-- > 0) {
            char c = (char) msg.readByte();
            result.writeByte(c);
        }

        out.writeBytes(result);
    }

}
