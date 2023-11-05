package org.camerapuppet;


import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {


        createTreatedImages();





    }
    private static void createTreatedImages() {
        FacialRecognition facialRecognition = new FacialRecognition();
        String prePath = "testimages\\testphoto";
        for(int i = 1; i < 7; i++) {
            BufferedImage treatedImage = facialRecognition.treatImage(ImageWork.retrieveImage(prePath + i));
            ImageWork.saveImage(treatedImage, prePath + i);
        }
    }

    public static void p(String s) {
        System.out.println(s);
    }

}