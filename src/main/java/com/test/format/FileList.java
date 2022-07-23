package com.test.format;

import java.io.*;

public class FileList {
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
        String s = "C:\\Users\\zhudongping\\Desktop\\cv\\222.jpeg";
        String s1 = "D:\\DataAnnotation\\idas-service\\data-annotation-service\\out\\production\\datasettemp\\yzhshow\\cocococococ\\coco\\annotations\\ instance_default.json";
        File f = new File(s);
        String[] f1 = f.list();
        File[] f2 = f.listFiles();
        String ss = readJsonFile("D:\\DataAnnotation\\idas-service\\data-annotation-service\\out\\production\\datasettemp\\yzhshow\\cocococococ\\coco\\annotations\\ instance_default.json");
        System.out.println(ss);
    }
}
