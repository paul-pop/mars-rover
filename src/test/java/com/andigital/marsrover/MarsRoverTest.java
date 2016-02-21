package com.andigital.marsrover;

import com.andigital.marsrover.exception.RoverOutOfBoundsException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Tests for {@link MarsRover}
 *
 * Created by ppop on 19/02/2016.
 */
public class MarsRoverTest {

    private static final Coordinates bounds = new Coordinates(5, 5);

    private MarsRover rover;

    @BeforeTest
    public void before() {
        rover = new MarsRover();
        rover.setBounds(bounds);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenRoverReceivesNullCommandThenThrowException() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.N);

        rover.execute(null);

        fail(); // should not get here
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenRoverReceivesInvalidCommandThenThrowException() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.W);

        rover.execute("X");

        fail(); // should not get here
    }

    @Test(expectedExceptions = RoverOutOfBoundsException.class)
    public void whenRoverMovesOutOfBoundsThrowException() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.N);

        rover.execute("LM"); // turn L then move 1 step (position will be -1)

        fail(); // should not get here
    }

    @Test(expectedExceptions = RoverOutOfBoundsException.class)
    public void whenRoverEventuallyMovesOutOfBoundsThrowException() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.N);

        rover.execute("RMMMMMM"); // turn R, then move 6 steps in the same direction (bound is 5,5)

        fail(); // should not get here
    }

    @Test
    public void whenRoverDoesNotReceiveCommandsStayStill() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.W);

        rover.execute("");

        assertEquals(new Coordinates(0, 0), rover.getCoordinates());
        rover.setDirection(Direction.W);
    }

    @Test
    public void whenRoverDoesNotReceiveAnyMoveCommandsStayStill() {
        rover.setCoordinates(new Coordinates(0, 0));
        rover.setDirection(Direction.W);

        rover.execute("LLLLRRRLRLRLRRR");

        assertEquals(new Coordinates(0, 0), rover.getCoordinates());
    }

    @Test
    public void whenRotating360DegreesHaveSameDirectionAndSameCoordinates() {
        rover.setCoordinates(new Coordinates(2, 2));
        rover.setDirection(Direction.E);

        rover.execute("RRRR");

        assertEquals(new Coordinates(2, 2), rover.getCoordinates());
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    public void whenRoverMovesAcrossGridCheckItsFinalPosition() {
        rover.setCoordinates(new Coordinates(3, 3));
        rover.setDirection(Direction.S);

        rover.execute("LMLMMRMRMM");

        assertEquals(new Coordinates(5, 3), rover.getCoordinates());
        assertEquals(Direction.S, rover.getDirection());
    }

    @Test
    public void whenRoverDoesntTurnCheckItsFinalPosition() {
        rover.setCoordinates(new Coordinates(1, 1));
        rover.setDirection(Direction.N);

        rover.execute("MMMM");

        assertEquals(new Coordinates(1, 5), rover.getCoordinates());
        assertEquals(Direction.N, rover.getDirection());
    }

    @Test
    public void whenRoverReceivesInstructionTwiceCheckItsFinalPosition() {
        rover.setCoordinates(new Coordinates(2, 2));
        rover.setDirection(Direction.N);

        rover.execute("MM");
        rover.execute("RMMRMMLM");

        assertEquals(new Coordinates(5, 2), rover.getCoordinates());
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    public void whenMultipleRoversMoveAcrossTheGridCheckTheirFinalPosition() {
        // Given
        MarsRover rover1 = new MarsRover();
        rover1.setCoordinates(new Coordinates(1, 2));
        rover1.setDirection(Direction.N);
        rover1.setBounds(bounds);
        MarsRover rover2 = new MarsRover();
        rover2.setCoordinates(new Coordinates(3, 3));
        rover2.setDirection(Direction.E);
        rover2.setBounds(bounds);

        // When
        rover1.execute("LMLMLMLMM");
        rover2.execute("MMRMMRMRRM");

        // Then
        assertEquals(new Coordinates(1, 3), rover1.getCoordinates());
        assertEquals(Direction.N, rover1.getDirection());
        assertEquals(new Coordinates(5, 1), rover2.getCoordinates());
        assertEquals(Direction.E, rover2.getDirection());
    }
}
