package com.test.heartbeat;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            new Thread(new SendThread(socket)).start();
            new Thread(new ReceiveThread(socket)).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    static class SendThread implements Runnable{
        private long lastTime;
        private Socket socket;

        public SendThread(Socket s){
            this.socket = s;
        }
        @Override
        public void run(){
            while (true){
                try {
                    if(System.currentTimeMillis()-lastTime>2000){
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        HeartBeat heartBeat = new HeartBeat();
                        heartBeat.setTime(System.currentTimeMillis());
                        objectOutputStream.writeObject(heartBeat);
                        lastTime = System.currentTimeMillis();

                    }else {
                        Thread.sleep(10);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    static class ReceiveThread implements Runnable{
        Socket socket;
        public ReceiveThread(Socket s){
            this.socket = s;
        }
        @Override
        public void run(){
            while (true){
                try {
                    InputStream inputStream = socket.getInputStream();
                    if(inputStream.available()>0){
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        Object o = objectInputStream.readObject();
                        System.out.println("接收到的服务器端返回："+o);
                    }else {
                        Thread.sleep(10);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
