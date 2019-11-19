package com.jslubowski.model;

import org.opencv.core.Mat;

public class ParkingSpace {

    // == fields ==

    private ParkingSpaceCorner corner;
    private int width;
    private int length;
    private Mat image;

    // == constructor ==

    public ParkingSpace(int x, int y, int width, int length, Mat image) {
        this.image = image;
        this.corner = new ParkingSpaceCorner(x, y);
        this.width = width;
        this.length = length;
    }

    // == getters and setters ==

    public ParkingSpaceCorner getCorner() {
        return corner;
    }

    public void setCorner(ParkingSpaceCorner corner) {
        this.corner = corner;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }
}
