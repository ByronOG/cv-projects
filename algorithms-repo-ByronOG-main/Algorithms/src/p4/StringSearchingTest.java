package p4;

import util.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringSearchingTest {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\n----- String Searching Practical: Analysis -----");
        Scanner sc = new Scanner(new File("C://Users//Byron//Downloads//algorithms-repo-ByronOG-main//algorithms-repo-ByronOG-main//Algorithms//src//p4//input.txt"));
        StdOut.println("--- Timing Analysis of Brute Force vs KMP implementations ---");
        StdOut.println("Search times in milliseconds:");
        StdOut.println("Text Size\tBrute Force Search\tKMP Search\tFirst Index that pattern is found (BF)\tFirst Index that pattern is found (KMP)");
        while (sc.hasNextLine()) {
            // The text file contains alternating lines of patterns and text, each tes is 2 lines long
            String text = sc.nextLine();
            String pattern = sc.nextLine();

            long startTime = System.nanoTime();
            ArrayList<Integer> indexesBF = StringSearchingBruteForce.bruteForce(text, pattern);
            long bruteForce = System.nanoTime() - startTime;
            double bfMS = (double) bruteForce / 100000;

            startTime = System.nanoTime();
            ArrayList<Integer> indexesKMP = KMPSearch.search(pattern, text);
            long kmp = System.nanoTime() - startTime;
            double kmpMS = (double) kmp / 100000;

            //get the first index of where the pattern was found in the text
            int i1 = indexesBF.get(0);
            int i2 = indexesKMP.get(0);
            StdOut.printf("%8d\t%18f\t%10f\t%38d\t%38d\n", text.length(), bfMS, kmpMS, i1, i2);
        }
    }
}
