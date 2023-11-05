package org.camerapuppet;

import java.awt.image.BufferedImage;

public class FaceData {

    private BufferedImage treatedImage;
    private Eye leftEye;
    private Eye rightEye;
    private Mouth mouth;
    private Integer eyeDistanceApart;
    private Integer headRotation;
    private Integer headTurn;

    public FaceData() {}

    public BufferedImage getTreatedImage() {
        return treatedImage;
    }

    public void setTreatedImage(BufferedImage treatedImage) {
        this.treatedImage = treatedImage;
    }

    public Eye getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(Eye leftEye) {
        this.leftEye = leftEye;
    }

    public Eye getRightEye() {
        return rightEye;
    }

    public void setRightEye(Eye rightEye) {
        this.rightEye = rightEye;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }

    public Integer getEyeDistanceApart() {
        return eyeDistanceApart;
    }

    public void setEyeDistanceApart(Integer eyeDistanceApart) {
        this.eyeDistanceApart = eyeDistanceApart;
    }

    public Integer getHeadRotation() {
        return headRotation;
    }

    public void setHeadRotation(Integer headRotation) {
        this.headRotation = headRotation;
    }

    public Integer getHeadTurn() {
        return headTurn;
    }

    public void setHeadTurn(Integer headTurn) {
        this.headTurn = headTurn;
    }
}