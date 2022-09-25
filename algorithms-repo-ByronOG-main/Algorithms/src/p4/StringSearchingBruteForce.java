package p4;

import util.StdOut;

import java.util.ArrayList;
import java.util.Scanner;

public class StringSearchingBruteForce {

    /**
     * Time Complexity: O(mn) -> as in the worst case it does m x n compares.
     * Auxiliary Space Complexity: O(n) -> for every pattern matched, it's index in the text is stored in an arraylist. In the worst case the list has size N
     *
     * @param text    : text to be searched
     * @param pattern : pattern to be searched for in text
     * @return : List of indexes for each pattern matched in text
     */
    public static ArrayList<Integer> bruteForce(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                //store the indexes of each pattern found
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        System.out.println("\n----- String Searching Practical: Brute Force -----");
        Scanner sc = new Scanner(System.in);
        StdOut.println("--- Search for a pattern in text using brute force ---");
        StdOut.println("Enter text:");
        String text = sc.nextLine();
        StdOut.println("Enter pattern:");
        String pattern = sc.nextLine();
        ArrayList<Integer> indexes = bruteForce(text, pattern);
        if (indexes.size() == 0) {
            StdOut.println("Pattern not found in text");
        } else {
            for (int index : indexes) {
                System.out.println("pattern: " + pattern + " found at index: " + index);
            }
        }
    }
}
