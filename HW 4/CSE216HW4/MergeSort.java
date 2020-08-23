// Nitin Dev
// NDEV
// 112298641

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    private static final Random RNG = new Random(10982755L);
    private static final int LENGTH = 1024000;
    private static final int NUMBEROFTHREADS = 1;

    public static void main(String... args) {
        int[] arr = randomIntArray();
        long start = System.currentTimeMillis();
        concurrentMergeSort(arr);
        long end = System.currentTimeMillis();
        if (!sorted(arr)) {
            System.err.println("The final array is not sorted");
            System.exit(0);
        }
        System.out.printf("%10d numbers: %6d ms%n", LENGTH, end - start);
    }

    private static int[] randomIntArray() {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < arr.length; i++)
            arr[i] = RNG.nextInt(LENGTH * 10);
        return arr;
    }

    public static boolean sorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void concurrentMergeSort(int[] arr) {
        concurrentMergeSort(arr, NUMBEROFTHREADS);
    }

    public static void concurrentMergeSort(int[] arr, int threadCount) {
        if (threadCount <= 1) {
            mergingDifferentParts(arr);
        } else {
            int middle = (arr.length) / 2;
            int[] left = new int[middle + 1];                       // length of left = 0 to middle
            int[] right = new int[(arr.length - 1) - middle];       // length of right = middle to end

            System.arraycopy(arr, 0, left, 0, middle + 1);            // copy contents of left array ... IDE generated
            for (int y = 0; y < ((arr.length - 1) - middle); y++) {  // copy contents of right array
                right[y] = arr[middle + 1 + y];
            }

            Thread leftThread = new Thread(new Sorting(left, threadCount / 2));
            Thread rightThread = new Thread(new Sorting(right, threadCount / 2));

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();          // wait for left to finish
                rightThread.join();         // wait for right to finish
            } catch (InterruptedException ie) {
            }
            mergeALGORITHM(left, right, arr);
        }
    }


    //// MERGING STUFF  -- BELOW

    public static void mergingDifferentParts(int[] arr) {
        int ARR_LEN = arr.length;
        if (ARR_LEN < 2)
            return;
        int mid = ARR_LEN / 2;
        int[] left_Array_of_number = Arrays.copyOfRange(arr, 0, mid);
        int[] right_Array_of_number = Arrays.copyOfRange(arr, mid, ARR_LEN);

        mergingDifferentParts(left_Array_of_number);
        mergingDifferentParts(right_Array_of_number);
        mergeALGORITHM(left_Array_of_number, right_Array_of_number, arr);
    }

    public static void mergeALGORITHM(int[] leftArr, int[] rightArr, int[] doneMergingContents) {
        int left = leftArr.length;
        int right = rightArr.length;
        int x = 0;
        int y = 0;
        int z = 0;
        while ((x < left) && (y < right)) {
            if (leftArr[x] <= rightArr[y]) {
                doneMergingContents[z] = leftArr[x];
                x++;
            } else {
                doneMergingContents[z] = rightArr[y];
                y++;
            }
            z++;
        }
        while (x < left) {
            doneMergingContents[z] = leftArr[x];
            x++;
            z++;
        }
        while (y < right) {
            doneMergingContents[z] = rightArr[y];
            y++;
            z++;
        }
    }
}
