package com.andigital.marsrover.exception;

/**
 * Custom exception that can be thrown in case the rover wants to go out of the plateau.
 * This should not happen, as they are pretty expensive!
 *
 * Created by ppop on 19/02/2016.
 *
 */
public class RoverOutOfBoundsException extends RuntimeException {

	public RoverOutOfBoundsException() {
		super();
	}

}
