import org.camerapuppet.FacialRecognition;
import org.camerapuppet.FaceData;
import org.camerapuppet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacialRecognitionTests {

    public final String path = "org.camerapuppet/testimages/testphoto";
    public FacialRecognition facialRecognition;


    @BeforeEach
    public void setup() {
        facialRecognition = new FacialRecognition();
    }

    @Test
    public void testPhoto1_RecognizeFace() {
        // Given
        BufferedImage image = ImageWork.retrieveImage(path + "1.jpg");
        Point leftEyeGoal = new Point(617,444);
        //When
        FaceData face = facialRecognition.recognize(image);
        //Then
        boolean closeEnoughToEyeCenter = false;
        Point leftEyeActual = face.getLeftEye().getCenter();
        if (closeEnough(leftEyeActual.x, leftEyeGoal.x) &&
            closeEnough(leftEyeActual.y, leftEyeGoal.y)) {
            closeEnoughToEyeCenter = true;
        }
        assertTrue(closeEnoughToEyeCenter);
    }

    public boolean closeEnough(int a, int b) {
        return Math.abs(a - b) < 5;
    }
}
