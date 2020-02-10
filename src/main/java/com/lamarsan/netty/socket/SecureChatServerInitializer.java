package com.lamarsan.netty.socket;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * className: SecureChatServerInitializer
 * description: 原有基础上加密处理
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/10 21:05
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
    private final SSLContext context;

    public SecureChatServerInitializer(ChannelGroup group, SSLContext context) {
        super(group);
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = context.createSSLEngine();
        engine.setUseClientMode(false);
        ch.pipeline().addFirst(new SslHandler(engine));
    }
}
