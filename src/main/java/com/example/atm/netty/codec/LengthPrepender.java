package com.example.atm.netty.codec;

import io.netty.handler.codec.LengthFieldPrepender;

public class LengthPrepender extends LengthFieldPrepender {

    public LengthPrepender() {
        super(2);
    }

}
