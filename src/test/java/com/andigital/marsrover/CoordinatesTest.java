package com.andigital.marsrover;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests for {@link Coordinates}
 *
 * Created by ppop on 19/02/2016.
 */
public class CoordinatesTest {

    @Test(dataProvider = "validValues")
    public void whenCheckingIfWithinBoundsReturnTrue(Coordinates coordinate, Coordinates bounds) {
        assertTrue(coordinate.areWithinBounds(bounds));
    }

    @Test(dataProvider = "invalidValues")
    public void whenCheckingIfWithinBoundsReturnFalse(Coordinates coordinate, Coordinates bounds) {
        assertFalse(coordinate.areWithinBounds(bounds));
    }

    /**
     * Data providers
     */
    @DataProvider
    private Object[][] validValues() {
        return new Object[][] {
                {new Coordinates(0, 0), new Coordinates(0, 0)},
                {new Coordinates(2, 2), new Coordinates(2, 2)},
                {new Coordinates(1, 2), new Coordinates(2, 2)},
                {new Coordinates(1, 1), new Coordinates(2, 2)}
        };
    }

    @DataProvider
    private Object[][] invalidValues() {
        return new Object[][] {
                {new Coordinates(-1, 0), new Coordinates(0, 0)},
                {new Coordinates(1, 1), new Coordinates(0, 0)},
                {new Coordinates(1, 3), new Coordinates(2, 2)},
                {new Coordinates(3, 1), new Coordinates(2, 1)}
        };
    }

}
