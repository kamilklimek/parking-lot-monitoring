package com.jslubowski;

import com.jslubowski.model.ParkingSpace;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    // WHAT TODO - P10 needs to be worked because of lines on the ground, P9 needs to ignore the black post and the black car detection needs to be reworked

    public static void main(String[] args) {

	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // library load, needed for OpenCV

        String projectFilePath = System.getProperty("user.dir") + "\\src\\data\\";
        //TODO Implement for each file in a directory
        String filename = "example_lot2"; // name of the picture to work on


        //Main method steps
        // 1. Load an image
        Mat sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");

        // TODO Implement reading from the file (point 2 and 3)
        // 2. Initiate ParkingSpaces points on an image
        ParkingSpace P1 = new ParkingSpace(425, 460, 480, 515, sourceImage, "P1");
        ParkingSpace P2 = new ParkingSpace(480, 440, 545, 520, sourceImage, "P2");
        ParkingSpace P3 = new ParkingSpace(545, 440, 595, 520, sourceImage, "P3");
        ParkingSpace P4 = new ParkingSpace(600, 440, 660, 520, sourceImage, "P4");
        ParkingSpace P5 = new ParkingSpace(275, 550, 360, 665, sourceImage, "P5");
        ParkingSpace P6 = new ParkingSpace(365, 550, 450, 665, sourceImage, "P6");
        ParkingSpace P7 = new ParkingSpace(450, 550, 540, 665, sourceImage, "P7");
        ParkingSpace P8 = new ParkingSpace(540, 550, 620, 645, sourceImage, "P8");
        ParkingSpace P9 = new ParkingSpace(620, 550, 720, 645, sourceImage, "P9");
        ParkingSpace P10 = new ParkingSpace(780, 430, 825, 520, sourceImage, "P10");
        ParkingSpace P11 = new ParkingSpace(827, 440, 880, 520, sourceImage, "P11");
        ParkingSpace P12 = new ParkingSpace(885, 430, 945, 510, sourceImage, "P12");
        ParkingSpace P13 = new ParkingSpace(945, 430, 1010, 510, sourceImage, "P13");


        // 3. Create a list of parking spaces (list because of future purposes)
        List<ParkingSpace> parkingSpaces = new ArrayList<>(Arrays.asList(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13));

//        P10.checkOccupation();

        // 4. Run analysis
        // TODO Implement this inside the a static method in separate class
        Mat sourceImageAnalysed = sourceImage.clone();
        for(ParkingSpace p: parkingSpaces){
            if(p.checkOccupation()) {
                Imgproc.rectangle(sourceImageAnalysed, new Rect(p.getCornerTopLeft(), p.getCornerBottomRight()), new Scalar(0, 0, 255), 1);
                double xTextPoint = p.getCornerTopLeft().x;
                double yTextPoint = p.getCornerTopLeft().y - 10;
                Imgproc.putText(sourceImageAnalysed, p.getName(), new Point(xTextPoint, yTextPoint), Imgproc.FONT_HERSHEY_PLAIN, 1,
                        new Scalar(0, 0, 255), 1);
            }
            else {
                Imgproc.rectangle(sourceImageAnalysed, new Rect(p.getCornerTopLeft(), p.getCornerBottomRight()), new Scalar(0, 255, 0), 1);
                double xTextPoint = p.getCornerTopLeft().x;
                double yTextPoint = p.getCornerTopLeft().y - 10;
                Imgproc.putText(sourceImageAnalysed, p.getName(), new Point(xTextPoint, yTextPoint), Imgproc.FONT_HERSHEY_PLAIN, 1,
                        new Scalar(0, 255, 0), 1);
            }
            Imgcodecs.imwrite(projectFilePath + filename + "_" + "analysed" + ".jpg", sourceImageAnalysed);
        }

        }
    }
