package com.andigital.marsrover;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests for {@link Direction}
 *
 * Created by ppop on 19/02/2016.
 */
public class DirectionTest {

    @Test(dataProvider = "turnLeftValues")
    public void whenTurningLeftDirectionChanges(Direction currentDirection, Direction newDirection) {
        assertEquals(newDirection, currentDirection.left());
    }

    @Test(dataProvider = "turnRightValues")
    public void whenTurningRightDirectionChanges(Direction currentDirection, Direction newDirection) {
        assertEquals(newDirection, currentDirection.right());
    }

    @Test(dataProvider = "stepSizeXAxisChangeValues")
    public void whenChangingDirectionStepSizeForXAxisChanges(Direction direction, int stepSizeX) {
        int stepSize = direction.getStepSizeX();
        assertEquals(stepSizeX, stepSize);
    }

    @Test(dataProvider = "stepSizeYAxisChangeValues")
    public void whenChangingDirectionStepSizeForYAxisChanges(Direction direction, int stepSizeY) {
        int stepSize = direction.getStepSizeY();
        assertEquals(stepSizeY, stepSize);
    }

    @Test
    public void whenDoingAFullLeftRotationThenKeepSameDirection() {
        Direction direction = Direction.N;
        assertEquals(Direction.N, direction.left().left().left().left());
    }

    @Test
    public void whenDoingAFullRightRotationThenKeepSameDirection() {
        Direction direction = Direction.N;
        assertEquals(Direction.N, direction.right().right().right().right());
    }

    /**
     * Data providers
     */
    @DataProvider
    private Object[][] turnLeftValues() {
        return new Object[][] {
                {Direction.N, Direction.W},
                {Direction.S, Direction.E},
                {Direction.W, Direction.S},
                {Direction.E, Direction.N}
        };
    }

    @DataProvider
    private Object[][] turnRightValues() {
        return new Object[][] {
                {Direction.N, Direction.E},
                {Direction.S, Direction.W},
                {Direction.W, Direction.N},
                {Direction.E, Direction.S}
        };
    }

    @DataProvider
    private Object[][] stepSizeXAxisChangeValues() {
        return new Object[][] {
                {Direction.N, 0},
                {Direction.S, 0},
                {Direction.W, -1},
                {Direction.E, 1}
        };
    }

    @DataProvider
    private Object[][] stepSizeYAxisChangeValues() {
        return new Object[][] {
                {Direction.N, 1},
                {Direction.S, -1},
                {Direction.W, 0},
                {Direction.E, 0}
        };
    }
}
