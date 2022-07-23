package com.test.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlTest {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document document = db.parse("src/main/java/com/test/dom/test.xml");
        NodeList nodeList = document.getElementsByTagName("object");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.hasChildNodes());
            if(node.hasChildNodes()){
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
//                        System.out.print(childNodes.item(j).getNodeName() + ":");
//                        System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                    }
                }
            }
        }



    }
}
