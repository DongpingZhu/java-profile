package com.test.cvat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Map;
import java.util.Set;

public class JSON2XML {
    public static void main(String[] args) throws Exception {

        String str1 = "{\"images\":[{\"file_name\":\"701f8020ced81602239cf0eb25318da9.jpg\",\"flickr_url\":\"http://117.73.9.199:9000/ida/c5786c2c7a6b932f6c5bfdac0b895b1d/701f8020ced81602239cf0eb25318da9.jpg\",\"width\":499,\"date_captured\":\"2020-08-21 15:00:38\",\"id\":\"701f8020ced81602239cf0eb25318da9\",\"height\":315}],\"annotations\":[{\"category_id\":\"3982f2733faf7b9980ae065aaa7551d4\",\"iscrowd\":0,\"bbox\":\"[41,40,499,315]\",\"id\":\"feature-1597997212239\",\"image_id\":\"701f8020ced81602239cf0eb25318da9\"}],\"categories\":[{\"name\":\"gou\",\"supercategory\":\"gou\",\"id\":\"3982f2733faf7b9980ae065aaa7551d4\"}],\"info\":{\"contributor\":\"yzgyhlw\",\"date_created\":\"2020-08-21 15:43:19\"}}";
        String xml = readJsonFile("D:\\idea_Workspace\\gradle-java-demo\\src\\main\\java\\com\\test\\cvat\\cvat-image.xml");
        String json = readJsonFile("D:\\idea_Workspace\\gradle-java-demo\\src\\main\\java\\com\\test\\cvat\\test1.json");// 不含有重复key
        String json1 = readJsonFile("D:\\idea_Workspace\\gradle-java-demo\\src\\main\\java\\com\\test\\cvat\\annotations.json"); // 含有重复key

//        String s1 = xml2json(xml);
//        System.out.println(s1);
        String s = json2xml(json);
        System.out.println(s);



    }





    public static String json2xml(String str1) throws Exception{

        if(StringUtils.isEmpty(str1)) {
            return null;
        }
        String XML_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
        XmlMapper xmlMapper = new XmlMapper();
        StringWriter w = new StringWriter();
        JSONObject jsonObject = JSON.parseObject(str1);

        xmlMapper.writeValue(w, jsonObject);
        String xml = w.toString();
        xml = xml.replaceAll("JSONObject", "annotations").replaceAll("", "");
        xml = XML_PREFIX + xml;
        return xml;
    }
    public static String xml2json(String xml){
        if(StringUtils.isEmpty(xml)) {
            return null;
        }
        String XML_PREFIX_HEAD = "<?";
        String XML_PREFIX_TAIL = "?>";
        String XML_ROOT_START = "";
        String XML_ROOT_END = "";
        if(xml.indexOf(XML_PREFIX_HEAD) > -1 && xml.indexOf(XML_PREFIX_TAIL) > -1){
            xml = xml.substring(xml.indexOf(XML_PREFIX_TAIL) + 2);
        }
        xml = XML_ROOT_START + xml + XML_ROOT_END;
        StringWriter w = new StringWriter();
        try(JsonParser jp = new XmlMapper().getFactory().createParser(xml); JsonGenerator jg = new ObjectMapper().getFactory().createGenerator(w)){
            while (jp.nextToken() != null) {
                jg.copyCurrentEvent(jp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return w.toString();
    }
    public static String readJsonFile(String fileName) {
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
}
