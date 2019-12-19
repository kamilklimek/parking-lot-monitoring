package com.jslubowski.model;

import com.jslubowski.dip.Drawing;
import com.jslubowski.dip.Processing;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import java.util.List;

public class ParkingSpace {

    // == fields ==
    private String name;
    private Point cornerTopLeft;
    private Point cornerBottomRight;
    private Mat image;
    private Mat imageProcessed;
    private int area;
    private boolean occupied;

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
    public boolean checkOccupation(List<Rect> rectangles){
        // Are conditions met?
        if(rectangles.size() >= 1) return true;
        else return false;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
