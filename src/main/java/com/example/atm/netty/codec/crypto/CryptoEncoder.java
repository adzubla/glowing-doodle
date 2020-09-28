package com.example.atm.netty.codec.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CryptoEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        out.writeBytes(encrypt(msg));
    }

    private ByteBuf encrypt(ByteBuf data) {
        int length = data.readableBytes();
        ByteBuf result = Unpooled.buffer(length);

        while (length-- > 0) {
            char c = (char) data.readByte();
            //System.out.println("< c = " + c);
            result.writeByte(c);
        }

        return result;
    }

}
