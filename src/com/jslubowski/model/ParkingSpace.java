package com.jslubowski.model;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpace {

    // == fields ==
    private String name;
    private Point cornerTopLeft;
    private Point cornerBottomRight;
    private Mat image;
    private Mat imageProcessed;

    // == constructor ==

    public ParkingSpace(int x1, int y1, int x2, int y2, Mat image, String name) {
        this.cornerTopLeft = new Point(x1, y1);
        this.cornerBottomRight = new Point(x2, y2);
        this.name = name;
        // crop out the parking spot from the image
        Rect rectCrop = new Rect(cornerTopLeft, cornerBottomRight);
        this.image = new Mat(image, rectCrop);
        this.imageProcessed = this.image;
    }

    // == methods ==
    public void saveProcessedImage(String projectFilePath, String filename, String state){
        Imgcodecs.imwrite(projectFilePath + filename + "_" + name + "_" + state + ".jpg", imageProcessed);
    }

    // this is the main algorithm for detecting empty parking space
    public void isOccupied(){
        Mat edges = new Mat();
        int kernelSize = 3;
        Mat morphologyKernel = Mat.ones(3, 3, 1);

        // ONLY FOR DEBUGGING - DELETE LATER
        String projectFilePath = "D:\\wszystko\\studia\\semestr 7\\praca inzynierska\\ParkingLotMonitoring\\src\\data\\";
        String filename = "example_lot2"; // name of the picture to work on
        //DELETE

        // Algorithm steps
        // 1. Gaussian blur
        Imgproc.GaussianBlur(image, imageProcessed, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
        saveProcessedImage(projectFilePath, filename, "blurred");

        // 2. Canny Edge detection
        Imgproc.Canny(imageProcessed, edges, 230, 300, kernelSize, false);
        this.imageProcessed = edges;
        saveProcessedImage(projectFilePath, filename, "canny");

        // 4. Dilation
        Imgproc.dilate(imageProcessed, imageProcessed, morphologyKernel, new Point(-1, -1), 3);
        saveProcessedImage(projectFilePath, filename, "dilated");

        // 5. Erosion
        Imgproc.erode(imageProcessed, imageProcessed, morphologyKernel, new Point(-1, -1), 1);
        saveProcessedImage(projectFilePath, filename, "eroded");

        // 6. Find and draw rectangles around contours
        findAndDrawRectangles();
        saveProcessedImage(projectFilePath, filename, "rectangles");

        // 7. Are conditions met?
        // TODO implement it completely



    }

        // this is a method to call if you want to draw your rectangles
        private List<Rect> findAndDrawRectangles(){
            List<MatOfPoint> contours = findAndDrawContours();
            List<Rect> retContours = new ArrayList<>();
            Mat rectangles = Mat.zeros(imageProcessed.size(), CvType.CV_8UC3);
            for(MatOfPoint c: contours) {
                    Rect rect = Imgproc.boundingRect(c);
                    if(rect.area() > 500) {
                        Imgproc.rectangle(rectangles, rect, new Scalar(0, 255, 0), 1);
                        retContours.add(rect);
                    }
                }
            this.imageProcessed = rectangles;
            return retContours;
        }


        private List<MatOfPoint> findAndDrawContours(){
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(imageProcessed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            Mat drawing = Mat.zeros(imageProcessed.size(), CvType.CV_8UC3);
            Scalar color = new Scalar(0, 255, 0);
            for (int i = 0; i < contours.size(); i++) {
                if(Imgproc.contourArea(contours.get(i)) > 2) {
                    Imgproc.drawContours(drawing, contours, i, color, 2, Imgproc.LINE_4, hierarchy, 0, new Point());
                }
            }
            this.imageProcessed = drawing;
            return contours;
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
