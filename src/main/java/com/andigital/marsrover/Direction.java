package com.andigital.marsrover;

/**
 * Enum that contains the cardinal points together with step sizes on each axis (X and Y)
 * (e.g. if Rover is instructed to move N, we know that x will have the same value and y will be increased by 1)
 *
 * Created by ppop on 19/02/2016.
 */
public enum Direction {

    N(0, 1) {
        @Override
        public Direction right() {
            return E;
        }

        @Override
        public Direction left() {
            return W;
        }
    },

    S(0, -1) {
        @Override
        public Direction right() {
            return W;
        }

        @Override
        public Direction left() {
            return E;
        }
    },

    W(-1, 0) {
        @Override
        public Direction right() {
            return N;
        }

        @Override
        public Direction left() {
            return S;
        }
    },

    E(1, 0) {
        @Override
        public Direction right() {
            return S;
        }

        @Override
        public Direction left() {
            return N;
        }
    };

    private int stepSizeX;
    private int stepSizeY;

    Direction(int stepSizeX, int stepSizeY) {
        this.stepSizeX = stepSizeX;
        this.stepSizeY = stepSizeY;
    }

    public int getStepSizeX() {
        return stepSizeX;
    }

    public int getStepSizeY() {
        return stepSizeY;
    }

    /**
     * Method that returns the next cardinal direction after the Rover has turned right.
     * @return {@link Direction}
     */
    public abstract Direction right();

    /**
     * Method that returns the next cardinal direction after the Rover has turned left.
     * @return {@link Direction}
     */
    public abstract Direction left();
}
