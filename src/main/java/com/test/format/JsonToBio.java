package com.test.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

public class JsonToBio {
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
        String path = JsonToBrat.class.getClassLoader().getResource("twoWords.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        HashMap<List<Integer>, String> scope = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        String content = jobj.getString("content"); //标注原始文本
        JSONArray labelCat = jobj.getJSONArray("labelCategories");
        JSONArray label = jobj.getJSONArray("labels");
        //E
        for (int i = 0; i < labelCat.size(); i++) {
            int id = (int) labelCat.getJSONObject(i).get("id");
            String text = (String) labelCat.getJSONObject(i).get("text"); //实体标签
            for (int j = 0; j < label.size(); j++) {
                int categotyId = (int) label.getJSONObject(j).get("categoryId");
                if (categotyId == id) {
                    List<Integer> scopeIndex = new ArrayList<>();
                    if ((Integer) label.getJSONObject(j).get("endIndex") - 1 == (Integer) label.getJSONObject(j).get("startIndex")) {
                        scopeIndex.add((Integer) label.getJSONObject(j).get("startIndex"));
                        scope.put(scopeIndex, text);
                    } else {
                        scopeIndex.add((Integer) label.getJSONObject(j).get("startIndex"));
                        scopeIndex.add((Integer) label.getJSONObject(j).get("endIndex") - 1);
                        scope.put(scopeIndex, text);
                    }
                }
            }
        }
        System.out.println(scope);

        String[] contentArray = content.split("");
        System.out.println(content.length());
        System.out.println(contentArray.length);
        System.out.println(Arrays.toString(contentArray));
        for (int i = 0; i < contentArray.length; i++) {
            contentArray[i] = contentArray[i] + " " + "O";
        }


        for (Map.Entry<List<Integer>, String> entry : scope.entrySet()) {
            List<Integer> key = entry.getKey();
            String value = entry.getValue();
            for (int index : key) {
                if (index == key.get(0)) {
                    if (key.size() == 1) {
                        contentArray[index] = contentArray[index].replace("O", "") + "S-" + value;
                    } else {
                        contentArray[index] = contentArray[index].replace("O", "") + "B-" + value;
                    }
                } else {
                    for (int i = key.get(0) + 1; i < key.get(1) + 1; i++) {
                        contentArray[i] = contentArray[i].replace("O", "") + "I-" + value;
                    }


                }

            }

            if (key.size() > 1) {
                int end = key.get(1);
                contentArray[end] = contentArray[end].replace("I-", "E-");
            }
        }

        for (int i = 0; i < contentArray.length; i++) {
            sb.append(contentArray[i]).append(System.lineSeparator());
        }
        System.out.println(sb);
    }
}
