package com.jslubowski;

import com.jslubowski.model.ParkingSpace;
import com.jslubowski.util.Drawing;
import com.jslubowski.util.FileFolderService;
import com.jslubowski.util.ParkingSpacesCreation;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);                                                                       // library load, needed for OpenCV
        String projectFilePath = System.getProperty("user.dir") + "\\src\\data\\";                                          // gets current path
        String[] batches = FileFolderService.getAllFoldersFromDirectory(projectFilePath + "input\\");

        for(String s : batches) {
            System.out.println("Batch folder: " + s);
            List<String> imagesPaths = FileFolderService.readAllFilesInDirectory(projectFilePath + "input\\" + s);
            List<Integer> spacesCoordinates = new ArrayList<>();
            if (imagesPaths.size() != 0) {
                spacesCoordinates = FileFolderService.findAllSpaceCoordinates(projectFilePath + "input\\" + s + "\\");   // calls a method that reads all spaceCoordinates from spaces.txt file
            }
            for(String imagePath: imagesPaths) {
                if(imagesPaths.size() == 0) break;
                String[] arrImagePath = imagePath.split("\\\\");
                String filename = arrImagePath[arrImagePath.length - 1].split("\\.")[0];
                if(arrImagePath[arrImagePath.length - 1].split("\\.")[1].equals("jpg")) {

                    // Main method steps
                    // 1. Load an image
                    Mat sourceImage = Imgcodecs.imread(imagePath);

                    // 2. Initiate ParkingSpaces points on an image
                    List<ParkingSpace> parkingSpaces = ParkingSpacesCreation.createParkingSpaces(spacesCoordinates, sourceImage);

                    // 3. Run analysis
                    Mat sourceImageAnalysed = Drawing.drawParkingSpaces(parkingSpaces, sourceImage);
                    Imgcodecs.imwrite(projectFilePath + "output\\" + s + "\\" + filename + "_analysed" + ".jpg", sourceImageAnalysed);
                }
            }
        }
    }
}
