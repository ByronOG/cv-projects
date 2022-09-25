package p4;

import util.StdOut;

import java.util.ArrayList;
import java.util.Scanner;

public class KMPSearch {

    /**
     * Time Complexity: O(n) -> while loop performs n comparisons
     * -> This is just taking the search into consideration. I am assuming the runtime of lps creation is negligible.
     * Auxiliary Space Complexity: O(n) -> for every pattern matched, it's index in the text is stored in an arraylist. In the worst case the list has size N
     *
     * @param pattern : pattern to be searched for in text
     * @param text    : text to be searched
     * @return : List of indexes for each pattern matched in text
     */
    public static ArrayList<Integer> search(final String pattern, final String text) {
        int m = pattern.length();
        int n = text.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[m];
        int j = 0; // index for pattern[]

        ArrayList<Integer> indexes = new ArrayList<>();


        // Preprocess the pattern (calculate lps[] array)
        computeLPSArray(pattern, m, lps);

        int i = 0; //index for text[]
        while (i < n) {
            //if matching characters
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                //if pattern found
                if (j == m) {
                    indexes.add(i - j);
                    j = lps[j - 1]; //set j to final index of lps
                }
            } else { //else not matching
                j = lps[j]; //set j to prev index
                if (j < 1) { //if j is 0 increment both j and i
                    j++;
                    i++;
                }
            }
        }
        return indexes;
    }

    public static void computeLPSArray(final String pat, final int M, final int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { //pat[i] != pat[len]
                if (len != 0) {
                    len = lps[len - 1];
                } else { //len==0
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("\n----- String Searching Practical: Knuth-Morris-Pratt -----");
        Scanner sc = new Scanner(System.in);
        StdOut.println("--- Search for a pattern in text using Knuth Morris Pratt Search ---");
        StdOut.println("Enter text:");
        String text = sc.nextLine();
        StdOut.println("Enter pattern:");
        String pattern = sc.nextLine();
        ArrayList<Integer> indexes = search(pattern, text);
        if (indexes.size() == 0) {
            StdOut.println("Pattern not found in text");
        } else {
            for (int index : indexes) {
                System.out.println("pattern: " + pattern + " found at index: " + index);
            }
        }

    }
}

