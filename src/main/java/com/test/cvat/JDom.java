package com.test.cvat;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;

public class JDom {
    public static void main(String[] args) throws Exception{
        jdom();

    }
    public static void jdom() throws Exception{
        Element annotations = new Element("annotations");
        Document document = new Document(annotations);

        Element version = new Element("version");
        version.setText("1.1");
        annotations.addContent(version);

        Element meta = new Element("meta");
        annotations.addContent(meta);

        Format compactFormat = Format.getCompactFormat();
        compactFormat.setIndent(" ");
        compactFormat.setEncoding("UTF-8");

        XMLOutputter outputter = new XMLOutputter(compactFormat);
        File file = new File("cvat-jdom.xml");
        outputter.output(document, new FileOutputStream(file));

        System.out.println("success...");

    }
}
