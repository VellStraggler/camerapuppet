package org.camerapuppet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FacialRecognition {

    BufferedImage originalImage;

    public FacialRecognition(BufferedImage image) {
        originalImage = image;
    }

    /**
     * Make a copy of our image and modify it to show where the eyes,
     * mouth, and skin is on the image.
     * @param image
     * @return
     */
    public BufferedImage treatImage() {
        BufferedImage treatedImage = ImageWork.copyImage(originalImage);

        List<Point> nearBlacks = new ArrayList<>();
        List<Point> nearWhites = new ArrayList<>();

        // Scan the image for cases of near-black and near-white

        // Find where near-white touches near-black

        // If there are two cases, it's a front-facing person

        // If there is one case, it's a side-facing person

        // Highlight eyes in the treated image

        // Take samples around the eyes to find the skin-tone

        // Highlight the skin in the treated image

        // Return the treated image
        return treatedImage;

    }
}
