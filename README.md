# parking-lot-monitoring
This application is in v0.3 version and it is work in progress.

# What is it about?
The application uses OpenCV. It is a library for Computer Vision. 
The application reads all photos from the src/data/input folder. This photos are the images of a parking lot that I took with Raspberry Pi. It detects occupation of specific parking spaces. Also, in this folder there is 'spaces.txt' file, where the coordinates of the parking spaces are.  Afther the analysis, application creates images with marked free and occupied spaces in the src/data/output folder. Bear in mind that the algorithm was created for specific photos and specific parking lot, so the parameters of the functions might not be the best for your usage. 

# TODO
1. Create a functionality that reads multiple batches of photos in input files.
2. Improve an algorithm (especially black cars).
3. Update the README.
