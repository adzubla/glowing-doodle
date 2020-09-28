package com.example.atm.netty.codec.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CryptoDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte c = in.getByte(in.readerIndex());
        if (c >= 48 && c <= 57) {
            System.out.println("* INVALID *");
        }

        out.add(decrypt(in));
    }

    private ByteBuf decrypt(ByteBuf data) {
        int length = data.readableBytes();
        ByteBuf result = Unpooled.buffer(length);

        while (length-- > 0) {
            char c = (char) data.readByte();
            //System.out.println("> c = " + c);
            result.writeByte(c);
        }

        return result;
    }

}
