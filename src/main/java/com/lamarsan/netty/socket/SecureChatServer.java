package com.lamarsan.netty.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;

/**
 * className: SecureChatServer
 * description: 访问地址：https://localhost:4096
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/10 21:07
 */
public class SecureChatServer extends ChatServer {
    private final SSLContext context;

    public SecureChatServer(SSLContext context) {
        this.context = context;
    }

    @Override
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        return new SecureChatServerInitializer(group, context);
    }

    /**
     * 获取SSLContext需要相关的keystore文件，这里没有关于HTTPS可以查阅相关资料，这里只介绍在Netty中如何使用
     */
    private static SSLContext getSslContext() {
        return null;
    }

    public static void main(String[] args) {
        SSLContext context = getSslContext();
        final SecureChatServer server = new SecureChatServer(context);
        ChannelFuture future = server.start(new InetSocketAddress(4096));
        Runtime.getRuntime().addShutdownHook(new Thread(server::destroy));
        future.channel().closeFuture().syncUninterruptibly();
    }
}
