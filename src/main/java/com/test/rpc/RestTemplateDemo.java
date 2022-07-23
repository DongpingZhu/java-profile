package com.test.rpc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo {
    public static void main(String[] args) {
        String uri = "http://tool.bitefu.net/jiari/?d=%s";
        String format = String.format(uri, "20210716");
        System.out.println(format);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(format, String.class);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(format, String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forObject);

    }

}
