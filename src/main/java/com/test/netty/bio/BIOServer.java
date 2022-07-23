package com.test.netty.bio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("正在读取....");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, CharsetUtil.UTF_8));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server is ok...");

        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println("from a client");
            newCachedThreadPool.execute(() -> handler(socket));
        }


    }
}
