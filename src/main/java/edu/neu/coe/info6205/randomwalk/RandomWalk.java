/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

// For the testing of generating the relationship table
import java.io.FileWriter;
import java.io.IOException;


public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // TO BE IMPLEMENTED
        // First, we need to know that x is the accumulation of dx.
        // And y is just the accumulation of dy

        x += dx;
        y += dy;

        // I think that should be it?
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // TO BE IMPLEMENTED
        // So we will have m iterations.
        for (int i = 0; i < m; i++){
            // Then we call out the randomMove I reckon?
            // Otherwise it is gonna be a clusterfuck.
            randomMove();
        }

    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // TO BE IMPLEMENTED

        // Now we calculate the thing as:
        double dist_temp = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        //return 0;
        // Instead of returning 0.
        return dist_temp;

    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
//        if (args.length == 0)
//            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
//        int m = Integer.parseInt(args[0]);
        int[] m = new int[10];
        int n = 5000;

        for (int i = 0; i < 10; i++) {
            // Recycle the same function.
            m[i] =  (i+1)*(i+1);
        }

//        if (args.length > 1) n = Integer.parseInt(args[1]);
        for (int j:m) {
            double meanDistance = randomWalkMulti(j, n);
            System.out.println(j + " steps: " + meanDistance + " over " + n + " experiments");
        }

    }

}
