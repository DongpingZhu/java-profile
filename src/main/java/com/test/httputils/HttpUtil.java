package com.test.httputils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class HttpUtil {
    private static void setHeaders(HttpRequestBase requestBase, Map<String, String> headers){
        if(headers == null || headers.size()==0){
            return;
        }
        requestBase.setHeader("Content-type", "application/json");
        requestBase.setHeader("Accept", "application/json");
        headers.forEach((k,v)->requestBase.setHeader(k,v));
    }
    private static void setParameters(HttpPost post, Map<String, String> params, Map<String, String> headers){

    }

    private static HttpRequestBase request(String httpMethod, String url, Map<String, String> headers, Map<String, String> params, Object body){
        String lowerHttpMethod  = httpMethod.toLowerCase();
        switch (lowerHttpMethod){
            case "get":
                HttpGet httpGet = new HttpGet(url);
                setHeaders(httpGet, headers);
                return httpGet;
            case "post":
                HttpPost httpPost = new HttpPost(url);
                setHeaders(httpPost,headers);
                setParameters(httpPost,params,headers);
                return httpPost;
            case "delete":
                HttpDelete httpDelete = new HttpDelete(url);
                setHeaders(httpDelete, headers);
                return httpDelete;
            case "patch":
                HttpPatch httpPatch = new HttpPatch(url);
                setHeaders(httpPatch, headers);
                return httpPatch;
            default:
                return null;
        }
    }

    public static String httpResquest(String url, String httpMethod, Map<String, String> headers, Map<String, String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        String responseStr = null;
        try {
            httpResponse = httpClient.execute(request(httpMethod,url,headers,params,null));
            HttpEntity httpEntity = httpResponse.getEntity();
            if(Objects.nonNull(httpEntity)){
                responseStr = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(Objects.nonNull(httpClient)){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(Objects.nonNull(httpResponse)){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;

    }
}
