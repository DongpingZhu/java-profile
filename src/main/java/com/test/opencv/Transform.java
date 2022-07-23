package com.test.opencv;


import java.net.URL;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;



import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class Transform {
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final int BASE_VAL = (MIN_VAL + MAX_VAL) >>> 1;

    public static void main(String[] args) throws Exception {
        System.out.println(Core.VERSION);
        System.out.println(Core.NATIVE_LIBRARY_NAME);
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
        String testPic = "src/main/resources/opencv-data/5.jpg";
//        rgb2gray(testPic);
//        adjustSaturation(testPic,10);
        adjustContrast(testPic,150);
//        adjustLightness(testPic,150);
//        transposition(testPic);
    }

    // 1. 彩色图像转换为灰度图像
    private static void rgb2gray(String pic) throws Exception{
        // 读取并显示图像
        Mat image = imread(pic);
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        // 创建输出单通道图像
        Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8SC1);
        // 进行图像色彩空间转换
        cvtColor(image, grayImage, COLOR_RGB2GRAY);
        imshow("Processed Image", grayImage);
        imwrite("src/main/resources/opencv-data/rgb2gray_test1.jpg", grayImage);
        waitKey();
    }
    // 2. 调节图像饱和度(S)
    private static void adjustSaturation(String pic, int value) throws Exception{
        Mat image = imread(pic);
        Mat processdImage = new Mat();
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        if(value == BASE_VAL){
            processdImage = image;
        }else {
            Mat hsv = new Mat();
            double f;
            boolean flag = false;
            if (value > BASE_VAL) {
                flag = true;
                f = 1.0 * (value - BASE_VAL) / BASE_VAL;
            } else {
                f = 1.0 * value / BASE_VAL;
            }
            Imgproc.cvtColor(image, hsv, Imgproc.COLOR_BGR2HSV);
            for (int i = 0; i < hsv.height(); i++) {
                for (int j = 0; j < hsv.width(); j++) {
                    double[] vals = hsv.get(i, j);
                    if (flag) {
                        vals[1] = vals[1] + (MAX_VAL - vals[1]) * f;
                    } else {
                        vals[1] = vals[1] * f;
                    }
                    hsv.put(i, j, vals);
                }
            }
            Imgproc.cvtColor(hsv, processdImage, Imgproc.COLOR_HSV2BGR);
        }
        imshow("Processed Image", processdImage);
        imwrite("src/main/resources/opencv-data/saturation_test1.jpg", processdImage);
        waitKey();
    }
    // 3. 调节图像对比度
    private static void adjustContrast(String pic, int value) throws Exception{
        Mat image = imread(pic);
        Mat processdImage;
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        if(value == BASE_VAL){
            processdImage = image;
        }else {
            Mat ret = new Mat(image.height(), image.width(), image.type());
            double maxx = -1, minn = 300.0;
            for (int i = 0; i < image.height(); i++) {
                for (int j = 0; j < image.width(); j++) {
                    double[] vals = image.get(i, j);
                    for (int k = 0; k < vals.length; k++) {
                        maxx = maxx > vals[k] ? maxx : vals[k];
                        minn = minn < vals[k] ? minn : vals[k];
                    }
                }
            }
            double midd = (maxx + minn) / 2;
            double a;
            if(value>BASE_VAL){
                if(value==MAX_VAL) value--;
                a = BASE_VAL/(1.0*(MAX_VAL-value));
            }else{
                a = (1.0 * value) / BASE_VAL;
            }
            double b = midd * (1 - a);
            for (int i = 0; i < image.height(); i++) {
                for (int j = 0; j < image.width(); j++) {
                    double[] vals = image.get(i, j);
                    for (int k = 0; k < vals.length; k++) {
                        vals[k] = a * vals[k] + b;
                    }
                    ret.put(i, j, vals);
                }
            }
            processdImage = ret;
        }
        imshow("Processed Image", processdImage);
        imwrite("src/main/resources/opencv-data/contrast_test1.jpg", processdImage);
        waitKey();
    }
    // 4. 调节图像亮度
    private static void adjustLightness(String pic, int value) throws Exception{
        Mat image = imread(pic);
        Mat processdImage;
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        if(value == BASE_VAL){
            processdImage = image;
        }else {
            Mat ret = new Mat(image.height(), image.width(), image.type());
            int hei = image.height();
            int wid = image.width();
            double f;
            boolean flag = false;
            if (value > BASE_VAL) {
                flag = true;
                f = 1.0 * (MAX_VAL - value) / BASE_VAL;
            } else {
                f = 1.0 * value / BASE_VAL;
            }
            for (int i = 0; i < hei; i++) {
                for (int j = 0; j < wid; j++) {
                    double[] vals = image.get(i, j);
                    for (int k = 0; k < vals.length; k++) {
                        if (flag) {
                            vals[k] = 255 - (vals[k] * f);
                        } else {
                            vals[k] = vals[k] * f;
                        }
                    }
                    ret.put(i, j, vals);
                }
            }
            processdImage = ret;
        }
        imshow("Processed Image", processdImage);
        imwrite("src/main/resources/opencv-data/lightness_test1.jpg", processdImage);
        waitKey();

    }
    // 5. 图像矩阵转置
    private static void transposition(String pic) throws Exception{
        Mat image = imread(pic);
        Mat processdImage;
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        Mat ret = new Mat(image.width(), image.height(), image.type());
        for (int i = 0; i < image.height(); i++) {
            for (int j = 0; j < image.width(); j++) {
                double[] vals = image.get(i, j);
                ret.put(j, i, vals);
            }
        }
        processdImage = ret;
        imshow("Processed Image", processdImage);
        imwrite("src/main/resources/opencv-data/transposition_test1.jpg", processdImage);
        waitKey();
    }
    // 6. 图像降噪：线性滤波（均值，高斯，中值，双边）
    private static void Filter(String pic) throws Exception{
        Mat image = imread(pic);
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        Mat ret = new Mat(image.size(),image.type());
        Imgproc.blur(image,ret,new Size(3,3),new Point(-1,-1));
        Imgproc.GaussianBlur(image, ret, new Size(11,11), 7,7);
        Imgproc.medianBlur(image, ret, 9);
        Imgproc.bilateralFilter(image, ret, 2, 150, 9);
        imshow("双边滤波(降噪)", ret);
    }

}
