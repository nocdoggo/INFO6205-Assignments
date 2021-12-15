package edu.neu.coe.info6205;

public class Pell {
    public Pell() {
    }

    public long get(int n) {
//        if (n < 0) throw new UnsupportedOperationException("Pell.get is not supported for negative n");
//        return -1;

        // By default, the first several are indeed the index value itself.
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n <= 2) {
            return n;
        }
        // Then we start
        long x1 = 1;
        long x2 = 2;
        long x3;

        // Start the loop
        for (long i = 3; i <= n; i++)
        {
            x3 = 2 * x2 + x1;
            x1 = x2;
            x2 = x3;
        }
        return x2;


    }



}

