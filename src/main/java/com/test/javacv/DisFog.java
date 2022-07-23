package com.test.javacv;


import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.Mat;

import java.net.URL;

import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;


public class DisFog {
    public static void main(String[] args) {
        String testPic = "src/main/resources/opencv-data/fog1.jpg";
        Mat image = imread(testPic);
        imshow("Original Image", image);
        Mat processed = disFog(image, 10);
        imshow("Processed Image", processed);
        waitKey(10000);
    }
    public static Mat disFog(Mat mat, int cellSize) { // cellSize 去雾时使用的方框大小
        UByteIndexer indexer = mat.createIndexer();
        int height = mat.rows();
        int weight = mat.cols();
        int channel = mat.channels();

        Mat mat1 = new Mat(height,weight, mat.type());
        UByteIndexer indexer1 = mat1.createIndexer();

        double[][] dackCha = getDarkChannel(mat, cellSize);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                double[] valsTo = new double[3];
                int i0 = indexer.get(i, j, 0);
                int i1 = indexer.get(i, j, 1);
                int i2 = indexer.get(i, j, 2);
                double[] valsFrom = new double[]{i0,i1,i2};
                double f = dackCha[i][j] / 255;
                for (int k = 0; k < channel; k++) {
                    valsTo[k] = (valsFrom[k] - 127 * f) / (1 - f);
                    indexer.put(i,j,k,(int)valsTo[k]);
                }
            }
        }
        return mat;
    }

    private static double[][] getDarkChannel(Mat mat, int cellSize) {
        UByteIndexer indexer = mat.createIndexer();
        int height = mat.rows();
        int weight = mat.cols();
        if (cellSize % 2 == 0)
            cellSize++;
        int hcellSize = cellSize >>> 1;
        double[][] doubles = new double[mat.rows()][mat.cols()];
        for (int i = 0; i <height; i++) {
            for (int j = 0; j < weight; j++) {
//                double[] vals = mat.get(i, j);
                int i0 = indexer.get(i, j, 0);
                int i1 = indexer.get(i, j, 1);
                int i2 = indexer.get(i, j, 2);
                double[] vals = new double[]{i0,i1,i2};

                doubles[i][j] = vals[0];
                for (int k = 1; k < vals.length; k++) {
                    doubles[i][j] = (doubles[i][j] < vals[k]) ? doubles[i][j] : vals[k];
                }
            }
        }

        double[][] ret = new double[mat.rows()][mat.cols()];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                double maxx = 300;
                for (int k = 0; k < cellSize; k++) {
                    for (int l = 0; l < cellSize; l++) {
                        int locx = i + (k - hcellSize);
                        int locy = j + (l - hcellSize);
                        if (locx >= 0 && locx < ret.length && locy >= 0 && locy < ret[locx].length)
                            maxx = (maxx < doubles[locx][locy]) ? maxx : doubles[locx][locy];
                    }
                }
                ret[i][j] = maxx;
            }
        }
        return ret;
    }
}
