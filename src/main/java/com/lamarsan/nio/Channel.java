package com.lamarsan.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * className: Channel
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/2 14:04
 */
public class Channel {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\hasee\\Desktop\\file\\output.txt", "rw");
        FileChannel inChannel = accessFile.getChannel();
        long start = System.currentTimeMillis();
        // 分配缓冲区容量大小
        ByteBuffer buffer = ByteBuffer.allocate(48);
        // 1：从channel写入数据到buffer
        int bytesRead = inChannel.read(buffer);
        while (bytesRead != -1) {
            // 2：调用flip() 从写模式切换到读模式
            buffer.flip();
            while (buffer.hasRemaining()) {
                // 3：从buffer中读取
                System.out.print((char) buffer.get());
            }
            // 4：调用clear清空缓冲区
            buffer.clear();
            // 继续读
            bytesRead = inChannel.read(buffer);
        }
        accessFile.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
