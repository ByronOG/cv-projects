package a1_sorting;

import util.StdIn;
import util.StdOut;

import java.util.*;

public class Sorting {

    /**
     * Time complexity is O(N * N!) on average. Best case = O(N), worst: infinity
     * The algorithm only requires auxiliary variables for flags, temporary variables and thus the space complexity is O(1).
     * Bogo sort is one of most inefficient sorting algorithms.
     * As N increases, so does the number of potential permutations of the array, the time complexity grows exponentially because of this
     *
     * @param list
     * @param comparator
     * @param <T>
     */
    public static <T extends Comparable<T>> void bogoSort(final List<T> list, final Comparator<T> comparator) {
        // TODO

        //while list is not sorted, shuffle list
        while (!(isListSorted(list, comparator))) {
            Collections.shuffle(list);
        }
    }

    //method to check if a list is sorted
    public static <T extends Comparable<T>> boolean isListSorted(List<T> list, final Comparator<T> comparator) {
        //if size==1, list is sorted
        if (list.size() < 2) {
            return true;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            //if not sorted, return false
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        //list is sorted
        return true;
    }

    public static <T extends Comparable<T>> void bogoSort(final T[] array, final Comparator<T> comparator) {
        // TODO

        ArrayList<T> arrayAsList = new ArrayList<>();
        arrayAsList.addAll(Arrays.asList(array));

        while (!(isListSorted(arrayAsList, comparator))) {
            Collections.shuffle(arrayAsList);
        }

        arrayAsList.toArray(array);
    }


    /**
     * BUBBLESORT
     * The algorithm only requires auxiliary variables for flags, temporary variables and thus the space complexity is O(1).
     * Time complexity:
     * Worst: O(N^2) -> when the inputted array is in reverse order
     * Best: O(N) -> when the array is already sorted
     * Average O(N^2) -> The number of comparisons is constant in Bubble Sort so in average case, there is O(N^2) comparisons.
     * This is because irrespective of the arrangement of elements, the number of comparisons C(N) is the same.
     *
     * @param list
     * @param comparator
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void bubbleSort(final List<T> list, final Comparator<T> comparator) {
        // TODO
        //list is already sorted
        if (list.size() < 2) {
            return;
        }

        int i, j;
        boolean swapped;
        for (i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (j = 0; j < list.size() - i - 1; j++) {

                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            // if no two elements were swapped by inner loop, then break
            if (!swapped)
                break;
        }

    }

    public static <T extends Comparable<T>> void bubbleSort(final T[] array, final Comparator<T> comparator) {
        //array is already sorted
        if (array.length < 2) {
            return;
        }

        int i, j;
        boolean swapped;
        for (i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (j = 0; j < array.length - i - 1; j++) {

                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // if no two elements were swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

    /**
     * SELECTIONSORT
     * <p>
     * The algorithm only requires auxiliary variables for flags, temporary variables and thus the space complexity is O(1).
     * Time complexity:
     * Worst: O(N^2) -> when the smallest element is last in the array. There will be N swaps and N comparisons, thus O(N^2)
     * Best: O(N^2) -> when the array is sorted. There is 0 swaps and N comparisons
     * Average: O(N^2) -> Based on the worst case and best case, we know that the number of comparisons will be the same for every case and hence,
     * for average case as well, the number of comparisons will be constant.
     * Number of comparisons = N * (N+1) / 2
     * Therefore, the time complexity will be O(N^2).
     *
     * @param list
     * @param comparator
     * @param <T>
     */
    public static <T extends Comparable<T>> void selectionSort(final List<T> list, final Comparator<T> comparator) {
        // TODO
        for (int i = 0; i < list.size() - 1; i++) {
            int min = i;

            for (int j = i + 1; j < list.size(); j++) {
                //if min is greater than j, assign j to min
                if (comparator.compare(list.get(min), list.get(j)) > 0) {
                    min = j;
                }
            }
            //swap
            T swap = list.get(min);
            list.set(min, list.get(i));
            list.set(i, swap);
        }
    }

    public static <T extends Comparable<T>> void selectionSort(final T[] array, final Comparator<T> comparator) {
        // TODO

        for (int i = 0; i < array.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < array.length; j++) {

                if (comparator.compare(array[min], array[j]) > 0) {
                    min = j;
                }
            }
            T swap = array[min];
            array[min] = array[i];
            array[i] = swap;
        }
    }

    /**
     * MERGESORT
     * <p>
     * O(N) space. This is because we divide the array and store each individual element(N elements in an array of size N)
     * <p>
     * Time complexity:
     * Worst: O(N logN) -> the number of comparisons is N logN
     * Best: O(N logN) -> The array is already sorted so the number of comparisons would be minimum (0.5N logN)
     * Average: O(N logN) -> Based off our worst and best cases being the same time complexity, the average is also the same
     *
     * @param list
     * @param comparator
     * @param <T>
     */
    public static <T extends Comparable<T>> void mergeSort(final List<T> list, final Comparator<T> comparator) {
        // TODO
        //if list size == 1, the list is already sorted
        if (list.size() < 2) {
            return;
        }

        //split the list into two sub lists
        int n = list.size();
        int mid = n / 2;

        T[] leftArray = (T[]) new Comparable[mid];
        T[] rightArray = (T[]) new Comparable[n - mid];

        for (int i = 0; i < mid; i++) {
            leftArray[i] = list.get(i);
        }
        for (int i = mid; i < n; i++) {
            rightArray[i - mid] = list.get(i);
        }

        //sort both sub lists
        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        //merge the sorted sub lists
        merge(list, leftArray, rightArray, mid, n - mid, comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort(final T[] array, final Comparator<T> comparator) {
        // TODO
        //if array length == 1, the array is already sorted
        if (array.length < 2) {
            return;
        }

        //split the array in two
        int n = array.length;
        int mid = n / 2;

        T[] leftArray = (T[]) new Comparable[mid];
        T[] rightArray = (T[]) new Comparable[n - mid];

        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            rightArray[i - mid] = array[i];
        }

        //sort sub arrays
        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        //merge the now sorted sub arrays
        merge(array, leftArray, rightArray, mid, n - mid, comparator);
    }

    public static <T extends Comparable<T>> void merge(T[] array, T[] leftArray, T[] rightArray, int left, int right, final Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;

        //merge the two sorted sub-arrays into one array
        while (i < left && j < right) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }

        }

        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }

    public static <T extends Comparable<T>> void merge(List<T> list, T[] leftArray, T[] rightArray, int left, int right, final Comparator<T> comparator) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {

            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                list.set(k++, leftArray[i++]);
            } else {
                list.set(k++, rightArray[j++]);
            }
        }

        while (i < left) {
            list.set(k++, leftArray[i++]);
        }
        while (j < right) {
            list.set(k++, rightArray[j++]);
        }
    }


    /**
     * Space complexity: O(N) -> as we are not creating any container other than the given array therefore Space complexity will be in order of N
     * Time Complexity:
     * Best: O(N logN) -> when the selected pivot is the mean
     * -> as we have selected mean element as pivot then the array will be divided
     * in branches of equal size so that the height of the tree will be mininum
     * Worst: O(0.5N^2) -> when the array is sorted, as the number of comparisons is 0.5N^2
     * Average: O(N logN) -> the expected number of compares is 1.39N logN
     * <p>
     * Potential Issues:
     * 1. the algorithm has too many overheads for tiny subarrays.
     * -> use insertion sort for arrays of size <=10, can improve running time by 10-20%
     * 2. Best choice of pivot is the median of the array
     * -> estimate true median by taking median of sample(median of 3), this will improve running time
     * 3. In worst case, quicksort uses space linear in the array size; this can be modified to be logarithmic
     * -> sort the smaller of the two partitions first. This means that the largest partition is sorted last, and it is a tail call.
     * 4. When an array has a number of repeated elements the time complexity is O(N^2)
     * To reduce the worst-case scenarios when repeated elements are in large numbers,
     * instead of a single-pivot partitioning scheme, we can implement a three-way partitioning scheme.
     * -> Three-way partitioning: we can place keys in the right position when we first encounter them.
     * -> So three-way partitioning of the array consists of the following three partitions:
     * - The left-most partition contains elements that are strictly less than the pivot.
     * - The middle partition contains all elements which are equal to the pivot.
     * - The right-most partition contains all elements which are strictly greater than the pivot.
     *
     * @param list       : list to be sorted
     * @param comparator : used to compare generics
     * @param <T>        : used for generic implementation
     */
    public static <T extends Comparable<T>> void quickSort(final List<T> list, final Comparator<T> comparator) {
        //size==1, list is already sorted
        if (list.size() < 2) {
            return;
        }

        Collections.shuffle(list); //shuffle list for performance guarantee
        sort(list, 0, list.size() - 1, comparator);

    }

    //sorts the list
    public static <T extends Comparable<T>> void sort(final List<T> list, int lo, int hi, final Comparator<T> comparator) {
        //repeat until pointers cross
        if (hi <= lo) {
            return;
        }
        //partition, then sort each sub-list recursively
        int j = partition(list, lo, hi, comparator);
        sort(list, lo, j - 1, comparator);
        sort(list, j + 1, hi, comparator);

    }

    //partition so that, for some j
    // - entry list.get[j] is in place
    // - no larger entry to the left of j
    // - no smaller entry to the right of j
    private static <T extends Comparable<T>> int partition(List<T> list, int lo, int hi, final Comparator<T> comparator) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            //find item on left to swap
            while (comparator.compare(list.get(++i), list.get(lo)) < 0) {
                if (i == hi) {
                    break;
                }
            }

            //find item on right to swap
            while (comparator.compare(list.get(lo), list.get(--j)) < 0) {
                if (j == lo) {
                    break;
                }
            }

            //check if pointers cross
            if (i >= j) {
                break;
            }

            swap(list, i, j); //swap
        }
        //swap with partitioning item
        swap(list, lo, j);

        return j; //return index of item now known to be in place
    }

    //swap items at index i and j in list
    private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static <T extends Comparable<T>> void quickSort(final T[] array, final Comparator<T> comparator) {
        quickSort(Arrays.asList(array), comparator);
    }

    public static <E> void printArray(E[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i + 1 == array.length) {
                System.out.print(array[i] + "]");
            } else {
                System.out.print(array[i] + ", ");
            }
        }
    }

    // Prompt the user to choose from a menu
    private static void promptUser() {
        StdOut.println("\nUser Options:");
        StdOut.println("1. Run timing analysis");
        StdOut.println("2. Visualise Sorted Arrays");
        StdOut.print("Choose option 1 or 2 (0 to exit): ");
    }

    public static void visualiseSortedArrays(ArrayList<Integer[]> randomArrays) {
        System.out.print("\n\nBogo Sort!");
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print("\nOriginal Array: ");
            printArray(randomArrays.get(i));
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            bogoSort(copy, Comparator.comparing(Integer::intValue));
            System.out.print("\nSorted Array: ");
            printArray(copy);
        }

        printLine();
        System.out.print("\n\nBubble Sort!");
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print("\nOriginal Array: ");
            printArray(randomArrays.get(i));
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            bubbleSort(copy, Comparator.comparing(Integer::intValue));
            System.out.print("\nSorted Array: ");
            printArray(copy);
        }

        printLine();
        System.out.print("\n\nSelection Sort!");
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print("\nOriginal Array: ");
            printArray(randomArrays.get(i));
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            selectionSort(copy, Comparator.comparing(Integer::intValue));
            System.out.print("\nSorted Array: ");
            printArray(copy);
        }

        printLine();
        System.out.print("\n\nMerge Sort!");
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print("\nOriginal Array: ");
            printArray(randomArrays.get(i));
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            mergeSort(copy, Comparator.comparing(Integer::intValue));
            System.out.print("\nSorted Array: ");
            printArray(copy);
        }

        printLine();
        System.out.print("\n\nQuick Sort!");
        for (int i = 0; i < 3; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print("\nOriginal Array: ");
            printArray(randomArrays.get(i));
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            quickSort(copy, Comparator.comparing(Integer::intValue));
            System.out.print("\nSorted Array: ");
            printArray(copy);
        }
        printLine();
    }

    public static void runTimingAnalysis(ArrayList<Integer[]> randomArrays) {
        System.out.println("\n----- Bogo Sort! -----");
        StdOut.printf("\n%16s\t\t%18s", "Input Size (N)", "Run Time (ms)\n");
        //stop at index 3 due to the algorithm's exponential time complexity
        for (int i = 0; i < 3; i++) {
            final long startTime = System.currentTimeMillis();
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            bogoSort(copy, Comparator.comparing(Integer::intValue));
            final long runTime = System.currentTimeMillis() - startTime;
            StdOut.printf("%16d\t%20d\n", copy.length, runTime);
        }

        System.out.println("\n----- Bubble Sort! -----");
        StdOut.printf("\n%16s\t\t%18s", "Input Size (N)", "Run Time (ms)\n");
        //stop at index 8 due to the algorithm's quadratic time complexity
        for (int i = 0; i < 8; i++) {
            final long startTime = System.currentTimeMillis();
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            bubbleSort(copy, Comparator.comparing(Integer::intValue));
            final long runTime = System.currentTimeMillis() - startTime;
            StdOut.printf("%16d\t%20d\n", copy.length, runTime);
        }

        System.out.println("\n----- Selection Sort! -----");
        StdOut.printf("\n%16s\t\t%18s", "Input Size (N)", "Run Time (ms)\n");
        //stop at index 8 due to the algorithm's quadratic time complexity
        for (int i = 0; i < 8; i++) {
            final long startTime = System.currentTimeMillis();
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            selectionSort(copy, Comparator.comparing(Integer::intValue));
            final long runTime = System.currentTimeMillis() - startTime;
            StdOut.printf("%16d\t%20d\n", copy.length, runTime);
        }

        System.out.println("\n----- Merge Sort! -----");
        StdOut.printf("\n%16s\t\t%18s", "Input Size (N)", "Run Time (ms)\n");
        for (int i = 0; i < 11; i++) {
            final long startTime = System.currentTimeMillis();
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            mergeSort(copy, Comparator.comparing(Integer::intValue));
            final long runTime = System.currentTimeMillis() - startTime;
            StdOut.printf("%16d\t%20d\n", copy.length, runTime);
        }

        System.out.println("\n----- Quick Sort! -----");
        StdOut.printf("\n%16s\t\t%18s", "Input Size (N)", "Run Time (ms)\n");
        for (int i = 0; i < 11; i++) {
            final long startTime = System.currentTimeMillis();
            Integer[] copy = new Integer[randomArrays.get(i).length];
            System.arraycopy(randomArrays.get(i), 0, copy, 0, randomArrays.get(i).length);
            quickSort(copy, Comparator.comparing(Integer::intValue));
            final long runTime = System.currentTimeMillis() - startTime;
            StdOut.printf("%16d\t%20d\n", copy.length, runTime);
        }
        printLine();
    }


    public static void generateRandomArrays(Integer[] sizeOfArrays, ArrayList<Integer[]> arr) {
        arr.clear();
        for (int size : sizeOfArrays) {
            arr.add(generateRandomArray(size));
        }
    }

    public static Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // Fill array with random integers
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    // Print a line of dashes
    private static void printLine() {
        System.out.println();
        for (int i = 0; i < 77; i++) {
            StdOut.print("-");
        }
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

    public static void main(String[] args) {
        System.out.println("\n----- Sorting Assignment -----");
        Integer[] sizeOfArrays = {8, 10, 12, 100, 1000, 4000, 10000, 20000, 40000, 80000, 100000};
        ArrayList<Integer[]> randomArrays = new ArrayList<>();
        generateRandomArrays(sizeOfArrays, randomArrays);

        promptUser();
        int choice = readInt();

        while (choice != 0) {
            switch (choice) {
                case 0:
                    break;
                case 1:
                    runTimingAnalysis(randomArrays);
                    break;
                case 2:
                    visualiseSortedArrays(randomArrays);
                    break;
                default:
                    StdOut.println("Invalid input, please try again!");
            }
            promptUser();
            choice = readInt();
        }
    }
}
