package org.camerapuppet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWork {

    public static final int[] quantizedHuesOld = new int[]{0, 85, 170, 255};
    // increment from 0 to 255 by increments of 255/4, creating 5 sections
    // these \/ are the numbers in the middles of those 5 sections
    // or more simply, the first 4 sections starts + 32
    public static final int[] quantizedHues =    new int[]{32, 96, 159, 223};
    public static final int[] quantizedSection = new int[]{64, 128, 191, 255};
    public static final Rectangle screenDimensions = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    public static final Robot screenGrabber;
    static {
        try {
            screenGrabber = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage takeScreenshot() {
        return screenGrabber.createScreenCapture(screenDimensions);
    }

    /**
     * Save an image to the given path in BMP format. Do not include ".bmp" in the path parameter.
     * @param image
     * @param path
     */
    public static boolean saveImage(BufferedImage image, String path) {
        try {
            ImageIO.write(image, "bmp", new File(path + ".bmp"));
            return true;
        } catch (IOException e) {
            p("Unable to save image: " + e + " " + e.getMessage());
            return false;
        }
    }
    public static BufferedImage retrieveImage(String path) {
        try {
            return ImageIO.read(new File(path +".jpg"));
        } catch (IOException e) {
            p("Unable to read image with path: " + path);
            return null;
        }
    }

    public static BufferedImage copyImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        copy = image.getSubimage(0,0, image.getWidth(), image.getHeight());
        return copy;
    }

    public static BufferedImage convertImageToGrayscale(BufferedImage image) {
        return convertImage(image, BufferedImage.TYPE_BYTE_GRAY);
    }
    /** Reduces the quality of the image by limiting the number of bytes used to store RGB values */
    public static BufferedImage convertImageToReduced(BufferedImage image) {
        return convertImage(image, BufferedImage.TYPE_USHORT_555_RGB);
    }
    /** Reduces the image to only two colors: black and white */
    public static BufferedImage convertImageToBinary(BufferedImage image) {
        return convertImage(image, BufferedImage.TYPE_BYTE_BINARY);
    }
    /** Converts into black and white, making the face easier to find **/
    public static BufferedImage convertImageToFindFace(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new BufferedImage to return
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        RGB brightestColor = getBrightestColor(originalImage, 10);
        RGB changeRequired = new RGB(0,0,0);
        boolean changeIt = false;
        RGB averageColor = getAverageColor(originalImage, 10);
        if (brightestColor.compareTo(new RGB(60,60,60)) < 0) {
            // This average color is a certain distance away from a neutral gray,
            // which the supposed perfect picture would have as an average;
            changeRequired = new RGB(RGB.MID-averageColor.red,
                                     RGB.MID-averageColor.green,
                                     RGB.MID-averageColor.blue);
            changeIt = true;
        }

        // Iterate through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                RGB color = new RGB(originalImage.getRGB(x, y));
                color.add(changeRequired);
                int contrast = 10;
                color.red = increaseContrast(color.red, contrast);
                color.green = increaseContrast(color.green, contrast);
                color.blue = increaseContrast(color.blue, contrast);

                if (closeEnough((int)(color.red/3.0), color.green) && closeEnough(color.green,color.blue)) {
                    color.red = 0; color.blue = 0; color.green = 0;
                } else {
                    color.red = 255;
                    color.green = 255;
                    color.blue = 255;
                }

                newImage.setRGB(x, y, color.getIntValue());
            }
        }
        return newImage;
    }
    private static boolean closeEnough(int a, int b) {
        return Math.abs(a - b) < 25;
    }

    /** The number that the total amount of pixels is divided by is actually
     * the sampleDivider squared.
     * @param image
     * @param sampleDivider
     * @return
     */
    public static RGB getAverageColor(BufferedImage image, int sampleDivider) {
        // get estimated average color;
        RGB avgColor = new RGB(0,0,0);
        for (int y = 0; y < image.getHeight(); y+= sampleDivider) {
            for (int x = 0; x < image.getWidth(); x+= sampleDivider) {
                avgColor.add(new RGB(image.getRGB(x, y)));
            }
        }
        int total = (int)(image.getHeight() * image.getWidth() * .01);
        avgColor.red = avgColor.red/total;
        avgColor.green = avgColor.green/total;
        avgColor.blue = avgColor.blue/total;
        return avgColor;
    }
    public static RGB getBrightestColor(BufferedImage image, int sampleDivider) {
        RGB maxColor = new RGB(0,0,0);
        for (int y = 0; y < image.getHeight(); y+= sampleDivider) {
            for (int x = 0; x < image.getWidth(); x+= sampleDivider) {
                RGB otherColor = new RGB(image.getRGB(x,y));
                if (maxColor.compareTo(otherColor) < 0) {
                    maxColor = otherColor;
                }
            }
        }
        return maxColor;
    }
    private static int increaseContrast(int n, int amount) {
        return increaseContrast(n,amount, 127);
    }
    private static int increaseContrast(int n, int amount, int mid) {
        if (n > mid) {
            if (n < 255-amount) {
                n += amount;
            } else {
                n = 255;
            }
        } else {
            if (n > amount) {
                n -= amount;
            } else {
                n = 0;
            }
        }
        return n;
    }
    private static int treatFaceValue(int n, double divider) {
        if (n > 75*divider) return 255;
        return 0;
    }
    public static BufferedImage convertImageTo4Level(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new BufferedImage for the 2-bit RGB image
        BufferedImage twoBitRGBImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);

        // Iterate through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                // Extract the individual RGB components
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Quantize the RGB values to 2 bits each (4 levels)
                int quantizedRed = quantizeComponent(red);
                int quantizedGreen = quantizeComponent(green);
                int quantizedBlue = quantizeComponent(blue);

                // Combine the quantized RGB components
                int quantizedRGB = (quantizedRed << 16) | (quantizedGreen << 8) | quantizedBlue;

                // Set the pixel value in the twoBitRGBImage
                twoBitRGBImage.setRGB(x, y, quantizedRGB);
            }
        }
        return twoBitRGBImage;
    }
    private static BufferedImage convertImage(BufferedImage image, int imageType) {
        BufferedImage post = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
        Graphics2D artist = post.createGraphics();
        artist.drawImage(image, 0, 0, null);
        artist.dispose();
        return post;
    }

    /** Quantize a component (e.g., red, green, or blue) to 2 bits (4 levels).
    *  Used in convertImageTo4Level() */
    private static int quantizeComponent(int component) {
        // Adjust the quantization as needed to create 4 levels
        if (component < quantizedSection[0]) {
            return quantizedHues[0];
        } else if (component < quantizedSection[1]) {
            return quantizedHues[1];
        } else if (component < quantizedSection[2]) {
            return quantizedHues[2];
        } else {
            return quantizedHues[3];
        }
    }
    private static void p(String s) {
        System.out.println(s);
    }
}