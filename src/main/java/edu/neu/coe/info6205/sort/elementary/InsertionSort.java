/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;


// Just in case
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();

        // TO BE IMPLEMENTED

        // Start the iteration
        for (int i = from + 1; i < to; i++) {

            int tempIdx = i - 1;

            // tempIdx can be 0
            //while (tempIdx >= from +1){
            while (tempIdx >= 0 && !helper.less(xs[tempIdx], xs[tempIdx + 1])){
                // Check condition
//                if (helper.swapStableConditional(xs, tempIdx)) {
//                    tempIdx -- ;
//
//                } else {
//                    break;
//                }

                // Now perform the swap here

                helper.swap(xs, tempIdx, tempIdx + 1);

                // Then travel back by decreasing the index by 1
                tempIdx --;
            }

        }
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    // Insert benchmark code

    public static void main(String[] args) throws IOException {
        int NumOfRuns = 1000;

        // Starting with 1000
        for(int temp = 1000; temp <= 10000; temp = temp + 1000) {
            int iterationCount = temp;


            BaseHelper<Integer> helper = new BaseHelper<>("InsertionSort", iterationCount, Config.load(InsertionSort.class));

            SortWithHelper<Integer> sorter = new InsertionSort<>(helper);

            Benchmark<Integer[]> benchmarkTimer = new Benchmark_Timer<>("InsertionSort", sorter::preProcess, sorter::sort);


            // For random order test
            System.out.println("");
            System.out.println("======================================================================================");
            System.out.println("");
            double randomOrderTest = benchmarkTimer.run(helper.random(Integer.class, r -> r.nextInt(iterationCount)), NumOfRuns);
            System.out.println("For random order @ " + iterationCount + " times running," + " the runtime is " + randomOrderTest);

            System.out.println("");
//            System.out.println("======================================================================================");

            // Now, we run the ordered array test
            Integer[] numberIncreasingArray = new Integer[iterationCount];
            for (int i = 0; i < iterationCount; i++) {

                // Simply copy over
                numberIncreasingArray[i] = i;
            }

            double orderedArrayTest = benchmarkTimer.run(numberIncreasingArray, NumOfRuns);
//            System.out.println("");
            System.out.println("For ordered @ " + iterationCount + " times running," + " the runtime is " + orderedArrayTest);

//            System.out.println("");
//            System.out.println("======================================================================================");
            System.out.println("");

            // For the partially ordered array
            Integer[] messyArray = helper.random(Integer.class, r -> r.nextInt(iterationCount));
            Arrays.sort(messyArray, messyArray.length / 3, messyArray.length * 2 / 3);

            double partiallyOrderedTest = benchmarkTimer.run(messyArray, NumOfRuns);
            System.out.println("Partially ordered " + iterationCount + " times running," + " the runtime is " + partiallyOrderedTest);

            // Now we flip it and run
            Integer[] flippedArray = new Integer[iterationCount];
            for (int i = 0; i < iterationCount; i++) {
                flippedArray[i] = iterationCount - (i + 1);
            }

            System.out.println("");

            double flippedOrderTest = benchmarkTimer.run(flippedArray, NumOfRuns);
            System.out.println("Reversed " + iterationCount + " times running," + " the runtime is " + flippedOrderTest);
        }
    }
}
