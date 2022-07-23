package com.test.tesseract;

import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class Demo {
    public static void main(String[] args) throws Exception {
        String tessdata_prefix = System.getenv("TESSDATA_PREFIX"); // 加载本地已安装的tesseract引擎
        System.out.println(tessdata_prefix);
        String langPath = "src/main/resources/tessdata";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(langPath);
        tesseract.setLanguage("eng");
        String s = tesseract.doOCR(new File("src/main/resources/opencv-data/ocr.png"));
        System.out.println(s);
    }
}
