package com.test.cvat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class ImageNet {
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
        String path = ImageNet.class.getClassLoader().getResource("cvat-idas.json").getPath();
        String s = readJsonFile(path);
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray tracks = jsonObject.getJSONArray("tracks");
        JSONArray shapes = jsonObject.getJSONArray("shapes");
        JSONArray tags = jsonObject.getJSONArray("tags");
        JSONArray hards = jsonObject.getJSONArray("hards");
        int width = jsonObject.getIntValue("width");
        int height = jsonObject.getIntValue("height");
        String content = jsonObject.getString("content");
        for (int i = 0; i < shapes.size(); i++) {
            JSONObject shape = shapes.getJSONObject(i);
            String type = shape.getString("type");
            JSONObject image = new JSONObject();
            image.put("file_name", content.substring(content.lastIndexOf("/") + 1));
            JSONArray bbox = new JSONArray();
            JSONArray points = shape.getJSONArray("points");
            double upLfet_x = points.getDouble(0);
            double upLfet_y = points.getDouble(1);
            double lowRight_x = points.getDouble(2);
            double lowRight_y = points.getDouble(3);
            bbox.add(upLfet_x);
            bbox.add(upLfet_y);
            bbox.add(lowRight_x);
            bbox.add(lowRight_y);
            System.out.println(bbox);


        }
    }
}
