package com.lamarsan.netty.official;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * className: TimeEncoder
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 0:35
 */
public class TimeEncoder extends MessageToByteEncoder<TimeEntity> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TimeEntity msg, ByteBuf out) throws Exception {
        out.writeInt((int) msg.value());
    }
}
