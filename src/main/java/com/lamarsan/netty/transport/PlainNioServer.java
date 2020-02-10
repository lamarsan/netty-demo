package com.lamarsan.netty.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * className: PlainNioServer
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 21:52
 */
public class PlainNioServer {
    public static void main(String[] args) throws IOException {
        PlainNioServer plainNioServer = new PlainNioServer();
        plainNioServer.server(8080);
    }

    public void server(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        while (true) {
            int n = selector.select();
            if (n > 0) {
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    try {
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                            SocketChannel client = serverSocketChannel1.accept();
                            System.out.println("Accepted connection from " + client);
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_WRITE, msg.duplicate());
                        }
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (buffer.hasRemaining()) {
                                if (client.write(buffer) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }
                    } catch (Exception e) {
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }
}
