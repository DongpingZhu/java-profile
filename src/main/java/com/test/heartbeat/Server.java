package com.test.heartbeat;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        new Thread(new ListeningThread()).start();
    }

    static class ListeningThread implements Runnable{
        @Override
        public void run(){
            try{
                ServerSocket serverSocket = new ServerSocket(8081);  // 建立一个服务器ServerSocket对象
                Socket accept = serverSocket.accept(); // 监听客户端Socket消息
                new Thread(new ProcessThread(accept)).start(); // 将Socket交给线程处理
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    static class ProcessThread implements Runnable{
        Socket socket;
        long lastReceiveTime = System.currentTimeMillis();
        public ProcessThread(Socket s){
            this.socket = s;
        }

        @Override
        public void run(){
            while (true){
                try {
                    if(System.currentTimeMillis()-lastReceiveTime>3000){
                        Thread.sleep(1000);
                        System.out.println("客户端下线了");
                    }else {
                        InputStream inputStream = socket.getInputStream();
                        if(inputStream.available()>0){
                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                            Object o = objectInputStream.readObject();
                            System.out.println("来自客户端的心跳："+o);
                            lastReceiveTime = System.currentTimeMillis();
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                            objectOutputStream.writeObject(o);
                            objectOutputStream.flush();
                        }else {
                            Thread.sleep(10);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    }
}
