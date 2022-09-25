package p5;

import java.util.Scanner;

public class runLengthEncoding {
    /**
     * This method takes a string as input and prints the encoded string to the console.
     * Run Length encoding works best on strings with repeated characters
     *
     * @param input : String to be compressed
     */
    public static void printRLE(final String input) {
        int i = 0;
        char tmp;
        int n = input.length();

        while (i < n) {
            tmp = input.charAt(i);
            int counter = 1;
            boolean found = false; //used in second loop as edge condition
            int j = i + 1;

            while (j <= n && !found) { //once the next index doesn't equal temp char

                //if the same increment counter
                if (tmp == input.charAt(j)) {
                    counter++;
                    j++;
                    //if j is out of bounds print tmp and counter
                    if (j >= n) {
                        System.out.print("" + tmp + counter);
                        break;
                    }

                } else { //else print char and its count
                    found = true;
                    System.out.print("" + tmp + "" + counter);
                }
            }
            //add the counter tally to i
            i += counter;
        }
    }

    public static void main(String[] args) {
        System.out.println("----- Run Length Encoding -----");
        System.out.println("RLE takes a string as input and returns a compressed string. Here is an example:");
        System.out.print("Input: aaaaaaaaaaabbbbbbbbbbcccccccccccdddddddddddddd\nOutput: ");
        printRLE("aaaaaaaaaaabbbbbbbbbbcccccccccccdddddddddddddd");

        System.out.println("\n\nNow you can try it for yourself!\nEnter a string to compress:");
        Scanner s = new Scanner(System.in);
        String str = s.next();
        System.out.print("Input: " + str + "\nOutput: ");
        printRLE(str);
    }
}
