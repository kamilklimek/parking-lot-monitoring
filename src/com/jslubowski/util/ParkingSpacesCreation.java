package com.jslubowski.util;

import com.jslubowski.model.ParkingSpace;
import org.opencv.core.Mat;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpacesCreation {
    public static List<ParkingSpace> createParkingSpaces(List<Integer> spacesCoordinates, Mat sourceImage){
        /*
         TODO Here extract pre and post processed images and save then in order to subtract them
         */
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        int counter = 0;
        int[] coordinates = new int[4];
        int parkingSpace = 1;

        for (Integer coordinate : spacesCoordinates) {
            coordinates[counter] = coordinate;
            counter++;
            if (counter == 4) {
                ParkingSpace p = new ParkingSpace(coordinates[0], coordinates[1], coordinates[2], coordinates[3], sourceImage, "P" + parkingSpace);
                parkingSpaces.add(p);
                parkingSpace++;
                counter = 0;
            }
        }
        return parkingSpaces;
    }
}
