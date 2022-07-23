package com.test.opencv;

import java.io.File;

public class Demo{
    public static void main(String[] args) {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        testHexFormat();
    }
    public static String hexFormat(byte[] bytes,int colNum){
        StringBuilder stringBuilder = new StringBuilder(bytes.length*4);
        for (int i = 0; i < bytes.length; i++) {
            if(i%colNum==0){
                stringBuilder.append(String.format("0x%05x",i));
            }
            stringBuilder.append(String.format(" %02x",bytes[i]));
            if(i%colNum==colNum-1){
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
    public static void testHexFormat(){
        byte[] bytes = new byte[100];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) i;
        }
        System.out.println(hexFormat(bytes,16));
    }

}
