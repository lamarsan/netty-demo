package com.lamarsan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * className: Select
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/2 14:41
 */
public class Select {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("http://jenkov.com", 80));
        channel.configureBlocking(false);
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) {
                continue;
            }
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
