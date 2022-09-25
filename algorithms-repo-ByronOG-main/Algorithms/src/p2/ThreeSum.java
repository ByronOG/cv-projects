package p2;

import util.StdOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ThreeSum {
    private static final List<String> filePaths = new ArrayList<>();

    static {
        filePaths.add("C:\\Users\\Byron\\Downloads\\algorithms-repo-ByronOG-main\\algorithms-repo-ByronOG-main\\Algorithms\\src\\p2\\1Kints.txt");
        filePaths.add("C:\\Users\\Byron\\Downloads\\algorithms-repo-ByronOG-main\\algorithms-repo-ByronOG-main\\Algorithms\\src\\p2\\2Kints.txt");
        filePaths.add("C:\\Users\\Byron\\Downloads\\algorithms-repo-ByronOG-main\\algorithms-repo-ByronOG-main\\Algorithms\\src\\p2\\4Kints.txt");
        filePaths.add("C:\\Users\\Byron\\Downloads\\algorithms-repo-ByronOG-main\\algorithms-repo-ByronOG-main\\Algorithms\\src\\p2\\8Kints.txt");
        // 16Kints not added as file size is too big
        //filePaths.add("C:\\Users\\Byron\\IdeaProjects\\algorithms-repo-ByronOG\\Algorithms\\src\\p2\\16Kints.txt");
    }

    public static void main(final String[] args) {
        StdOut.println("----- Practical 2: Complexity Analysis -----");
        StdOut.println("Note: The program can take several minutes to run due to the complexities of the algorithms");

        StdOut.println("\n----- Timing Analysis -----");
        StdOut.println("\n----- ThreeSumA -----");
        StdOut.printf("\n%16s\t%16s\t\t%18s", "File Size (Kints)", "counts", "Run Time (ms)\n");

        int fileSize = 1;
        for (final String path : filePaths) {
            // do something
            try {
                int[] pathInt = loadFileAsIntArray(path);
                final long startTime = System.currentTimeMillis();
                final int counts = threeSumA(pathInt);
                final long runTime = System.currentTimeMillis() - startTime;
                StdOut.printf("%16d\t%16d\t%20d\n", fileSize, counts, runTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileSize *= 2;
        }

        StdOut.println("\n----- ThreeSumB -----");
        StdOut.printf("\n%16s\t%16s\t\t%18s", "File Size (Kints)", "counts", "Run Time (ms)\n");
        fileSize = 1;
        for (final String path : filePaths) {
            // do something
            try {
                int[] pathInt = loadFileAsIntArray(path);
                final long startTime = System.currentTimeMillis();
                final int counts = threeSumB(pathInt);
                final long runTime = System.currentTimeMillis() - startTime;
                StdOut.printf("%16d\t%16d\t%20d\n", fileSize, counts, runTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileSize *= 2;
        }
    }

    public static int threeSumA(final int[] array) {
        final int length = array.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int threeSumB(final int[] array) {
        final int length = array.length;
        Arrays.sort(array);
        if (Arrays.stream(array).distinct().toArray().length != length) {
            throw new IllegalArgumentException("Input array contains duplicates");
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int k = Arrays.binarySearch(array, -(array[i] + array[j]));
                if (k > j) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] loadFileAsIntArray(final String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath)).stream().mapToInt(Integer::parseInt).toArray();
    }
}
