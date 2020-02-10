package com.lamarsan.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * className: TimeServerHandler
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/2 15:27
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                // 读取数据的部分是阻塞的
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order: " + body);
                currentTime = "Query Time Order".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "Bad Order";
                // 输出数据
                out.println(currentTime);
            }
        }catch (IOException e) {
            e.printStackTrace();
            if(in != null){
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if(out != null){
                out.close();
                out = null;
            }
        }
    }
}
