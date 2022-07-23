package com.test.javacv;

import org.opencv.core.Mat;

import java.net.URL;
import java.util.Arrays;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class OpencvLightness {
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final int BASE_VAL = (MIN_VAL + MAX_VAL) >>> 1;
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
        String pic = "src/main/resources/opencv-data/test1.jpg";
        Mat image = imread(pic);
        imshow("Original Image", image);
        int height = image.height();
        int width = image.width();
        int channel = image.channels();
        int depth = image.depth();
        final int value = 50;
//        System.out.println("高，宽，通道，深度依次是："+ height+","+width+","+channel+","+depth);
        Mat result;
        if(value == BASE_VAL){
            result = image;
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
//                    System.out.println(Arrays.toString(vals));
                    for (int k = 0; k < vals.length; k++) {
                        if (flag) {
                            vals[k] = 255 - (vals[k] * f);
                        } else {
                            vals[k] = vals[k] * f;
                        }
                        System.out.println(vals[k]);
                    }
//                    System.out.println(Arrays.toString(vals));
                    ret.put(i, j, vals);
//                    System.out.println(Arrays.toString(ret.get(i,j)));
                }
            }
            result = ret;
        }

        imshow("processed", result);
        waitKey();
    }
}
