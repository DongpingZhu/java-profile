package com.test.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

import static org.opencv.core.Core.*;
import static org.opencv.core.CvType.CV_16S;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.Sobel;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class EdgeDetection {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
        String testPic = "src/main/resources/opencv-data/test1.jpg";
        Mat image = imread(testPic);
        imshow("Original Image", image);
        Mat processed = edgeDetection(image);
//        Mat processed = oilPainting(image);
        imshow("Processed Image", processed);
        waitKey(300);
    }
    public static Mat edgeDetection(Mat img) {
        Mat clone = img.clone();
        int sizex = 5, sizey = 5;
        double dx = 0, dy = 0;
        Imgproc.GaussianBlur(clone, clone, new Size(sizex, sizey), dx, dy, BORDER_DEFAULT);
        Mat grad_x = new Mat(), grad_y = new Mat(), src_gray = new Mat();
        Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();
        cvtColor(clone, src_gray, Imgproc.COLOR_BGR2GRAY);
        int scale = 1;
        int delta = 0;
        int ddepth = CV_16S;
        Sobel(src_gray, grad_x, ddepth, 1, 0, 3, scale, delta, BORDER_DEFAULT);
        convertScaleAbs(grad_x, abs_grad_x);

        Sobel(src_gray, grad_y, ddepth, 0, 1, 3, scale, delta, BORDER_DEFAULT);
        convertScaleAbs(grad_y, abs_grad_y);
        Mat ret = new Mat();
        addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, ret);
        return ret;
    }
    public static Mat oilPainting(Mat img) {
        Mat mat1 = edgeDetection(img);
        Mat ret = new Mat();
        Imgproc.GaussianBlur(img, ret, new Size(11, 11), 20, 20, Core.BORDER_DEFAULT);
        for (int i = 0; i < img.height(); i++) {
            for (int j = 0; j < img.width(); j++) {
                double[] doubles = img.get(i, j);
                double[] doubles1 = mat1.get(i, j);
                for (int k = 0; k < doubles.length; k++) {
                    doubles[k] -= 1 * doubles1[0];
                }
                ret.put(i, j, doubles);
            }
        }
        return ret;
    }

}
