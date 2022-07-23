package com.test.javacv;

import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteArrayIndexer;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import static org.bytedeco.opencv.global.opencv_core.pow;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;


public class EnhanceLightness {
    private static final int MIN_VAL = 0;
    private static final int MAX_VAL = 255;
    private static final int BASE_VAL = (MIN_VAL + MAX_VAL) >>> 1;
    public static void main(String[] args) {
        final int value = 150;
        String pic = "src/main/resources/opencv-data/5.jpg";
        Mat image = imread(pic);
        imshow("Original Image", image);
        int height = image.rows();
        int width = image.cols();
        int channel = image.channels();
        int depth = image.depth();
        UByteIndexer indexer = image.createIndexer();
//        System.out.println("高，宽，通道，深度依次是："+ height+","+width+","+channel+","+depth);
        if(value == BASE_VAL){

        }else {
            double f;
            boolean flag = false;
            if(value>BASE_VAL){
                flag = true;
                f = 1.0 * (MAX_VAL-value) / BASE_VAL;
            }else {
                f = 1.0 * value / BASE_VAL;
            }
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    int i0 = indexer.get(i, j, 0);
                    int i1 = indexer.get(i, j, 1);
                    int i2 = indexer.get(i, j, 2);
                    double[] vals = new double[]{i0,i1,i2};
//                    System.out.println(Arrays.toString(vals));
                    for(int k=0; k<channel;k++){
                        if(flag){
                            vals[k] = 255-(vals[k]*f);
                        }else {
                            vals[k] = vals[k]*f;
                        }
                        indexer.put(i,j,k,(int)vals[k]);
                    }
                }
            }
        }
        imshow("processed", image);
        waitKey();

    }


}


