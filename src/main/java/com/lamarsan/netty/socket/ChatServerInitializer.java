package com.lamarsan.netty.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * className: ChatServerInitializer
 * description: 初始化ChannelHandler
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/10 20:51
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup channelGroup;

    public ChatServerInitializer(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        // 编解码http请求
        channelPipeline.addLast(new HttpServerCodec());
        // 写文件内容
        channelPipeline.addLast(new ChunkedWriteHandler());
        // 聚合解码HttpRequest/HttpContent/LastHttpContent到FullHttpRequest
        //保证接收的Http请求的完整性
        channelPipeline.addLast(new HttpObjectAggregator(64 * 1024));
        // 处理FullHttpRequest
        channelPipeline.addLast(new HttpRequestHandler("/ws"));
        // 处理其他的WebSocketFrame
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 处理TextWebSocketFrame
        channelPipeline.addLast(new TextWebSocketFrameHandler(channelGroup));
    }
}
