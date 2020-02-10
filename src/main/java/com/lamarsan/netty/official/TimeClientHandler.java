package com.lamarsan.netty.official;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * className: TimeClientHandler
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 0:10
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TimeEntity timeEntity = (TimeEntity) msg;
        System.out.println(timeEntity);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
