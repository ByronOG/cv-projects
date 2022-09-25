import a1_sorting.*;
import a2_compression.*;
import a3_DynamicProgramming.*;
import p1.*;
import p2.*;
import p3.*;
import p4.*;
import p5.*;
import util.StdIn;
import util.StdOut;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("--- Algorithms Repository of Byron O'Gorman ---");
        showUserOptions();
        chooseOption(readInt());
    }

    private static void showUserOptions() {
        System.out.println("Options:");
        System.out.println("1. Russian Peasant Algorithm");
        System.out.println("2. Complexity Analysis: ThreeSum");
        System.out.println("3. Sorting Assignment");
        System.out.println("4. Binary Search");
        System.out.println("5. String Searching: Brute Force");
        System.out.println("6. String Searching: Knuth-Morris-Pratt");
        System.out.println("7. String Searching: BF VS KMP Analysis");
        System.out.println("8. Run Length Encoding");
        System.out.println("9. Compression Assignment");
        System.out.println("10. Dynamic Programming Assignment");
        System.out.println("Enter Choice (or 0 to exit):");
    }

    private static void chooseOption(int option) throws FileNotFoundException {
        switch(option) {
            case 0:
                System.out.println("Goodbye!");
                break;
            case 1:
                p1.RussianPeasantAlgorithm.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 2:
                p2.ThreeSum.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 3:
                a1_sorting.Sorting.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 4:
                p3.BinarySearch.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 5:
                p4.StringSearchingBruteForce.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 6:
                p4.KMPSearch.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 7:
                p4.StringSearchingTest.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 8:
                System.out.println("Run Length Encoding practical is run via command line.\nPlease inspect the class for further information.");
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 9:
                System.out.println("Compression Assignment is run via command line.\nPlease inspect the class for further information.");
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            case 10:
                a3_DynamicProgramming.DynamicProgrammingAssignment.main(null);
                if(doContinue()){
                    showUserOptions();
                    chooseOption(readInt());
                }
                break;
            default:
                System.out.println("Invalid choice, please try again!");
                chooseOption(readInt());
        }
    }

    // Ensures that user enters an integer choice
    private static int readInt() {
        int input = -1;
        while (input == -1) {
            try {
                input = StdIn.readInt();
            } catch (InputMismatchException e) {
                StdOut.println("Invalid choice, please try again!");
            }
        }
        return input;
    }

    // Ask user if they want to continue seeing options
    private static boolean doContinue() {
        StdOut.println("\nContinue? (y/n): ");
        String choice = StdIn.readString();
        while (!choice.equalsIgnoreCase("y") &&
                !choice.equalsIgnoreCase("n")) {
            StdOut.println("Invalid choice, please try again!\n");
            StdOut.println("Continue? (y/n): ");
            choice = StdIn.readString();
        }
        return choice.equalsIgnoreCase("y");
    }
}
