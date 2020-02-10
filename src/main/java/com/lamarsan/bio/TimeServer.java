package com.lamarsan.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * className: TimeServer
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/2 15:26
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                // 从socket的队列中获取socket的连接
                // 相当于一个消费者
                socket = serverSocket.accept();
                // 获得到socket连接之后，分配线程任务进行处理
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("The time server close");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
    }
}
