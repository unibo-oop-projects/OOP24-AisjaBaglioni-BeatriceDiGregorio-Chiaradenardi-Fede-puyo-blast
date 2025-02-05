package it.unibo;

import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private Grid grid;
    private PuyoInterface mockPuyo;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5); // Crea una griglia 5x5
        mockPuyo = new Puyo("red",10, 10); // Simula un Puyo
    }

    @Test
    void testGridInitialization() {
        assertEquals(5, grid.getRows());
        assertEquals(5, grid.getCols());
    }

    @Test
    void testAddPuyo() {
        assertTrue(grid.addPuyo(mockPuyo, 2, 3));
        assertEquals(mockPuyo, grid.getPuyo(2, 3));
    }

    @Test
    void testAddPuyoInvalidPosition() {
        assertFalse(grid.addPuyo(mockPuyo, -1, 3));
        assertFalse(grid.addPuyo(mockPuyo, 5, 5));
    }

    @Test
    void testRemovePuyo() {
        grid.addPuyo(mockPuyo, 1, 1);
        grid.removePuyo(1, 1);
        assertNull(grid.getPuyo(1, 1));
    }

    @Test
    void testIsValidPosition() {
        assertTrue(grid.isValidPosition(0, 0));
        assertTrue(grid.isValidPosition(4, 4));
        assertFalse(grid.isValidPosition(-1, 0));
        assertFalse(grid.isValidPosition(5, 5));
    }

    @Test
    void testIsColumnFull() {
        for (int y = 0; y < grid.getRows(); y++) {
            grid.addPuyo(mockPuyo, 2, y);
        }
        assertTrue(grid.isColumnFull(2));
    }

    @Test
    void testIsRowFull() {
        for (int x = 0; x < grid.getCols(); x++) {
            grid.addPuyo(mockPuyo, x, 2);
        }
        assertTrue(grid.isRowFull(2));
    }

    @Test
    void testIsGridFull() {
        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                grid.addPuyo(mockPuyo, x, y);
            }
        }
        assertTrue(grid.isGridFull());
    }

    @Test
    void testClearGrid() {
        grid.addPuyo(mockPuyo, 2, 2);
        grid.clear();
        assertNull(grid.getPuyo(2, 2));
    }
}
