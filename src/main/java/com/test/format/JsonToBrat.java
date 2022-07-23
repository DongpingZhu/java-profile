package com.test.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class JsonToBrat {
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
        String path = JsonToBrat.class.getClassLoader().getResource("test.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        StringBuffer sb = new StringBuffer();
        String content = jobj.getString("content");
        JSONArray labelCat = jobj.getJSONArray("labelCategories");
        JSONArray label = jobj.getJSONArray("labels");
        JSONArray connCat = jobj.getJSONArray("connectionCategories");
        JSONArray conn = jobj.getJSONArray("connections");
        // E
        StringBuilder labels = new StringBuilder();
        for (int i = 0; i < labelCat.size(); i++) {
            int id = (int) labelCat.getJSONObject(i).get("id");
            String text = (String) labelCat.getJSONObject(i).get("text"); //实体标签\
            for (int j = 0; j < label.size(); j++) {
                int categotyId = (int) label.getJSONObject(j).get("categoryId");
                int labelId = (int) label.getJSONObject(j).get("id");
                if (categotyId == id) {
                    StringBuilder list = new StringBuilder();
                    list.append(EntityR.getItemEByIndex(labelId)).append(" ");
                    list.append(text).append(" ");
                    list.append(label.getJSONObject(j).get("startIndex").toString()).append(" ");
                    list.append(label.getJSONObject(j).get("endIndex").toString()).append(" ");
                    list.append(content, (int) label.getJSONObject(j).get("startIndex"), (int) label.getJSONObject(j).get("endIndex")).append(" ");
                    labels.append(list.toString()).append(System.lineSeparator());
                }
            }
        }
        // R
        StringBuilder connList = new StringBuilder();
        for (int i = 0; i < connCat.size(); i++) {
            int id = (int) connCat.getJSONObject(i).get("id");
            String itemSign = (String) connCat.getJSONObject(i).get("itemSign");
            for (int j = 0; j < conn.size(); j++) {
                int connId = (int) conn.getJSONObject(j).get("id");
                int catId = (int) conn.getJSONObject(j).get("categoryId");
                int fromId = (int) conn.getJSONObject(j).get("fromId");
                int toId = (int) conn.getJSONObject(j).get("toId");
                if (catId == id) {
                    StringBuilder list = new StringBuilder();
                    list.append(EntityR.getItemRByIndex(connId)).append(" ");
                    list.append(itemSign).append(" ");
                    list.append("Arg1:").append(EntityR.getItemEByIndex(fromId)).append(" ");
                    list.append("Arg2:").append(EntityR.getItemEByIndex(toId)).append(" ");
                    connList.append(list).append(System.lineSeparator());
                }
            }
        }
        sb.append(labels).append(connList);
        System.out.println(sb);
    }
}
