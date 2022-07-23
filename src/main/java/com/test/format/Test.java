package com.test.format;

public class Test {
    public static void main(String[] args) {
        String s = "http://10.110.26.164:9000/idas/25b4445a730ebec57fb38043f49e2d92/5b48cace82a0258e51d56791ee9a1bfd/5b48cace82a0258e51d56791ee9a1bfd.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20210516T034357Z&X-Amz-SignedHeaders=host&X-Amz-Expires=900&X-Amz-Credential=minioadmin%2F20210516%2FUS_EAST_1%2Fs3%2Faws4_request&X-Amz-Signature=98501d384daa1ea4c96e4a0a5b1ebc25bda897a7c35616ba6b128d9e2a57cf89";

        s = s.substring(0, s.indexOf("?"));
        System.out.println(s);
    }
}
