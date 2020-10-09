package com.example.atm.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {

    private final boolean proceed;

    public MyDecoder(boolean proceed) {
        this.proceed = proceed;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (proceed) {
            int length = in.readableBytes();
            ByteBuf result = Unpooled.buffer(length);

            length--;
            in.skipBytes(1);

            while (length-- > 0) {
                char c = (char) in.readByte();
                result.writeByte(c);
            }

            out.add(result);
        } else {
            ctx.writeAndFlush("* error *");
        }
    }

}
