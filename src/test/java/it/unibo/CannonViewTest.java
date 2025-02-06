package it.unibo;

import it.unibo.view.CannonView;
import it.unibo.model.CannonModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Scale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;

/**
 * Unit tests for the {@link CannonView} class.
 */
class CannonViewTest {

    private CannonView cannonView;
    private CannonModel cannonModel;
    private Scale scale;

    @BeforeEach
    void setUp() {
        cannonModel = mock(CannonModel.class);
        scale = mock(Scale.class);
        cannonView = new CannonView(scale, cannonModel);
    }

    /**
     * Test to verify that the images are loaded correctly in the constructor.
     */
    @Test
    void testImagesLoaded() throws NoSuchFieldException, IllegalAccessException {
        // Use reflection to access the private field cannonImages
        Field cannonImagesField = CannonView.class.getDeclaredField("cannonImages");
        cannonImagesField.setAccessible(true);

        Image[] cannonImages = (Image[]) cannonImagesField.get(cannonView);

        assertNotNull(cannonImages, "Cannon images array should not be null.");
        assertEquals(5, cannonImages.length, "There should be 5 cannon images loaded.");
    }

    /**
     * Test to check if the cannon's image is drawn correctly for a specific angle.
     */
    @Test
    void testDrawCannon() {
        when(cannonModel.getAngle()).thenReturn(0.3); // Set angle within a specific range

        Graphics g = mock(Graphics.class);
        cannonView.draw(g);

        verify(g).drawImage(any(Image.class), anyInt(), anyInt(), anyInt(), anyInt(), eq(0), eq(0), anyInt(), anyInt(),
                eq(null));
    }

    /**
     * Test to verify the getCenter() method for the cannon position.
     */
    @Test
    void testGetCenter() {
        when(cannonModel.getX()).thenReturn(0.5); // Set a mock x position
        when(scale.getScale()).thenReturn(800); // Set a mock scale value

        Point2DI center = cannonView.getCenter();

        assertNotNull(center, "Center point should not be null.");
        assertEquals(400, center.x(), "Expected x coordinate mismatch."); 
        assertEquals(660, center.y(), "Expected y coordinate mismatch.");
    }

    /**
     * Test the getImageIndexForAngle method to ensure correct image index based on
     * the angle.
     */
    @Test
    void testGetImageIndexForAngle() {
        int imageIndex = cannonView.getImageIndexForAngle(0.3);
        assertEquals(1, imageIndex, "Expected image index for angle 0.3.");

        imageIndex = cannonView.getImageIndexForAngle(0.7);
        assertEquals(3, imageIndex, "Expected image index for angle 0.7.");
    }
}
