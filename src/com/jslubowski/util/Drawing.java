package com.jslubowski.util;

import com.jslubowski.model.ParkingSpace;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Drawing {

    public static Mat drawParkingSpaces(List<ParkingSpace> parkingSpaces, Mat sourceImage) {
        Scalar color;
        for (ParkingSpace p : parkingSpaces) {
            if (p.checkOccupation()) {
                color = new Scalar(0, 0, 255);
            }
            else {
                color = new Scalar(0, 255, 0);
            }
            Imgproc.rectangle(sourceImage, new Rect(p.getCornerTopLeft(), p.getCornerBottomRight()), color, 1);
            double xTextPoint = p.getCornerTopLeft().x;
            double yTextPoint = p.getCornerTopLeft().y - 10;
            Imgproc.putText(sourceImage, p.getName(), new Point(xTextPoint, yTextPoint), Imgproc.FONT_HERSHEY_PLAIN, 1, color, 1);
        }
        return sourceImage;
    }

   private static List<MatOfPoint> findAndDrawContours(Mat imageProcessed){
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(imageProcessed, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        return contours;
    }

    public static List<Rect> findAndDrawRectangles(Mat imageProcessed, String name, int area){
        List<MatOfPoint> contours = findAndDrawContours(imageProcessed);
        List<Rect> retContours = new ArrayList<>();
        Mat rectangles = Mat.zeros(imageProcessed.size(), CvType.CV_8UC3);
        for(MatOfPoint c: contours) {
            Rect rect = Imgproc.boundingRect(c);
            if((rect.area() > (area / 6)) || (name == "P1" && rect.area() > area / 3) ) {
                Imgproc.rectangle(rectangles, rect, new Scalar(0, 255, 0), 1);
                retContours.add(rect);
            }
        }
        return retContours;
    }
}
