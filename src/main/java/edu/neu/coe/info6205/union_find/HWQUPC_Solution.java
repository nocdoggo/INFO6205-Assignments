package edu.neu.coe.info6205.union_find;

import java.io.BufferedReader;
import java.io.IOException;


import java.io.InputStreamReader;
import java.util.Random;

public class HWQUPC_Solution {

    // Just in case
    // public static void main(String[] args) {
    public static void main(String[] args) {


        int[] numOfSites = {100, 200, 500, 1000, 2000, 4000, 8000, 16000, 32000, 48000, 64000, 128000, 256000, 384000, 512000, 768000, 1024000};
        for ( int numTemp : numOfSites ) {
            int sites = numTemp;
            int counter = 0;
            int sum = 0;
            while(counter < 50) {
                // this is the passing of the number of sites for each run.
                int count = count(sites);
                System.out.print("TRIAL " + ( counter + 1 ) + ": " + count + ", ");
                // For every 5 trials, we start a new line
                if(counter % 5 == 4 ){
                    System.out.println();
                }

                // counter increase
                counter++;

                // accumulation
                sum += count;
            }
            System.out.println();
            System.out.println("Average of connections generated for " + sites + " sites: " + ((float) sum / 50));
            System.out.println();

        }



    }

    public static int count(int sites) {
        Random r = new Random();
        int connections = 0;
        UF h = new UF_HWQUPC(sites, true);
        while (h.components() > 1) {
            int p = r.nextInt(sites);
            int q = r.nextInt(sites);
            if(!h.isConnected(p, q)) {
                h.union(p, q);
            }
            connections++;
        }

        return connections;
    }
}
