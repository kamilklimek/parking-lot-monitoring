package com.jslubowski.model;

import com.jslubowski.util.Drawing;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpace {

    // == fields ==
    private String name;
    private Point cornerTopLeft;
    private Point cornerBottomRight;
    private Mat image;
    private Mat imageProcessed;
    private int area;

    // == constructor ==

    public ParkingSpace(int x1, int y1, int x2, int y2, Mat image, String name) {
        this.cornerTopLeft = new Point(x1, y1);
        this.cornerBottomRight = new Point(x2, y2);
        this.name = name;
        this.area = (x2 - x1) * (y2 - y1);
        Rect rectCrop = new Rect(cornerTopLeft, cornerBottomRight);          // crop out the parking spot from the image
        this.image = new Mat(image, rectCrop);
        this.imageProcessed = this.image;
    }

    // == methods ==

    /*
     * this is the main algorithm for detecting empty parking space
     */
    public boolean checkOccupation(){
        this.imageProcessed = preProcess(this.name, this.image, this.imageProcessed);
        List<Rect> rectangles = Drawing.findAndDrawRectangles(this.imageProcessed, this.name, this.area);

        // Are conditions met?
        if(rectangles.size() >= 1) return true;
        else return false;
    }

    /*
     * this is a method to call if you want to draw your rectangles
     */
    private Mat preProcess(String name, Mat image, Mat imageProcessed) {
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
        Imgproc.erode(imageProcessed, imageProcessed, morphologyKernel, new Point(-1, -1), 1);                                      // 5. Erosion
        return imageProcessed;
    }

    // == getters and setters ==
    public Point getCornerTopLeft() {
        return cornerTopLeft;
    }

    public void setCornerTopLeft(Point cornerTopLeft) {
        this.cornerTopLeft = cornerTopLeft;
    }

    public Point getCornerBottomRight() {
        return cornerBottomRight;
    }

    public void setCornerBottomRight(Point cornerBottomRight) {
        this.cornerBottomRight = cornerBottomRight;
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mat getImageProcessed() {
        return imageProcessed;
    }

    public void setImageProcessed(Mat imageProcessed) {
        this.imageProcessed = imageProcessed;
    }
}
