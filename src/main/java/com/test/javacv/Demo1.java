package com.test.javacv;


import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Size;

import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.util.Random;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_core.flip;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;

public class Demo1 {
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final int BASE_VAL = (MIN_VAL + MAX_VAL) >>> 1;
    public static void main(String[] args) throws Exception {

        String testPic = "src/main/resources/opencv-data/test1.jpg";
        Filter(testPic);
//        adjustLightness(testPic,20);

        BufferedImageOp bufferedImageOp;
        BufferedImage bufferedImage;
//        ConvolveOp convolveOp = new ConvolveOp();
//        BandCombineOp bandCombineOp = new BandCombineOp();


    }


//     4. 调节图像亮度
    private static void adjustLightness(String pic, int value) throws Exception {
        Mat image = imread(pic);
        Mat processdImage;
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);
        if (value == BASE_VAL) {
            processdImage = image;
        } else {
            Mat ret = new Mat(image.rows(), image.cols(), image.type());
            int hei = image.rows();
            int wid = image.cols();
            UByteIndexer srcI = image.createIndexer();
            Mat dst = new Mat(hei, wid, CV_8U);

            int channels = image.channels();
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
//                    double[] vals = image.get(i, j);
                    for(int c = 0;c<channels;c++){
                        srcI.put(i,j,c);
                    }

                    for (int k = 0; k < 2; k++) {
                        if (flag) {
                            srcI.put(i,j, (int) (255 - (k * f)));
                        } else {
                            srcI.put(i,j, (int) (k * f));
                        }
                    }
                }
            }
            processdImage = ret;
        }
        imshow("Processed Image", image);
        waitKey();
    }





    // 6. 图像降噪：线性滤波（均值，高斯，中值，双边）
    private static void Filter(String pic) throws Exception{
        Mat image = imread(pic);
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);

//        GaussianBlur(image,image, new Size(3,3),0);
//        blur(image,image,new Size(3,3));
//        medianBlur(image,image,3);
//        bilateralFilter();
//        flip(image,image,-1); // 0：垂直翻转 1：水平翻转 -1：同时翻转
        Mat salt = salt(image, 3000);
//        imwrite("src/main/resources/opencv-data/5-salt.jpg", salt);



        imshow("processed", salt);
        waitKey();

    }
    private static Mat salt(Mat image, int n){
        Random random = new Random();
        UByteIndexer indexer = image.createIndexer();
        int channels = image.channels();
        for(int i=0;i<n;i++){
            int i1 = random.nextInt(image.rows());
            int i2 = random.nextInt(image.cols());
            for(int c = 0;c<channels;c++){
                indexer.put(i1,i2,c,255);
            }
        }

        return image;
    }
}
