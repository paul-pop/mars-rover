package com.andigital.marsrover;

import com.andigital.marsrover.exception.RoverOutOfBoundsException;

import static java.lang.String.valueOf;

/**
 * Definition of the Mars Rover, it contains the current coordinates, the bounds and the direction of the rover.
 *
 * Created by ppop on 19/02/2016.
 */
public final class MarsRover {

    private Direction direction;
    private Coordinates coordinates;
    private Coordinates bounds = new Coordinates(0, 0);

    /**
     * Method used to call multiple command executions on the {@link MarsRover}
     *
     * @param commandString multiple command string
     */
    public void execute(String commandString) {
        for (char command : commandString.toCharArray()) {
            executeSingleCommand(Command.valueOf(valueOf(command)));
        }
    }

    /**
     * Method used to call single command executions on the {@link MarsRover}
     * @param command single command
     */
    private void executeSingleCommand(Command command) {
        switch (command) {
            case L:
                turn(Command.L);
                break;
            case R:
                turn(Command.R);
                break;
            case M:
                move();
                break;
            default:
                throw new IllegalArgumentException("Invalid command was received");
        }
    }

    private void turn(Command command) {
        // Update direction and step size if L or R
        if (Command.L.equals(command)) direction = direction.left();
        if (Command.R.equals(command)) direction = direction.right();
    }

    private void move() {
        Coordinates newCoordinates = new Coordinates(coordinates.getX() + direction.getStepSizeX(),
                coordinates.getY() + direction.getStepSizeY());

        isWithinBounds(newCoordinates);
    }

    /**
     * Check if the new coordinates are within the given bounds.
     * If they are not, throw a {@link RoverOutOfBoundsException}
     *
     * @param newCoordinates new coordinates
     */
    private void isWithinBounds(Coordinates newCoordinates) {
        if (newCoordinates.areWithinBounds(bounds)) {
            coordinates = newCoordinates;
        } else {
            throw new RoverOutOfBoundsException();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setBounds(Coordinates bounds) {
        this.bounds = bounds;
    }


}
