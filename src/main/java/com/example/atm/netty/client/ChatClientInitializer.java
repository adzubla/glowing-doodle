/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.example.atm.netty.client;

import com.example.atm.netty.codec.LengthFrameDecoder;
import com.example.atm.netty.codec.LengthPrepender;
import com.example.atm.netty.codec.MyDecoder;
import com.example.atm.netty.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFrameDecoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new StringDecoder());

        pipeline.addLast(new LengthPrepender());
        pipeline.addLast(new MyEncoder('!'));
        pipeline.addLast(new MyEncoder('@'));
        pipeline.addLast(new MyEncoder('#'));
        pipeline.addLast(new StringEncoder());

        pipeline.addLast(new ChatClientHandler());
    }
}
