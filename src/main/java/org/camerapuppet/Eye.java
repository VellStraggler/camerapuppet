package org.camerapuppet;

import java.awt.*;

public class Eye {

    private Point center;
    private Integer diameter;
    private Integer pupilDiameter;
    private Integer openness;
    // eye openness ranges from 0 (closed) to 100 (open)
    // it is based on how close the top and bottom of the eye are from the diameter

    private Eyebrow eyebrow;
    private Integer startDistanceToEyebrow;
    private Integer distanceToEyebrow;

    public Eye() {}

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public Integer getPupilDiameter() {
        return pupilDiameter;
    }

    public void setPupilDiameter(Integer pupilDiameter) {
        this.pupilDiameter = pupilDiameter;
    }

    public Integer getOpenness() {
        return openness;
    }

    public void setOpenness(Integer openness) {
        this.openness = openness;
    }

    public Eyebrow getEyebrow() {
        return eyebrow;
    }

    public void setEyebrow(Eyebrow eyebrow) {
        this.eyebrow = eyebrow;
    }

    public Integer getStartDistanceToEyebrow() {
        return startDistanceToEyebrow;
    }

    public void setStartDistanceToEyebrow(Integer startDistanceToEyebrow) {
        this.startDistanceToEyebrow = startDistanceToEyebrow;
    }

    public Integer getDistanceToEyebrow() {
        return distanceToEyebrow;
    }

    public void setDistanceToEyebrow(Integer distanceToEyebrow) {
        this.distanceToEyebrow = distanceToEyebrow;
    }
}
