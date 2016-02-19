package com.andigital.marsrover;

import java.util.Objects;

/**
 * Data structure containing the X and Y coordinates. Will be used to set the boundaries of the plateau.
 *
 * Created by ppop on 19/02/2016.
 *
 */
public class Coordinates {
	
	private int x;
	private int y;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

    /**
     * Checks if provided {@link Coordinates} are within the bounds of the plateau
     *
     * @param bounds bounds
     * @return boolean
     */
    public boolean areWithinBounds(Coordinates bounds) {
        return areWithinBounds(this.x, bounds.getX()) && areWithinBounds(this.y, bounds.getY());
    }

    private boolean areWithinBounds(int currentCoordinate, int boundsCoordinate) {
        return currentCoordinate <= boundsCoordinate && currentCoordinate >= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
