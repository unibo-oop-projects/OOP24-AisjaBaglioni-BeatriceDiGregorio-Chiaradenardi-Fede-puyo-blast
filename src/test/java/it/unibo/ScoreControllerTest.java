package it.unibo;

import it.unibo.controller.ScoreController;
import it.unibo.model.ScoreModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


/**
 * Unit tests for the {@link ScoreController} class.
 */
class ScoreControllerTest {
    private ScoreModel scoreModel;
    private ScoreController scoreController;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        scoreModel = mock(ScoreModel.class);
        scoreController = new ScoreController(scoreModel);
    }

    /**
     * Test: adding points should correctly update the score.
     */
    @Test
    void testAddPoints() {
        when(scoreModel.getScore()).thenReturn(10);
        scoreController.addPoints(3); // 3^2 = 9 points to add
        verify(scoreModel).setScore(19); // 10 + 9 = 19
    }

    /**
     * Test: adding zero power should not change the score.
     */
    @Test
    void testAddZeroPoints() {
        when(scoreModel.getScore()).thenReturn(5);
        scoreController.addPoints(0); // 0^2 = 0 points
        verify(scoreModel).setScore(5); // Score should remain unchanged
    }

    /**
     * Test: adding a power of 1 should add exactly 1 point.
     */
    @Test
    void testAddOnePoint() {
        when(scoreModel.getScore()).thenReturn(7);
        scoreController.addPoints(1); // 1^2 = 1 point
        verify(scoreModel).setScore(8); // 7 + 1 = 8
    }

    /**
     * Test: adding a large power should correctly apply the formula.
     */
    @Test
    void testAddLargePoints() {
        when(scoreModel.getScore()).thenReturn(50);
        scoreController.addPoints(10); // 10^2 = 100 points
        verify(scoreModel).setScore(150); // 50 + 100 = 150
    }
}
