package a3_DynamicProgramming;

import util.StdIn;
import util.StdOut;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class DynamicProgrammingAssignment {

    /**
     * Recursive Implementation
     * Time Complexity: O(2^n)
     * Auxiliary Space Complexity: O(1)
     */
    public static int fibRecursive(final int n) {

        if (n <= 1) {
            return n;
        } else {
            return fibRecursive(n - 1) + fibRecursive(n - 2);
        }
    }

    /**
     * Dynamic Programming Implementation
     * Time Complexity: O(n)
     * Auxiliary Space Complexity: O(n)
     * <p>
     * Comparison:
     * The recursive implementation has exponential time complexity. The time taken is directly proportional to the square of the input size.
     * The DP implementation has time complexity O(N). The time taken increases linearly and in proportion to the number of inputs
     * Conclusion: The DP implementation is superior to the recursive implementation, especially with large inputs.
     */
    public static int fibDP(final int n) {
        if (n < 3) {
            return 1;
        }
        int[] F = new int[n];

        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i < n; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }

        return F[n - 1] + F[n - 2];
    }

    /**
     * Longest Common Substring
     * Time Complexity: O(m*n)
     * Auxiliary Space Complexity: O(m*n)
     * <p>
     * Time complexity is optimum for this solution.
     * The auxiliary space complexity can be approved upon, as only the last row is being used in the LCTable.
     */
    public static int LCS(final String s1, final String s2) {

        int m = s1.length();
        int n = s2.length();

        // Create table which stores the length of the longest common suffixes of substrings
        // LCTable[i][j] stores length of longest common suffix of Strings s1 and s2
        int[][] LCTable = new int[m + 1][n + 1];

        // store length of longest common substring
        int length = 0;

        //build LCTable
        //find the length of the longest common suffix for all substrings of both strings and store these lengths in LCTable
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    LCTable[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) { //if last characters match, reduce both lengths by 1
                    LCTable[i][j] = LCTable[i - 1][j - 1] + 1;
                    length = Integer.max(length, LCTable[i][j]); //the maximum length longest common SUFFIX is the longest common SUBSTRING
                } else { //last characters do not match -> length is 0
                    LCTable[i][j] = 0;
                }
            }
        }
        return length;
    }

    /**
     * Brute Force implementation of the knapsack problem
     * Time Complexity: O(2^n) -> there are many redundant subproblems
     * Auxiliary Space Complexity: O(1) -> no extra data structures added
     */
    public static int knapsackBruteForce(final int W, final int[] w, final int[] vals) {

        int n = vals.length;
        return knapsackBFHelper(W, w, vals, n);
    }

    private static int knapsackBFHelper(int W, int[] w, int[] vals, int n) {
        // base Case
        if (n == 0 || W == 0) {
            return 0;
        }

        // If weight of the nth item is more than W, then this item is not included
        if (w[n - 1] > W) {
            return knapsackBFHelper(W, w, vals, n - 1);
        } else { //return max of two cases: 1. nth item included 2. nth item not included
            return Math.max(vals[n - 1] + knapsackBFHelper(W - w[n - 1], w, vals, n - 1), knapsackBFHelper(W, w, vals, n - 1));
        }
    }

    /**
     * Dynamic Programming implementation of the knapsack problem
     * Time Complexity: O(n*W) -> for every weight element we traverse through all weight capacities 1 <= w <= W
     * Auxiliary Space Complexity O(n*W) -> use of a 2D array of size n*W
     * <p>
     * Comparison:
     * The brute force implementation has exponential time complexity. The time taken is directly proportional to the square of the input size.
     * The DP implementation has time complexity O(n*W). The time taken increases linearly and in proportion to the number of inputs * W(capacity)
     * As you can see by the time complexities, the DP approach is superior.
     * This superiority comes at a slight auxiliary space cost.
     */
    public static int knapsackDP(final int W, final int[] w, final int[] vals) {

        int n = vals.length;
        return knapsackDPHelper(W, w, vals, n);
    }

    private static int knapsackDPHelper(int W, int[] w, int[] vals, int n) {

        int i, j;
        int[][] Table = new int[n + 1][W + 1];

        // Build table from the bottom up
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    Table[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    Table[i][j] = Math.max(Table[i - 1][j], vals[i - 1] + Table[i - 1][j - w[i - 1]]);
                } else {
                    Table[i][j] = Table[i - 1][j];
                }
            }
        }
        return Table[n][W];
    }


    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 46};
        int[] sizeOfArrays = {8, 10, 12, 20, 50, 80, 100, 400, 1000, 4000, 8000, 10000, 20000, 40000, 80000, 100000};
        int[] sizeOfStrings = {8, 10, 12, 20, 50, 80, 100, 400, 1000, 4000, 8000, 10000};
        int[] sizeOfSmallerStrings = {4, 5, 6, 10, 25, 40, 50, 200, 500, 2000, 4000, 5000, 10000};

        ArrayList<String> randomStrings1 = new ArrayList<>();
        ArrayList<String> randomStrings2 = new ArrayList<>();
        generateRandomStringArray(sizeOfStrings, randomStrings1);
        generateRandomStringArray(sizeOfSmallerStrings, randomStrings2);

        ArrayList<int[]> randomValArrays = new ArrayList<>();
        ArrayList<int[]> randomWeightArrays = new ArrayList<>();
        generateRandomArrays(sizeOfArrays, randomValArrays);
        generateRandomArrays(sizeOfArrays, randomWeightArrays);

        demonstrateBasicFunctionality();

        promptUser();
        int choice = readInt();

        while (choice != 0) {
            switch (choice) {
                case 0:
                    break;
                case 1:
                    runTimingAnalysisFib(numbers);
                    break;
                case 2:
                    runTimingAnalysisLCS(randomStrings1, randomStrings2);
                    break;
                case 3:
                    runTimingAnalysisKnapSack(randomValArrays, randomWeightArrays);
                    break;
                default:
                    StdOut.println("Invalid input, please try again!");
            }
            promptUser();
            choice = readInt();
        }
    }

    private static void demonstrateBasicFunctionality() {
        System.out.println("\n--- Assignment 3: Dynamic Programming ---");
        System.out.println("First I will demonstrate the basic functionality of my functions. You will be able to run timing analysis after.");
        System.out.println("\nWhich fibonacci number would you like to compute (enter positive value): ");
        int choice = readInt();
        while (choice < 1) {
            System.out.println("Enter a positive number:");
            choice = readInt();
        }
        System.out.println("Fibonacci number " + choice + " is: " + fibDP(choice));

        System.out.println("Enter two strings (separated by whitespace): ");
        Scanner s = new Scanner(System.in);
        String s1 = s.next();
        String s2 = s.next();
        System.out.println("\nThe length of the Least Common Substring between strings: " + s1 + ", " + s2 + " is : " + LCS(s1, s2));

        int[] vals = new int[]{20, 70, 30, 50};
        int[] w = new int[]{2, 1, 2, 1};
        System.out.println("\nKnapsack Problem: ");
        System.out.print("Vals array: ");
        printArray(vals);
        System.out.print("\nWeight array: ");
        printArray(w);
        System.out.println("\nW = 5. \nAnswer is: " + knapsackDP(5, w, vals));
    }

    // Ensures that user enters an integer choice
    private static int readInt() {
        int input = -1;
        while (input == -1) {
            try {
                input = StdIn.readInt();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid input, please try again!\n");
                promptUser();
            }
        }
        return input;
    }

    private static void generateRandomStringArray(int[] sizeOfStrings, ArrayList<String> arr) {
        for (int size : sizeOfStrings) {
            arr.add(getRandomString(size));
        }
    }


    private static void generateRandomArrays(int[] sizeOfArrays, ArrayList<int[]> arr) {
        arr.clear();
        for (int size : sizeOfArrays) {
            arr.add(generateRandomArray(size));
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // Fill array with random integers
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    private static void runTimingAnalysisKnapSack(ArrayList<int[]> vals, ArrayList<int[]> weights) {
        int W = 5;
        System.out.println("\n----- Timing Analysis of Brute Force KnapSack Implementation -----");
        StdOut.printf("\n%10s\t%16s\t%16s\t\t%8s", "n", "W", "Answer", "Run Time (ms)\n");

        for (int i = 0; i < 10; i++) {
            int[] copyWeights = new int[weights.get(i).length];
            System.arraycopy(weights.get(i), 0, copyWeights, 0, weights.get(i).length);
            int[] copyVals = new int[vals.get(i).length];
            System.arraycopy(vals.get(i), 0, copyVals, 0, vals.get(i).length);

            final long startTime = System.nanoTime();
            int answer = knapsackBruteForce(W, copyWeights, copyVals);
            final long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%10d\t%16d\t%16d\t%16f\n", copyVals.length, W, answer, runTimeMS);
        }

        System.out.println("\n----- Timing Analysis of Dynamic Programming KnapSack Implementation -----");
        StdOut.printf("\n%10s\t%16s\t%16s\t\t%8s", "n", "W", "Answer", "Run Time (ms)\n");
        for (int i = 0; i < 13; i++) {
            int[] copyWeights = new int[weights.get(i).length];
            System.arraycopy(weights.get(i), 0, copyWeights, 0, weights.get(i).length);
            int[] copyVals = new int[vals.get(i).length];
            System.arraycopy(vals.get(i), 0, copyVals, 0, vals.get(i).length);

            final long startTime = System.nanoTime();
            int answer = knapsackDP(W, copyWeights, copyVals);
            final long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%10d\t%16d\t%16d\t%16f\n", copyVals.length, W, answer, runTimeMS);
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i + 1 == array.length) {
                System.out.print(array[i] + "]");
            } else {
                System.out.print(array[i] + ", ");
            }
        }
    }

    public static void runTimingAnalysisFib(int[] numbers) {
        StdOut.printf("\n----- Timing Analysis ----- ");
        StdOut.printf("\n----- Recursive Fibonacci Implementation -----");
        StdOut.printf("\n%10s\t%16s\t\t%8s", "n", "fib(n)", "Run Time (ms)\n");
        for (int number : numbers) {
            final long startTime = System.nanoTime();
            int fib = fibRecursive(number);
            final long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%10d\t%16d\t%16f\n", number, fib, runTimeMS);
        }

        StdOut.printf("\n----- Dynamic Programming Fibonacci Implementation -----");
        StdOut.printf("\n%10s\t%16s\t\t%8s", "n", "fib(n)", "Run Time (ms)\n");
        for (int number : numbers) {
            final long startTime = System.nanoTime();
            int fib = fibDP(number);
            final long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%10d\t%16d\t%16f\n", number, fib, runTimeMS);
        }
    }

    public static void runTimingAnalysisLCS(ArrayList<String> strings1, ArrayList<String> strings2) {
        System.out.println("\n----- Timing Analysis of Least Common Substring -----");
        StdOut.printf("\n%10s\t%16s\t%16s\t%16s\t\t%8s", "m", "n", "m*n", "LCS", "Run Time (ms)\n");
        for (int i = 0; i < strings1.size(); i++) {
            String str1 = strings1.get(i);
            String str2 = strings2.get(i);
            final long startTime = System.nanoTime();
            int answer = LCS(str1, str2);
            final long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            int mTimesN = str1.length() * str2.length();
            StdOut.printf("%10d\t%16d\t%16d\t%16d\t%16f\n", str1.length(), str2.length(), mTimesN, answer, runTimeMS);
        }
    }

    //method to generate a random string
    public static String getRandomString(int n) {

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (str.length() * Math.random());
            sb.append(str.charAt(index));
        }

        return sb.toString();
    }

    // Prompt the user to choose from a menu
    private static void promptUser() {
        StdOut.println("\nUser Options:");
        StdOut.println("1. Run timing analysis on Fibonacci Functions");
        StdOut.println("2. Run Timing Analysis on LCS implementation");
        StdOut.println("3. Run timing analysis on the Knapsack Problem");
        StdOut.print("Choose option 1, 2 or 3 (0 to exit): ");
    }
}
