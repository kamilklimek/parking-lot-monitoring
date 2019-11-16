package com.jslubowski.dip;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Skeletonization {

    // == fields ==
    // these are structuring elements needed to skeletonize the image

    private static final int[][] E1 = new int[][]{{-1,-1,-1},{0,1,0},{1,1,1}};
    private static final int[][] E2 = new int[][]{{1,0,-1},{1,1,-1},{1,0,-1}};
    private static final int[][] E3 = new int[][]{{1,1,1},{0,1,0},{-1,-1,-1}};
    private static final int[][] E4 = new int[][]{{-1,0,1},{-1,1,1},{-1,0,1}};
    private static final int[][] E5 = new int[][]{{0,-1,-1},{1,1,-1},{0,1,0}};
    private static final int[][] E6 = new int[][]{{0,1,0},{1,1,-1},{0,-1,-1}};
    private static final int[][] E7 = new int[][]{{0,1,0},{-1,1,1},{-1,-1,0}};
    private static final int[][] E8 = new int[][]{{-1,-1,0},{-1,1,1},{0,1,0}};
    private static final int[][][] structuringElements = new int[][][]{E1, E2, E3, E4, E5, E6, E7, E8};


    // == static methods ==

    private static Mat hitOrMiss(Mat image, int[][] structuringElement){
        Mat ret = new Mat();
        Mat kernel = new Mat(3,3, CvType.CV_16S);

        // transposing 2D array into Mat object
        for(int row = 0 ; row < 3 ; row++){
            for(int col = 0 ; col < 3; col++){
                kernel.put(row, col, structuringElement[row][col]);
            }
        }

        // HitOrMiss operation
        Imgproc.morphologyEx(image, ret, Imgproc.MORPH_HITMISS, kernel);
        return ret;
    }


    private static Mat thinning(Mat image , int[][] structuringElement){
        Mat ret = new Mat();
        Mat subtraction = hitOrMiss(image, structuringElement);
        Core.subtract(image, subtraction, ret);
        return ret;
    }


    // this is the method you should call in your main class to extract a skeleton
    public static Mat sequentialThinning(Mat image){
        boolean continueThinning = true;
        Mat beforeThinning = image.clone();
        int i = 1;
        while(continueThinning) {
            System.out.println("Iteration number " + i);
            for(int[][] E : structuringElements) {
                image = Skeletonization.thinning(image, E);
            }
            if(comparison(image, beforeThinning)){
                continueThinning = false;
            }else{
                beforeThinning = image.clone();
                i++;
            }
        }
        return image;
    }

    private static boolean comparison(Mat img1, Mat img2){
        boolean isTheSame = false;
        if((img1.cols() == img2.cols()) && (img1.rows() == img2.rows()) && (img1.type() == img2.type())){
            Mat dst = new Mat();
            Core.bitwise_xor(img1, img2, dst);
            if(Core.countNonZero(dst) == 0){
                isTheSame = true;
            }
            else isTheSame = false;
        }else isTheSame = false;

        return isTheSame;
    }

}
