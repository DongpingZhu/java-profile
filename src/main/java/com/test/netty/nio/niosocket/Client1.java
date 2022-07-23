package com.test.netty.nio.niosocket;

import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws Exception{
        for (int i=0;i<100;i++){
            Socket socket = new Socket("localhost", 8888);
            socket.close();
            System.out.println("客户端连接个数：" + (i+1));
        }

    }
}
