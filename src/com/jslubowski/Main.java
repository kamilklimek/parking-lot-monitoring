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


//        { // First algorithm
//            // 1. Load image
//            sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
////            // 2. Noise reduction through gaussian blur
////            Imgproc.GaussianBlur(sourceImage, sourceImage,
////                    new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
////            Imgcodecs.imwrite(projectFilePath + filename + "_blurred.jpg", sourceImage);
//            // 3. Grayscale conversion
//            Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_RGB2GRAY);
//            Imgcodecs.imwrite(projectFilePath + filename + "_grayscale.jpg", sourceImage);
////            // 5. Smoothening for noise reduction
////            sourceImage = SmoothImage.smoothGrayscaleImage(sourceImage);
////            Imgcodecs.imwrite(projectFilePath + filename + "_smoothened.jpg", sourceImage);
//            // 4. Adaptive thresholding
//            Imgproc.adaptiveThreshold(sourceImage,sourceImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
//                    Imgproc.THRESH_BINARY_INV, 15, 10 );
//            Imgcodecs.imwrite(projectFilePath + filename + "_adaptiveThresh.jpg", sourceImage);
//            // 7. opening
//            Mat elementOpen = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS,
//                    new Size( kernelSize + 1 , kernelSize + 1 ),
//                    new Point(kernelSize, kernelSize));
//            for(int i = 0 ; i < 1; i++) {
//                Imgproc.morphologyEx(sourceImage, sourceImage, Imgproc.MORPH_OPEN, elementOpen);
//            }
//            Imgcodecs.imwrite(projectFilePath + filename + "_opening.jpg", sourceImage);
//            // 5. Laplace edge detection
//            Imgproc.Laplacian(sourceImage, sourceImage, sourceImage.type(), 3, 1, 0, Core.BORDER_DEFAULT);
//            Imgcodecs.imwrite(projectFilePath + filename + "_laplace.jpg", sourceImage);
//            // 6. Skeletonization in order to get simple borders
//            sourceImage = Skeletonization.sequentialThinning(sourceImage);
//            Imgcodecs.imwrite(projectFilePath + filename + "_skel.jpg", sourceImage);
//            // 7. Closing
//            Mat elementClose = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE,
//                    new Size( kernelSize + 1 , kernelSize + 1 ),
//                    new Point(kernelSize, kernelSize));
//            for(int i = 0 ; i < 4; i++) {
//                Imgproc.morphologyEx(sourceImage, sourceImage, Imgproc.MORPH_CLOSE, elementClose);
//            }
//            Imgcodecs.imwrite(projectFilePath + filename + "_firstAlgorithm.jpg", sourceImage);
//        }

//        { // Second algorithm
//            // 1. Load image
//            sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
//            // 2. Noise reduction through gaussian blur
//            Imgproc.GaussianBlur(sourceImage, sourceImage,
//                    new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
//            Imgcodecs.imwrite(projectFilePath + filename + "_blurred.jpg", sourceImage);
//            // 3. Grayscale conversion
//            Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_RGB2GRAY);
//            Imgcodecs.imwrite(projectFilePath + filename + "_grayscale.jpg", sourceImage);
//            // 4. Adaptive thresholding
//            Imgproc.adaptiveThreshold(sourceImage,sourceImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
//                    Imgproc.THRESH_BINARY_INV, 31, 10 );
//            Imgcodecs.imwrite(projectFilePath + filename + "_adaptiveThresh.jpg", sourceImage);
//            // 5. Skeletonization in order to get simple borders
//            sourceImage = Skeletonization.sequentialThinning(sourceImage);
//            Imgcodecs.imwrite(projectFilePath + filename + "_secondAlgorithm2.jpg", sourceImage);
//            // 6. Find and draw contours
//            List<MatOfPoint> contours = new ArrayList<>();
//            Mat hierarchy = new Mat();
//            Imgproc.findContours(sourceImage, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//            Mat drawing = Mat.zeros(sourceImage.size(), CvType.CV_8UC3);
//            Scalar color = new Scalar(0, 255, 0);
//            for (int i = 0; i < contours.size(); i++) {
//                if(Imgproc.contourArea(contours.get(i)) > 2) {
//                    Imgproc.drawContours(drawing, contours, i, color, 2, Imgproc.LINE_8, hierarchy, 0, new Point());
//                }
//            }
//            Imgcodecs.imwrite(projectFilePath + filename + "_contours2.jpg", drawing);
//            // Find and draw moments (or blobs if you prefer)
//            Mat rectangles = Mat.zeros(sourceImage.size(), CvType.CV_8UC3);
//            for(MatOfPoint c: contours) {
//                Rect rect = Imgproc.boundingRect(c);
//                if(rect.area() > 120) {
//                    Imgproc.rectangle(rectangles, rect, color, 2);
//                }
//            }
//            Imgcodecs.imwrite(projectFilePath + filename + "_rectangles2.jpg", rectangles);
//
//        }

//        { // Third algorithm
//            // 1. Load an image
//            sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
//            // 2. Grayscale conversion
//            Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_RGB2GRAY);
//            Imgcodecs.imwrite(projectFilePath + filename + "_grayscale.jpg", sourceImage);
//            // 3. Thresholding / Adaptive thresholding
//            Imgproc.adaptiveThreshold(sourceImage, sourceImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
//                    Imgproc.THRESH_BINARY_INV, 15, 10);
//            Imgcodecs.imwrite(projectFilePath + filename + "_adaptiveThresh.jpg", sourceImage);
//            // 4. Initialization of a kernel
//            Mat E = Mat.ones(kernelSize, kernelSize, 1);
//            System.out.println(E.dump());
//            // 5. Dilation
//            Imgproc.dilate(sourceImage, sourceImage, E, new Point(-1, -1), 2);
//            Imgcodecs.imwrite(projectFilePath + filename + "_dilated.jpg", sourceImage);
//            // 6. Erosion
//            Imgproc.erode(sourceImage, sourceImage, E, new Point(-1, -1), 2);
//            Imgcodecs.imwrite(projectFilePath + filename + "_eroded.jpg", sourceImage);
//            // 7. Find and draw contours
//            List<MatOfPoint> contours = new ArrayList<>();
//            Mat hierarchy = new Mat();
//            Imgproc.findContours(sourceImage, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
//            Mat drawing = Mat.zeros(sourceImage.size(), CvType.CV_8UC3);
//            Scalar color = new Scalar(0, 255, 0);
//            for (int i = 0; i < contours.size(); i++) {
//                if(Imgproc.contourArea(contours.get(i)) > 15) {
//                    Imgproc.drawContours(drawing, contours, i, color, 2, Imgproc.LINE_8, hierarchy, 0, new Point());
//                }
//            }
//            Imgcodecs.imwrite(projectFilePath + filename + "_contours4.jpg", drawing);
//        }

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


                // 4. Initialization of a kernel
                E = Mat.ones(kernelSize, kernelSize, 1);
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


                // TODO Check the algorithm that measures the amount of taken space, and compares
                //  it to the car, for instance, it checkes the lengths of a car, or the most optimal
                //  cars configuration in specific spot, and compares the parking spaces to what is available

            }
        }
    }
