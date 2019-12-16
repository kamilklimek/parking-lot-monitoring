package com.jslubowski.dip;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Processing {
    /*
     * this is a method to call if you want to draw your rectangles
     */
    public static Mat preProcess(String name, Mat image, Mat imageProcessed) {
        Mat edges = new Mat();
        int kernelSize = 3;
        Mat morphologyKernel = Mat.ones(3, 3, 1);
        // Algorithm steps
        if (name.equals("P1") || name.equals("P2") || name.equals("P3") || name.equals("P4") || name.equals("P13") || name.equals("P12")) {         // 1. Gaussian blur
            Imgproc.GaussianBlur(image, imageProcessed, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
        }
        Imgproc.Canny(imageProcessed, edges, 230, 300, kernelSize, false);                                          // 2. Canny Edge detection
        imageProcessed = edges;
        Imgproc.dilate(imageProcessed, imageProcessed, morphologyKernel, new Point(-1, -1), 4);                                     // 3. Dilation
        Imgproc.erode(imageProcessed, imageProcessed, morphologyKernel, new Point(-1, -1), 1);                                      // 4. Erosion
        return imageProcessed;
    }
}
