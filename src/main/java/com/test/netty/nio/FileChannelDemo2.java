package com.test.netty.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhudongping\\Desktop\\demo.txt");
        FileChannel fileChannel1 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zhudongping\\Desktop\\demo1.txt");
        FileChannel fileChannel2 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = fileChannel1.read(byteBuffer);
            System.out.println("read= " + read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
