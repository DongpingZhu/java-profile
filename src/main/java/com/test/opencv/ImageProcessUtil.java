package com.test.opencv;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

public class ImageProcessUtil {
    static {
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
    }
    private static Mat image = Imgcodecs.imread("src/main/resources/opencv-data/test1.jpg");
    // 1. 二值化：非黑即白
    private static Mat binary(Mat src){
        return null;
    }
    // 2. 自适应二值化
    private static Mat adaptiveBinary(){
        return null;
    }
    // 3. 高斯滤波
    private static Mat gussian(){
        return null;
    }
    // 4.图像锐化
    private static Mat sharpness(){
        return null;
    }
    // 5.图片缩放
    private static Mat resize(){
        return null;
    }
    // 6.腐蚀膨胀
    private static Mat erodingAndDilation(){
        return null;
    }
    // 7.形态学变换
    private static Mat morphologyEx(){
        return null;
    }
    // 8.边缘检测canny
    private static Mat canny(){
        return null;
    }
    // 9. 霍夫线变换
    private static Mat houghLine(){
        return null;
    }
    // 10. 霍夫圆变换
    private static Mat houghCircle(){
        return null;
    }

}
