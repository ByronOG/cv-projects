package p1;

import util.StdIn;
import util.StdOut;

import java.util.InputMismatchException;

public class RussianPeasantAlgorithm {

    /**
     * Implementation of the Russian Peasant's Algorithm
     * Time Complexity: O(log n)
     * Auxiliary Space Complexity: O(1)
     *
     * @param x : long input from user
     * @param y : long input from user
     * @return product of x times y
     */
    public static long russianPeasantMult(long x, long y) {
        long total = 0;
        while (x != 0) {
            //if n is odd, add the larger number to the total
            if (x % 2 != 0) {
                total += y;
            }
            x /= 2;
            y *= 2;
        }
        return total;
    }

    // Run a timing analysis for inputs of different sizes
    private static void runTimingAnalysis() {
        StdOut.println("\n----- Timing Analysis -----");
        StdOut.printf("\n%10s\t%10s\t%16s\t\t%8s", "x", "y", "x * y", "Run Time (ms)\n");
        int i = 2;
        while (i < (int) (Math.pow(2, 11))) {
            final long startTime = System.nanoTime();
            long answer = russianPeasantMult(i, i);
            final long elapsedTime = System.nanoTime() - startTime;
            double runTime = (double) elapsedTime / 100000;
            StdOut.printf("%10d\t%10d\t%16d\t%12f\n", i, i, answer, runTime);
            i *= 2;
        }
    }

    // Prompt the user to choose from a menu
    private static void promptUser() {
        StdOut.println("\nUser Options:");
        StdOut.println("1. Run timing analysis");
        StdOut.println("2. Multiply two numbers");
        StdOut.print("Choose option 1 or 2 (0 to exit): ");
    }

    // Ensures that user enters valid input
    private static long readLong() {
        long input = -1;
        while (input == -1) {
            try {
                input = StdIn.readLong();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid input, please try again!\n");
                promptUser();
            }
        }
        return input;
    }

    // Main method to test the Russian Peasant's algorithm
    public static void main(String[] args) {
        System.out.println("\n--- Russian Peasant's Algorithm ---");
        promptUser();
        long choice = readLong();

        while (choice != 0) {
            switch ((int) choice) {
                case 0:
                    break;
                case 1:
                    runTimingAnalysis();
                    break;
                case 2:
                    StdOut.println("Enter two numbers to multiply:");
                    long x = readLong();
                    long y = readLong();

                    final long startTime = System.nanoTime();
                    long answer = russianPeasantMult(x, y);
                    final long elapsedTime = System.nanoTime() - startTime;
                    double runTime = (double) elapsedTime / 100000;

                    StdOut.println("" + x + " x " + y + " = " + answer);
                    StdOut.println("Time taken: " + runTime + " ms");
                    break;
                default:
                    StdOut.println("Invalid input, please try again!");
            }
            promptUser();
            choice = readLong();
        }
    }
}
