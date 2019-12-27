package com.jslubowski;

import com.jslubowski.dip.Processing;
import com.jslubowski.model.ParkingSpace;
import com.jslubowski.dip.Drawing;
import com.jslubowski.util.FileFolderService;
import com.jslubowski.util.ParkingSpacesListService;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);                                                                       // library load, needed for OpenCV
        String projectFilePath = System.getProperty("user.dir") + "\\src\\data\\";                                          // gets current path
        String[] batches = FileFolderService.getAllFoldersFromDirectory(projectFilePath + "input\\");
        List<ParkingSpace> previousParkingSpaces = new ArrayList<>();

        for(String s : batches) {                                                                                           //for photo batch folder
            System.out.println("Batch folder: " + s);
            List<String> imagesPaths = FileFolderService.readAllFilesInDirectory(projectFilePath + "input\\" + s); //get all files from batch folder
            List<Integer> spacesCoordinates = new ArrayList<>();
            if (imagesPaths.size() != 0) {
                spacesCoordinates = FileFolderService.findAllSpaceCoordinates(projectFilePath + "input\\" + s + "\\");   // calls a method that reads all spaceCoordinates from spaces.txt file
            }

            for(String imagePath: imagesPaths) {
                if(imagesPaths.size() == 0) break;                                                                      // if no files in a dir then exit
                String[] arrImagePath = imagePath.split("\\\\");
                String filename = arrImagePath[arrImagePath.length - 1].split("\\.")[0];
                if(arrImagePath[arrImagePath.length - 1].split("\\.")[1].equals("jpg")) {                           // check if a photo

                    // Main method steps
                    // 1. Load an image
                    Mat sourceImage = Imgcodecs.imread(imagePath);
                    Mat outputImage = sourceImage.clone();

                    // 2. Initiate ParkingSpaces points on an image
                    List<ParkingSpace> currentParkingSpaces =
                            ParkingSpacesListService.createParkingSpaces(spacesCoordinates, sourceImage);

                    // 3. Preprocess all parking spaces and add to currentParkingSpaces list
                    currentParkingSpaces = Processing.preProcessAll(currentParkingSpaces);

                    // 4. Run occupation verification
                    Processing.runAnalysis(previousParkingSpaces, currentParkingSpaces,
                            outputImage, projectFilePath, filename, s);

                    // 5. Save current parking spaces as previous and go to next iteration
                    previousParkingSpaces = new ArrayList<>();
                    for(ParkingSpace space: currentParkingSpaces){
                        previousParkingSpaces.add(space);
                    }
                }
            }
        }
    }
}
