package org.camerapuppet;

import java.awt.image.BufferedImage;

public class FacialRecognition {

    private BufferedImage originalImage;
    private BufferedImage treatedImage;

    public FacialRecognition(BufferedImage image) {
        originalImage = image;
    }
    public FacialRecognition() {}

    public void setImage(BufferedImage image) {
        originalImage = ImageWork.copyImage(image);
    }

    /**
     * Make a copy of our image and modify it to show where the eyes,
     * mouth, and skin is on the image.
     * @return
     */
    public BufferedImage treatImage() {
        treatedImage = ImageWork.convertImageToFindFace(originalImage);
        return treatedImage;
    }
    public BufferedImage treatImage(BufferedImage image) {
        setImage(image);
        return treatImage();
    }

    public FaceData recognize(BufferedImage image) {
        setImage(image);
        return recognize();
    }
    public FaceData recognize() {

        // Scan the image for cases of near-black and near-white

        // Find where near-white touches near-black

        // If there are two cases, it's a front-facing person

        // If there is one case, it's a side-facing person

        // Highlight eyes in the treated image

        // Take samples around the eyes to find the skin-tone

        // Highlight the skin in the treated image

        // Return the treated image
        return null;

    }
}
