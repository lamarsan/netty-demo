package com.lamarsan.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * className: LogEventHandler
 * description: 打印
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/10 21:41
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        String builder = msg.getReceived() +
                "[" +
                msg.getSource().toString() +
                "][" +
                msg.getLogfile() +
                "]:" +
                msg.getMsg();
        System.out.println(builder);
    }
}
