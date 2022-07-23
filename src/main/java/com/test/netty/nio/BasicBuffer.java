package com.test.netty.nio;

import java.nio.IntBuffer;

/*
    buffer和channel的基本两套读写方式：
        1. put, get: 读入、写出buffer
        2. read, write: 读入、写出buffer
 */
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);
//        for(int i=0;i<intBuffer.capacity();i++){
//            intBuffer.put(i*2);
//        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer: " + intBuffer.get());
        }
    }
}
