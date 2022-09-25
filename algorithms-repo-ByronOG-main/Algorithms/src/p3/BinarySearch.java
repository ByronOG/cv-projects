package p3;

import java.util.*;

import a1_sorting.Sorting;
import util.StdOut;

public class BinarySearch {

    /**
     * array implementation of the binary search algorithm
     * Space Complexity: O(1)
     * Time Complexity:
     * Worst: O(log N) -> the elem is the final comparison
     * Best: O(1) -> when the element is present at the middle index
     * Average: O(log N)
     *
     * @param array:     sorted array to be searched
     * @param elem       : element to be searched for
     * @param comparator : used to compare generics
     * @param <T>        : used to for generics implementation
     * @return : index of element, -1 if element is not present
     */
    public static <T extends Comparable<T>> int binarySearch(final T[] array, final T elem, final Comparator<T> comparator) {

        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            //check if element is present at middle
            if (comparator.compare(array[mid], elem) == 0) {
                return mid;
            }

            if (comparator.compare(array[mid], elem) < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        //element is not present
        return -1;
    }

    /**
     * List<T> implementation of the binary search algorithm
     * Space Complexity: O(N) -> converts the given list to an array
     * Time Complexity:
     * Worst: O(log N)
     * Best: O(1) -> when the element is present at the middle index
     * Average: O(log N)
     *
     * @param list       : sorted list to be searched
     * @param elem       : element to be searched for
     * @param comparator : used to compare generics
     * @param <T>        : used to implement generics
     * @return : index of element, -1 if element is not present
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> int binarySearch(final List<T> list,
                                                             final T elem, final Comparator<T> comparator) {

        T[] array = (T[]) new Comparable[list.size()];
        array = list.toArray(array);
        return binarySearch(array, elem, comparator);
    }

    /**
     * array implementation of the recursive binary search algorithm
     * Space Complexity: O(1)
     * Time Complexity:
     * Worst: O(log N) -> the elem is in the last recursive call
     * Best: O(1) -> when the element is present at the middle index
     * Average: O(log N)
     *
     * @param array      : sorted array
     * @param elem       : element to be searched for
     * @param comparator : generics
     * @param <T>        : generics
     * @return : index of element, -1 if element is not present
     */
    public static <T extends Comparable<T>> int binarySearchRecursive(final T[]
                                                                              array, final T elem, final Comparator<T> comparator) {
        //if array is length 1, check if element is present
        if (array.length < 2) {
            if (comparator.compare(array[0], elem) == 0) {
                return 0;
            } else {
                return -1;
            }
        }
        int r = array.length - 1;
        int l = 0;

        int mid = l + (r - l) / 2;

        //if elem is present at mid
        if (comparator.compare(array[mid], elem) == 0) {
            return mid;
        }

        //if elem is smaller than mid, elem is present in left subarray
        if (comparator.compare(array[mid], elem) > 0) {
            return binarySearchSubArray(array, elem, comparator, l, mid - 1);
        }

        //else elem can only be present in right subarray
        return binarySearchSubArray(array, elem, comparator, mid + 1, r);
    }

    //method used in binarySearchRecursive
    // -> recursively searches the sub arrays for the element
    public static <T extends Comparable<T>> int binarySearchSubArray(final T[] array, final T elem,
                                                                     final Comparator<T> comparator, int l, int r) {

        if (r >= l) {
            int mid = l + (r - l) / 2;

            //if elem is present at mid
            if (comparator.compare(array[mid], elem) == 0) {
                return mid;
            }

            //if elem is smaller than mid, elem is present in left subarray
            if (comparator.compare(array[mid], elem) > 0) {
                return binarySearchSubArray(array, elem, comparator, l, mid - 1);
            }

            //else elem can only be present in right subarray
            return binarySearchSubArray(array, elem, comparator, mid + 1, r);
        }
        //else element is not present
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("\n----- Practical 3: Binary Search -----");
        Integer[] sizeOfArrays = {8, 10, 12, 100, 1000, 4000, 10000, 20000, 40000, 80000, 100000};
        ArrayList<Integer[]> sortedArrays = new ArrayList<>();
        generateSortedArrays(sizeOfArrays, sortedArrays);

        runTimingAnalysis(sortedArrays);
    }

    private static void runTimingAnalysis(ArrayList<Integer[]> sortedArrays) {
        System.out.println("\n--------- Timing Analysis ---------");
        System.out.println("--------- Binary Search ---------");
        StdOut.printf("\n%10s\t%10s\t\t%8s", "Input Size (n)", "index", "Run Time (ms)\n");
        for (Integer[] arr : sortedArrays) {
            long startTime = System.nanoTime();
            int index = binarySearch(arr, 9, Comparator.comparing(Integer::intValue));
            long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%13d\t%10d\t%16f\n", arr.length, index, runTimeMS);
        }
        System.out.println("\n\n--------- Binary Search Recursive ---------");
        StdOut.printf("\n%10s\t%10s\t\t%8s", "Input Size (n)", "index", "Run Time (ms)\n");
        for (Integer[] arr : sortedArrays) {
            long startTime = System.nanoTime();
            int index = binarySearchRecursive(arr, 9, Comparator.comparing(Integer::intValue));
            long runTime = System.nanoTime() - startTime;
            double runTimeMS = (double) runTime / 100000;
            StdOut.printf("%13d\t%10d\t%16f\n", arr.length, index, runTimeMS);
        }
    }

    private static void generateSortedArrays(Integer[] sizeOfArrays, ArrayList<Integer[]> sortedArrays) {
        sortedArrays.clear();
        for (int size : sizeOfArrays) {
            sortedArrays.add(generateSortedArray(size));
        }
    }

    private static Integer[] generateSortedArray(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
