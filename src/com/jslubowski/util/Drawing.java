package com.jslubowski.util;

import com.jslubowski.model.ParkingSpace;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

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
}
