package com.lamarsan.netty.official;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * className: TimeDecoder
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 0:23
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new TimeEntity(in.readUnsignedInt()));
    }
}
