package com.test.opencv;


import org.opencv.core.Core;
        import org.opencv.core.CvType;
        import org.opencv.core.Mat;
        import org.opencv.highgui.HighGui;
        import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

public class Contrast {
    public static void main(String[] args) {
        /*	1.加载本地动态链接库
         * 	加载动态链接库有很多种方法，也可以写成static静态代码块。
         */
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java411.dll");
        System.load(url.getPath());

        /*	2 读取测试图片
         *	imread：输入图片所在地址，返回Mat矩阵对象。
         *	Mat对象中保存着加载图片的大小、通道、像素数据等等与图片有关的数据
         */
        Mat image = Imgcodecs.imread("src/main/resources/opencv-data/5.jpg");
        // 3. 设置图片的大小，让图片显示的时候小一点
        //Imgproc.resize(image, image, new Size(image.cols()/2,image.rows()/2));

        HighGui.imshow("原图", image);
        HighGui.waitKey();

        transformation_1(image);

//        transformation_2(image);

    }

    /**
     * 线性变化
     * 最常用的是线性变换．即g(i,j)=α⋅f(i,j)+β
     * f(i,j)是原像素值,g(i,j)是变换后的像素值.
     * α调整对比度,β调整亮度.有时也称之为gain和bias参数.
     * 线性变换有个问题,如例图（下面附上）,α=1.3 and β=40,提高原图亮度的同时,
     * 导致云几乎看不见了.如果要看见云的话,山的亮度又不够.
     * @param image
     */
    public static void transformation_1(Mat image) {
        // 初始化一个与输入图像大小深度一致的无数据的Mat对象，用来存储调整后的图像数据
        Mat dst = new Mat(image.size(), image.type());
        //获取图像通道数
        int channels = image.channels();
        //初始化需要的变量
        double[] pixelArr = new double[3]; //存储像素的值
        float alpha = 1.3f; //调整对比度参数
        float bate = 40f; //调整亮度参数
        //此处也可使用convertTo()方法，传参请看下面伽马校正里的详解
//        image.convertTo(dst,-1,4,100);
        for (int i = 0, rlen = image.rows(); i < rlen; i++) {
            for (int j = 0, clen = image.cols(); j < clen; j++) {
                if (channels == 3) {//1 图片为3通道即(B,G,R)
                    pixelArr = image.get(i, j).clone();
                    pixelArr[0] = pixelArr[0]*alpha+bate;//B
                    pixelArr[1] = pixelArr[1]*alpha+bate;//G
                    pixelArr[2] = pixelArr[2]*alpha+bate;//R
                    dst.put(i, j, pixelArr);
                } else {//2 图片为单通道即灰度图
                    pixelArr=image.get(i, j).clone();
                    dst.put(i, j, pixelArr[0]*alpha+bate);
                }
            }
        }
        HighGui.imshow("线性变化", dst);
        HighGui.waitKey();
    }

    /**
     * 伽马校正
     * 伽马校正对图像的修正作用就是通过增强低灰度或高灰度的细节实现的
     * 值越小，对图像低灰度部分的扩展作用就越强，值越大，对图像高灰度部分的扩展作用就越强，
     * 通过不同的值，就可以达到增强低灰度或高灰度部分细节的作用。
     * 在对图像进行伽马变换时，如果输入的图像矩阵是CV_8U,在进行幂运算时，大于255的值会自动截断为255；
     * 所以，先将图像的灰度值归一化到【0,1】范围，然后再进行幂运算
     * @param image
     */
    public static void transformation_2(Mat image) {

        //定义2个与输入图像大小类型一致的空对象
        Mat dst = new Mat(image.size(),image.type());
        Mat dst_1 = new Mat(image.size(),image.type());
        /*
         * 缩放并转换到另外一种数据类型：
         * dst：目的矩阵；
         * type：需要的输出矩阵类型，或者更明确的，是输出矩阵的深度，如果是负值（常用-1）则输出矩阵和输入矩阵类型相同；
         * scale:比例因子（输入矩阵参数*比例因子）；
         * shift：将输入数组元素按比例缩放后添加的值（第三个参数处理后+第四个参数）；
         * CV_64F:64 -表示双精度 32-表示单精度 F - 浮点  Cx - 通道数,例如RGB就是三通道
         */
        image.convertTo(dst, CvType.CV_64F, 1.0 / 255, 0);

        /*  将每个数组元素提升为幂：
         *  对于非整数幂指数，将使用输入数组元素的绝对值。 但是，可以使用一些额外的操作获得负值的真实值。
         *  对于某些幂值（例如整数值0.5和-0.5），使用了专用的更快算法。
         *  不处理特殊值（NaN，Inf）。
         *  @param 输入数组。
         *  @param 幂的幂指数。
         *  @param 输出数组，其大小和类型与输入数组相同。
         */
        Core.pow(dst, 0.7, dst_1);
        /* 缩放并转换到另外一种数据类型：
         * CV_8UC1---8位无符号的单通道---灰度图片
         * CV_8UC3---8位无符号的三通道---RGB彩色图像
         * CV_8UC4---8位无符号的四通道---带透明色的RGB图像
         */
        dst_1.convertTo(dst_1, CvType.CV_8U,255,0);

        HighGui.imshow("伽马校正", dst_1);
        HighGui.waitKey();
    }
}

