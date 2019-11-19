package com.jslubowski;

import com.jslubowski.dip.Skeletonization;
import com.jslubowski.dip.SmoothImage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // library load

        String projectFilePath = "D:\\wszystko\\studia\\semestr 7\\praca inzynierska\\ParkingLotMonitoring\\src\\data\\";
        String filename = "example_lot4"; // name of the file to work on
        int kernelSize = 3;

        Mat sourceImage = new Mat(); // sourceImage variable initialization


        // Fourth algorithm
            {
                // 1. Load an image
                sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
                // 2. Crop
                Rect rectCrop = new Rect(new Point(368, 440), new Point(668, 516));
                sourceImage = new Mat(sourceImage, rectCrop);
                Imgcodecs.imwrite(projectFilePath + filename + "_cropped.jpg", sourceImage);
                // 2. Noise reduction through gaussian blur
                Imgproc.GaussianBlur(sourceImage, sourceImage,
                     new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
//                Imgcodecs.imwrite(projectFilePath + filename + "_blurred.jpg", sourceImage);
                // 3. Canny Edge detection
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 230, 300, kernelSize, true);
//                Imgcodecs.imwrite(projectFilePath + filename + "_canny.jpg", edges);
                // 4. Initialization of a kernel
                Mat E = Mat.ones(kernelSize, kernelSize, 1);
//            System.out.println(E.dump());


                // 5. Dilation
                Imgproc.dilate(edges, edges, E, new Point(-1, -1), 4);
//                Imgcodecs.imwrite(projectFilePath + filename + "_dilated.jpg", edges);
//                // 6. Erosion
//                Imgproc.erode(edges, edges, E, new Point(-1, -1), 1);
//                Imgcodecs.imwrite(projectFilePath + filename + "_eroded.jpg", edges);


                // 5. Find and draw contours
                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
                Mat drawing = Mat.zeros(sourceImage.size(), CvType.CV_8UC3);
                Scalar color = new Scalar(0, 255, 0);
                for (int i = 0; i < contours.size(); i++) {
                    if(Imgproc.contourArea(contours.get(i)) > 2) {
                        Imgproc.drawContours(drawing, contours, i, color, 2, Imgproc.LINE_4, hierarchy, 0, new Point());
                    }
                }
//                Imgcodecs.imwrite(projectFilePath + filename + "_contours.jpg", drawing);
                // 7. Find and draw rectangles
                Mat rectangles = Mat.zeros(sourceImage.size(), CvType.CV_8UC3);
                for(MatOfPoint c: contours) {
                    Rect rect = Imgproc.boundingRect(c);
                    if(rect.area() > 500) {
                        Imgproc.rectangle(rectangles, rect, color, 1);
                    }
                }
                Imgcodecs.imwrite(projectFilePath + filename + "_rectangles.jpg", rectangles);



            }
        }
    }
