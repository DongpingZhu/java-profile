package com.test.netty.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileChannelDemo3 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhudongping\\Desktop\\demo.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zhudongping\\Desktop\\demo2.txt");

        FileChannel fileChannel1 = fileInputStream.getChannel();
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        fileChannel2.transferFrom(fileChannel1, 0, fileChannel1.size());

        fileChannel1.close();
        fileChannel2.close();
        fileInputStream.close();
        fileOutputStream.close();
        ;


    }
}
