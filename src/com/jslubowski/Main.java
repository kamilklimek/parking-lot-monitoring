package com.jslubowski;

import com.jslubowski.dip.Skeletonization;
import com.jslubowski.dip.SmoothImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // library load

        String projectFilePath = "D:\\wszystko\\studia\\semestr 7\\praca inzynierska\\ParkingLotMonitoring\\src\\data\\";
        String filename = "example_lot2"; // name of the file to work on
        int kernelSize = 3;

        Mat sourceImage = new Mat(); // sourceImage variable initialization


//        { // First algorithm
//            // 1. Load image
//            sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
//            Imgcodecs.imwrite(projectFilePath + filename + ".jpg", sourceImage);
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
//            Imgcodecs.imwrite(projectFilePath + filename + ".jpg", sourceImage);
//            // 2. Noise reduction through gaussian blur
//            Imgproc.GaussianBlur(sourceImage, sourceImage,
//                    new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
//            Imgcodecs.imwrite(projectFilePath + filename + "_blurred.jpg", sourceImage);
//            // 3. Grayscale conversion
//            Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_RGB2GRAY);
//            Imgcodecs.imwrite(projectFilePath + filename + "_grayscale.jpg", sourceImage);
//            // 4. Adaptive thresholding
//            Imgproc.adaptiveThreshold(sourceImage,sourceImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
//                    Imgproc.THRESH_BINARY_INV, 17, 10 );
//            Imgcodecs.imwrite(projectFilePath + filename + "_adaptiveThresh.jpg", sourceImage);
//            // 5. Opening for more noise reduction
////            Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS,
////                    new Size( kernelSize + 1 , kernelSize + 1 ),
////                    new Point(kernelSize, kernelSize));
////            Imgproc.morphologyEx(sourceImage, sourceImage, Imgproc.MORPH_OPEN, element);
////            Imgcodecs.imwrite(projectFilePath + filename + "_opening.jpg", sourceImage);
////            // 6. Erosion
////            Imgproc.erode(sourceImage, sourceImage, new Mat(), new Point(-1, -1), 1);
////            Imgcodecs.imwrite(projectFilePath + filename + "_eroded.jpg", sourceImage);
//            // 7. Skeletonization in order to get simple borders
//            sourceImage = Skeletonization.sequentialThinning(sourceImage);
//            Imgcodecs.imwrite(projectFilePath + filename + "_secondAlgorithm.jpg", sourceImage);
//
//        }

        { // Third algorithm
            // 1. Load an image
            sourceImage = Imgcodecs.imread(projectFilePath + filename + ".jpg");
            Imgcodecs.imwrite(projectFilePath + filename + ".jpg", sourceImage);
            // 2. Grayscale conversion
            Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_RGB2GRAY);
            Imgcodecs.imwrite(projectFilePath + filename + "_grayscale.jpg", sourceImage);
            // 3. Thresholding / Adaptive thresholding
            Imgproc.adaptiveThreshold(sourceImage,sourceImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                    Imgproc.THRESH_BINARY_INV, 15, 10 );
            Imgcodecs.imwrite(projectFilePath + filename + "_adaptiveThresh.jpg", sourceImage);


            Mat E = new Mat(kernelSize, kernelSize, sourceImage.type());
            for(int i = 0; i < E.rows(); i++){
                for (int j = 0; j < E.cols(); j++){
                    E.put(i, j, 1);
                }
            }
//            System.out.println(E.dump());
            // 4. Dilation
            Imgproc.dilate(sourceImage, sourceImage, E, new Point(-1, -1), 2);
            Imgcodecs.imwrite(projectFilePath + filename + "_dilated.jpg", sourceImage);
            // 5. Erosion
            Imgproc.erode(sourceImage, sourceImage, E, new Point(-1, -1), 2);
            Imgcodecs.imwrite(projectFilePath + filename + "_eroded.jpg", sourceImage);
            // Profit ???

        }






    }
}
