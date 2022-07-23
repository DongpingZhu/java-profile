package com.test.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class jsonToTextCls {
    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String path = JsonToBrat.class.getClassLoader().getResource("textCls.json").getPath();
        String s = readJsonFile(path);
        System.out.println(s);
        JSONObject jobj = JSON.parseObject(s);
        String content = jobj.getString("content");
        String itemSign = jobj.getString("itemSign");
        StringBuffer sb = new StringBuffer();
        sb.append(content).append(" ").append(itemSign);
        System.out.println(sb);

    }
}
