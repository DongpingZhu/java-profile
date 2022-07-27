package com.test.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer{
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("等待远程MyClient的连接，端口号为："+serverSocket.getLocalPort()+"...");
            Socket accept = serverSocket.accept();// 阻塞等待连接
            System.out.println("远程MyClient的地址为："+ accept.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(accept.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(accept.getOutputStream());
            out.writeUTF("我是MyServer:"+accept.getLocalSocketAddress());
            accept.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








}
