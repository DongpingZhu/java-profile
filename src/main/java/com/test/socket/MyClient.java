package com.test.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 6666;

        try {
            System.out.println("连接远程主机：" + serverName + ", 端口号：" + port);
            Socket socket = new Socket(serverName, port);
            System.out.println("远程主机地址："+socket.getRemoteSocketAddress());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("我是MyClient:"+socket.getLocalSocketAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("来自服务器MyServer的响应："+in.readUTF());
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
