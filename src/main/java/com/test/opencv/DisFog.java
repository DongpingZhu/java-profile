package com.test.opencv;



import org.opencv.core.Mat;

import java.net.URL;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;


public class DisFog {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
        String testPic = "src/main/resources/opencv-data/fog1.jpg";
        Mat image = imread(testPic);
        imshow("Original Image", image);
        Mat processed = disFog(image, 10);
        imshow("Processed Image", processed);
        waitKey(1000);
    }
    public static Mat disFog(Mat mat, int cellSize) { // cellSize 去雾时使用的方框大小
        double[][] dackCha = getDarkChannel(mat, cellSize);
        Mat ret = new Mat(mat.rows(), mat.cols(), mat.type()); //16
        for (int i = 0; i < mat.rows(); i++) {
            for (int j = 0; j < mat.cols(); j++) {
                double[] valsTo = new double[3];
                double[] valsFrom = mat.get(i, j);

                double f = dackCha[i][j] / 255;
                for (int k = 0; k < valsFrom.length; k++) {
                    valsTo[k] = (valsFrom[k] - 127 * f) / (1 - f);
                }
                ret.put(i, j, valsTo);
            }
        }
        return ret;
    }

    private static double[][] getDarkChannel(Mat mat, int cellSize) {
        if (cellSize % 2 == 0)
            cellSize++;
        int hcellSize = cellSize >>> 1;
        double[][] doubles = new double[mat.rows()][mat.cols()];
        for (int i = 0; i < mat.rows(); i++) {
            for (int j = 0; j < mat.cols(); j++) {
                double[] vals = mat.get(i, j);

                doubles[i][j] = vals[0];
                for (int k = 1; k < vals.length; k++) {
                    doubles[i][j] = (doubles[i][j] < vals[k]) ? doubles[i][j] : vals[k];
                }
            }
        }

        double[][] ret = new double[mat.rows()][mat.cols()];
        for (int i = 0; i < mat.rows(); i++) {
            for (int j = 0; j < mat.cols(); j++) {
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
