package com.lamarsan.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * className: PipeDemo
 * description: sink 写数据 source 读数据
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/2 15:09
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        // 写数据
        String data = "This is a data" + System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }
        // 读数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        int bytesRead = sourceChannel.read(buffer);
        System.out.println(bytesRead);

        sinkChannel.close();
        sourceChannel.close();
    }
}
