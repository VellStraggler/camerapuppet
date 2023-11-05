package org.camerapuppet;

import java.awt.image.BufferedImage;

/**
 * Doesn't bother with alpha values.
 */
public class RGB implements Comparable<RGB>{
    public static final int MID = 127;
    public static final RGB WHITE = new RGB(255,255,255);
    public static final RGB BLACK = new RGB(0,0,0);
    public int red;
    public int green;
    public int blue;

    public RGB() {
        red = 0;
        green = 0;
        blue = 0;
    }
    public RGB(int rgb) {
        red = (rgb >> 16) & 0xFF;
        green = (rgb >> 8) & 0xFF;
        blue = rgb & 0xFF;
    }
    public RGB(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }
    public int avg() {
        return (red + green + blue) / 3;
    }

    /** This adds the values of 2 colors together, staying
     * within the bounds of 0-255.
     * @param o
     */
    public void add(RGB o) {
        red += o.red;
        green += o.green;
        blue += o.blue;
        if (red > 255) red = 255;
        else if (red < 0) red = 0;
        if (green > 255) green = 255;
        else if (green < 0) green = 0;
        if (blue > 255) blue = 255;
        else if (blue < 0) blue = 0;
    }
    public int getIntValue() {
        return (red << 16) | (green << 8) | blue;
    }
    public boolean equals(RGB o) {
        return (compareTo(o) == 0);
    }
    public int compareTo(RGB o) {
        if (red == o.red && green == o.green && blue == o.blue) {
            return 0;
        } else if (red + green + blue > o.red + o.green + o.blue) {
            return 1;
        } else return -1;
    }
    public boolean equals(BufferedImage image, int x, int y) {
        return equals(new RGB(image.getRGB(x,y)));
    }
    public String toString() {
        return "RGB: " + red + ", " + green + ", " + blue;
    }
}
