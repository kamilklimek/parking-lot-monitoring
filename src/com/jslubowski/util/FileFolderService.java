package com.jslubowski.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFolderService {

    public static List<String> readAllFilesInDirectory(String projectFilePath) {
        List<String> imagesPaths = null;
        try (Stream<Path> walk = Files.walk(Paths.get(projectFilePath))) {
            imagesPaths = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagesPaths;
    }

    public static List<Integer> findAllSpaceCoordinates(String projectFilePath) {
        Scanner sc = null;
        List<Integer> spacesCoordinates = new ArrayList<>();
        try (FileInputStream inputTextFile = new FileInputStream(projectFilePath + "spaces.txt")) {
            sc = new Scanner(inputTextFile);
            while (sc.hasNextInt()) {
                spacesCoordinates.add(sc.nextInt());
            }
        } catch (IOException e) {
            System.out.println("Error reading spaces.txt file.");
            e.printStackTrace();
            System.exit(0);
            return null;
        }
        return spacesCoordinates;
    }

    public static String[] getAllFoldersFromDirectory(String filePath) {
        File file = new File(filePath);
        String[] batches = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });
        return batches;
    }
}
