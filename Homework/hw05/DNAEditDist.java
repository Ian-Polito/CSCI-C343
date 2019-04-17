/* C343 Summer 2018
 * homework 01
 * Ian Polito
 * ipolito
 */

import java.time.*;

public class DNAEditDist {

    //takes two input strings (DNA strings) and computes their
    //hamming distance
    public int hammingDistance(String dna1, String dna2) {
        int result = 0;
        for (int i = 0; i < dna1.length(); i++) {
            if (dna1.charAt(i) != dna2.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    //returns the edit Distance of two equal length dna strings
    public int editDistance(String dna1, String dna2) {
        int d[][] = new int[dna1.length()+1][dna2.length()+1];
        //initialize the table
        for (int i = 0; i <= dna1.length(); i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= dna2.length(); j++) {
            d[0][j] = j;
        }
        int c = 0;
        //fill the dynamic programming table
        for (int i = 1; i <= dna1.length(); i++)  {
            for (int j = 1; j <= dna2.length(); j++) {
                if (dna1.charAt(i-1) == dna2.charAt(j-1)) {
                    c = 0;
                } else {
                    c = 1;
                }
                d[i][j] = min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1] + c);
            }
        }
        return d[dna1.length()-1][dna2.length()-1];
    }

    //returns the smallest of three integers
    public int min(int a, int b, int c) {
        int result = a;
        if (result > b) {
            result = b;
        }
        if (result > c) {
            result = c;
        }
        return result;
    }

    public static void main(String[] args) {
        //Testing editDistance

        RandomDNA test = new RandomDNA();
        DNAEditDist test2 = new DNAEditDist();
        for (int i = 4; i < 32768; i *= 2) {
            long a = Instant.now().toEpochMilli();
            String dna1 = test.getRandomDNA(i);
            long b = Instant.now().toEpochMilli();
            System.out.println("Time taken to generate string 1 in ms: " + (b - a));
            a = Instant.now().toEpochMilli();
            String dna2 = test.getRandomDNA(i);
            b = Instant.now().toEpochMilli();
            System.out.println("Time taken to generate string 2 in ms: " + (b - a));
            System.out.println("String length: " + dna1.length());

            a = Instant.now().toEpochMilli();
            System.out.println("Hamming Distance: " + test2.hammingDistance(dna1, dna2));
            b = Instant.now().toEpochMilli();
            System.out.println("Time taken to find Hamming Distance in ms: " + (b - a));

            a = Instant.now().toEpochMilli();
            System.out.println("Edit Distance: " + test2.editDistance(dna1, dna2));
            b = Instant.now().toEpochMilli();
            System.out.println("Time taken to find Edit Distance in ms: " + (b - a));
        }
    }
}