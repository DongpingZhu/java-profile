package com.test.netty.bio;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 6666);
        System.out.println("client is ok.....");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            String msg = scanner.nextLine();
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();
        }

    }
}
