package com.lamarsan.netty.transport;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * className: PlainOioServer
 * description: 接受客户端连接并发送“Hi!"字符串消息到客户端
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 21:45
 */
public class PlainOioServer {
    public static void main(String[] args) throws IOException {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.server(8080);
    }

    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            while (true) {
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(() -> {
                    OutputStream outputStream;
                    try {
                        outputStream = clientSocket.getOutputStream();
                        outputStream.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                        outputStream.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            socket.close();
        }
    }
}
