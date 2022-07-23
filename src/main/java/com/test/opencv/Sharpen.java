package com.test.opencv;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import java.net.URL;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Sharpen {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());
        String testPic = "src/main/resources/opencv-data/test1.jpg";
        Mat image = imread(testPic);
        imshow("Original Image", image);
        for (int i = 3; i < 20; i += 2) {
            showImg(sharpen(image, i, 2), 100);
            System.out.println(i);
        }
    }
    public static void showImg(Mat imread, int len) {
        HighGui.namedWindow("test", HighGui.WINDOW_AUTOSIZE);
        HighGui.imshow("test", imread);
        HighGui.waitKey(len);
    }
    private static int standardizeCellSize(int cellSize) {
        return cellSize % 2 == 0 ? cellSize + 1 : cellSize;
    }
    private static double gaussianFunction(double x, double y, double variance) {
        return 1.0 / (2 * Math.PI * variance * variance) * Math.exp((-x * x - y * y) / (2 * variance * variance));
    }

    private static boolean chackRange(int i, int j, int hei, int wid) {
        return i >= 0 && i < hei && j >= 0 && j < wid;
    }

    private static Mat filtering(Mat mat, double[][] weightMatrix) {
        Mat ret = new Mat(mat.height(), mat.width(), mat.type());
        double[][][] m = new double[mat.height()][mat.width()][mat.get(0, 0).length];
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                m[i][j] = mat.get(i, j);
            }
        }

        int halfCellSize = weightMatrix.length >>> 1;
        int hei = mat.height();
        int wid = mat.width();
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                double[] suma = new double[3];
                double sumb = 0;
                for (int k = 0; k < weightMatrix.length; k++) {
                    for (int l = 0; l < weightMatrix.length; l++) {
                        int loci = i - (halfCellSize - k);
                        int locj = j - (halfCellSize - l);
                        if (chackRange(loci, locj, mat.height(), mat.width())) {
                            sumb += weightMatrix[k][l];
                            for (int n = 0; n < suma.length; n++) {
                                suma[n] += weightMatrix[k][l] * m[loci][locj][n];
                            }
                        }
                    }
                }
                for (int k = 0; k < suma.length; k++) {
                    suma[k] /= sumb;
                }
                ret.put(i, j, suma);
            }
        }
        return ret;
    }

    public static Mat gaussianFiltering(Mat mat, int cellSize, double variance) {
        //通过高斯函数计算每一个格子的权值
        cellSize = standardizeCellSize(cellSize);
        int hCellSize = cellSize >>> 1;
        double[][] tCell = new double[cellSize][cellSize];
        double sum = 0;
        for (int i = 0; i < tCell.length; i++) {
            for (int j = 0; j < tCell[i].length; j++) {
                tCell[i][j] = gaussianFunction(hCellSize - i, hCellSize - j, variance);
                sum += tCell[i][j];
            }
        }
        for (int i = 0; i < tCell.length; i++) {
            for (int j = 0; j < tCell[i].length; j++) {
                tCell[i][j] = tCell[i][j] * 100 / sum;
            }
        }
        return filtering(mat, tCell);
    }

    public static Mat averageFiltering(Mat mat, int cellSize) {
        Mat ret = new Mat(mat.height(), mat.width(), mat.type());
        double[][][] mm = new double[mat.height()][mat.width()][mat.get(0, 0).length];
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                mm[i][j] = mat.get(i, j);
            }
        }
        cellSize = standardizeCellSize(cellSize);
        int halfCellSiz = cellSize >> 1;
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                double[] to = new double[3];
                int fi = i - halfCellSiz, ti = i + halfCellSiz, fj = j - halfCellSiz, tj = j + halfCellSiz;
                for (int k = 0; k < to.length; k++) {
                    double sum = 0;
                    int cnt = 0;
                    for (int l = fi; l <= ti; l++) {
                        for (int m = fj; m <= tj; m++) {
                            if (l >= 0 && l < mat.height() && m >= 0 && m < mat.width()) {
                                sum += mm[l][m][k];
                                cnt++;
                            }
                        }
                    }
                    to[k] = sum / cnt;
                }
                ret.put(i, j, to);
            }
        }
        return ret;
    }

    public static Mat sharpen(Mat mat, int cellSize, int factor) {
        Mat filter = averageFiltering(mat, cellSize);
        Mat ret = new Mat(mat.height(), mat.width(), mat.type());
        for (int i = 0; i < ret.height(); i++) {
            for (int j = 0; j < ret.width(); j++) {
                double[] rgb = mat.get(i, j);
                double[] frgb = filter.get(i, j);
                for (int k = 0; k < rgb.length; k++) {
                    rgb[k] += factor * (rgb[k] - frgb[k]);
                }
                ret.put(i, j, rgb);
            }
        }
        return ret;
    }
}
