package com.test.jackson;

import java.util.Base64;

public class Base64Demo {
    public static void main(String[] args) throws Exception {
        String s1 = Base64.getEncoder().encodeToString("zhudongping".getBytes("utf-8"));
        System.out.println("encoding:" + s1);
        byte[] b1 = Base64.getDecoder().decode(s1);
        System.out.println("decoding:" + new String(b1, "utf-8"));
    }
}
