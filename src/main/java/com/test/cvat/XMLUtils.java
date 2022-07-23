package com.test.cvat;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

public class XMLUtils {

    public static void main(String[] args) throws Exception {
        dom4j();
    }
    public static void dom4j() throws Exception{
        Document document = DocumentHelper.createDocument();
        Element annotations = document.addElement("annotations");
        Element version = annotations.addElement("version");
        version.setText("1.1");
        annotations.addElement("meta");

        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        prettyPrint.setEncoding("UTF-8");

        File file = new File("cvat-dom4j.xml");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), prettyPrint);

        xmlWriter.setEscapeText(false);
        xmlWriter.write(document);
        xmlWriter.close();

        String s = document.getRootElement().asXML();
        System.out.println(s);
    }
    public static void dom() throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        document.setXmlStandalone(true);

        org.w3c.dom.Element annotations = document.createElement("annotations");
        org.w3c.dom.Element version = document.createElement("version");
        version.setTextContent("1.1");
        org.w3c.dom.Element meta = document.createElement("meta");

        org.w3c.dom.Element track = document.createElement("track");

        annotations.appendChild(version);
        annotations.appendChild(meta);
        annotations.appendChild(track);

        document.appendChild(annotations);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(new DOMSource(document), new StreamResult(new File("cvat-dom.xml")));

        System.out.println("success...");
    }

    public static void sax() throws Exception{

    }
}
