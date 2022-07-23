package com.test.opencv.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

public class TestImageUtil {
    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // 需要把dll库放入环境变量，常量返回一个字符串，loadLibrary在环境变量中寻找
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath()); // 另一种加载方式，指定具体位置加载
    }

    private static Mat imread = Imgcodecs.imread("src/main/resources/opencv-data/test1.jpg");

    public static void showImg(Mat imread, int len) {
        HighGui.namedWindow("test", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("test", imread);
        HighGui.waitKey(len);
    }

    public static class TestBase {

        public void testShiftSaturability() {
            for (int i = 0; i <= 255; i += 50)
                showImg(ImageUtil.Base.shiftSaturability(imread, i), 1000);
        }


        public void testShiftContrast() {
            for (int i = 0; i <= 255; i += 50)
                showImg(ImageUtil.Base.shiftContrast(imread, i), 1000);
        }


        public void testShiftBrightness() {
            for (int i = 0; i <= 255; i += 50) {
                showImg(ImageUtil.Base.shiftBrightness(imread, i), 1000);
            }
        }


        public void testTransposition() {
            showImg(ImageUtil.Base.transposition(imread), 0);
        }
    }


    public static class TestFilter {

        public void testGaussianFiltering() {
            for (int i = 3; i < 20; i += 2) {
                showImg(ImageUtil.Filter.gaussianFiltering(imread, i, 100), 100);
                System.out.println(i);
            }
        }



        public void testAverageFiltering() {
            for (int i = 3; i < 20; i += 2) {
                showImg(ImageUtil.Filter.averageFiltering(imread, i), 100);
                System.out.println(i);
            }
        }


        public void testSharpen() {
            for (int i = 3; i < 20; i += 2) {
                showImg(ImageUtil.Filter.sharpen(imread, i, 2), 100);
                System.out.println(i);
            }
        }


        public void testDisFog() {
            showImg(ImageUtil.Filter.disFog(imread, 20), 0);
        }


        public void testEdgeDetection() {
            showImg(ImageUtil.Filter.edgeDetection(imread), 0);
        }



        public void testOilPainting() {
            showImg(ImageUtil.Filter.oilPainting(imread), 0);
        }
    }


}
