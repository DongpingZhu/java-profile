package com.test.format;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class JsonToXml {

    public static String jsonToXml(String json) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><annotation>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj, buffer);
            return buffer.append("</annotation>").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readJsonFile(String fileName) {
        Reader reader = null;
        try {
            File jsonFile = new File(fileName);
            reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String jsonToXmlstr(JSONObject jObj, StringBuffer buffer) {
        Set<Map.Entry<String, Object>> se = jObj.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> en = it.next();
            String clazz = en.getValue().getClass().getName();
            if (clazz.equals("com.alibaba.fastjson.JSONObject")) {
                buffer.append("<" + en.getKey() + ">").append(System.lineSeparator());
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo, buffer);
                buffer.append("</" + en.getKey() + ">").append(System.lineSeparator());
            } else if (clazz.equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey() + ">").append(System.lineSeparator());
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">").append(System.lineSeparator());
                }
            } else if (clazz.equals("java.lang.String") || clazz.equals("java.lang.Integer") || clazz.equals("java.lang.Long")) {
                buffer.append("<" + en.getKey() + ">" + System.lineSeparator() + en.getValue()).append(System.lineSeparator());
                buffer.append("</" + en.getKey() + ">").append(System.lineSeparator());
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {

        String str1 = "{\"images\":[{\"file_name\":\"701f8020ced81602239cf0eb25318da9.jpg\",\"flickr_url\":\"http://117.73.9.199:9000/ida/c5786c2c7a6b932f6c5bfdac0b895b1d/701f8020ced81602239cf0eb25318da9.jpg\",\"width\":499,\"date_captured\":\"2020-08-21 15:00:38\",\"id\":\"701f8020ced81602239cf0eb25318da9\",\"height\":315}],\"annotations\":[{\"category_id\":\"3982f2733faf7b9980ae065aaa7551d4\",\"iscrowd\":0,\"bbox\":\"[41,40,499,315]\",\"id\":\"feature-1597997212239\",\"image_id\":\"701f8020ced81602239cf0eb25318da9\"}],\"categories\":[{\"name\":\"gou\",\"supercategory\":\"gou\",\"id\":\"3982f2733faf7b9980ae065aaa7551d4\"}],\"info\":{\"contributor\":\"yzgyhlw\",\"date_created\":\"2020-08-21 15:43:19\"}}";
        String str2 = "{\"tracks\":[],\"shapes\":[{\"type\":\"rectangle\",\"clientID\":1,\"occluded\":false,\"z_order\":0,\"points\":[180.6337890625,284.3525390625,492.82891845703125,586.1411437988281],\"attributes\":[],\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"source\":\"manual\"},{\"type\":\"polygon\",\"clientID\":2,\"occluded\":false,\"z_order\":0,\"points\":[811.9619140625,478.607421875,1009.6856368563695,596.5474254742567,794.617886178863,704.0813008130099,659.3333333333339,572.2655826558275,704.4281842818436,457.7940379403808],\"attributes\":[],\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"source\":\"manual\"},{\"type\":\"polyline\",\"clientID\":3,\"occluded\":false,\"z_order\":0,\"points\":[534.455078125,797.740234375,697.4905149051501,818.5528455284566,870.9322493224954,822.0216802168034,1023.5609756097583,759.5826558265599,1141.5013550135518,655.5176151761534],\"attributes\":[],\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"source\":\"manual\"},{\"type\":\"points\",\"clientID\":4,\"occluded\":false,\"z_order\":0,\"points\":[399.1708984375,953.8369140625,478.95392953929695,1064.8401084010857,638.5203252032534,960.7750677506792,492.829268292684,818.5528455284566,402.6395663956646,874.0542005420066],\"attributes\":[],\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"source\":\"manual\"},{\"type\":\"cuboid\",\"clientID\":5,\"occluded\":false,\"z_order\":0,\"points\":[756.4609375,190.75917738811222,756.4609375,402.29249572753724,1079.0630187988281,190.63374867038874,1079.0630187988281,402.2924957275354,1195.940399335168,121.24453587787684,1195.940399335168,331.99370667934454,874.6548770648587,121.41036926001107,874.6548770648587,332.0351867383142],\"attributes\":[],\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"source\":\"manual\"}],\"tags\":[{\"clientID\":6,\"frame\":0,\"label_id\":\"a50069c76dac34ff4946b7092d476445\",\"group\":0,\"attributes\":[]}],\"hards\":[],\"content\":\"http://10.110.26.164:9000/idas/0cb74c0808d40a2203136174c82dc9e1/730ae669782e4dd8c71b884dc411d20e.jpg\",\"width\":1440,\"height\":1280}";


        System.out.println("str:" + str1);
        JSONObject jsonObject = JSON.parseObject(str1);
        String xmlstr = jsonToXml(str1);
        System.out.println(xmlstr);


    }
}
