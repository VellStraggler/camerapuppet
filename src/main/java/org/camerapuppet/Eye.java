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
}
