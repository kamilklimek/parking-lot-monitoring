package com.jslubowski;

import com.jslubowski.model.ParkingSpace;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {

	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // library load, needed for OpenCV

        // TODO Implement current path instead of hard-coded
        String projectFilePath = "D:\\wszystko\\studia\\semestr 7\\praca inzynierska\\ParkingLotMonitoring\\src\\data\\";
        String filename = "example_lot2"; // name of the picture to work on


        //Main method steps
        // 1. Load an image
        Mat sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");

        // TODO Implement reading from the file (point 2 and 3)
        // 2. Initiate ParkingSpaces points on an image
        ParkingSpace P1 = new ParkingSpace(425, 460, 480, 535, sourceImage, "P1");
        ParkingSpace P2 = new ParkingSpace(480, 440, 545, 535, sourceImage, "P2");
        ParkingSpace P3 = new ParkingSpace(545, 440, 595, 535, sourceImage, "P3");
        ParkingSpace P4 = new ParkingSpace(600, 440, 660, 535, sourceImage, "P4");
        ParkingSpace P5 = new ParkingSpace(275, 550, 360, 665, sourceImage, "P5");
        ParkingSpace P6 = new ParkingSpace(365, 550, 450, 665, sourceImage, "P6");
        ParkingSpace P7 = new ParkingSpace(450, 550, 540, 665, sourceImage, "P7");
        ParkingSpace P8 = new ParkingSpace(540, 550, 720, 645, sourceImage, "P8");
        ParkingSpace P9 = new ParkingSpace(620, 550, 720, 645, sourceImage, "P9");
        ParkingSpace P10 = new ParkingSpace(760, 430, 825, 520, sourceImage, "P10");
        ParkingSpace P11 = new ParkingSpace(840, 440, 880, 520, sourceImage, "P11");
        ParkingSpace P12 = new ParkingSpace(885, 430, 945, 510, sourceImage, "P12");
        ParkingSpace P13 = new ParkingSpace(945, 430, 1010, 510, sourceImage, "P13");

        P13.saveProcessedImage(projectFilePath, filename, "cropped");

        // 3. Create a list of parking spaces (list because of future purposes)
        List<ParkingSpace> parkingSpaces = new ArrayList<>(Arrays.asList(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13));

        // 4. Run analysis
        P3.isOccupied();



        }
    }
