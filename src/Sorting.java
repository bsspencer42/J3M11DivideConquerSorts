import java.util.*;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Base Case - Array Length of 1
        if (arr.length <= 1){
            return;
        }

        System.out.println("test");
        // Create new sub-arrays
        int midIndex = arr.length / 2;
        T[] left = (T[]) new Object[midIndex];
        for (int i = 0; i < midIndex;i++)
            left[i] = arr[i];
        T[] right = (T[]) new Object[arr.length-midIndex];
        for (int i = 0; i < arr.length-midIndex;i++)
            right[i] = arr[midIndex+i];
        // End create sub-arrays

        // Recurse
        mergeSort(left,comparator);
        mergeSort(right,comparator);

        // Combine Left/Right
        Integer i = 0;
        Integer j = 0;
        // Compare until one array empty
        while (i < left.length && j < right.length){
            if (comparator.compare(left[i], right[j]) <= 0){
                arr[i+j] = left[i];
                i++;
            }
            else {
                arr[i+j] = right[j];
                j++;
            }
        }
        // Copy Remainder to new array
        while (j < right.length){
            arr[i+j] = right[j];
            j++;
        }
        while (i < left.length){
            arr[i+j] = left[i];
            i++;
        }


    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!y
        // Get max number of digits
        int k = maxDigs(arr);

        // Create Queue list
        Queue<Integer>[] lsdArr = new Queue[19];

        // Outer Loop
        for (int i = 0; i < k;i++){
            // Add values to bins
            for (int j = 0; j < arr.length;j++){
                // Get bin value
                int lsdIndex = lsdIndex(arr[j], i);

                // Add value to index
                if (lsdArr[lsdIndex+9] == null){
                    lsdArr[lsdIndex+9] = new LinkedList<Integer>();
                }
                lsdArr[lsdIndex+9].add(arr[j]);
            }
            // Mod original Array
            int arrPos = 0;
            for (Queue<Integer> currQueue : lsdArr){
                while (currQueue != null && !currQueue.isEmpty()){
                    arr[arrPos] = currQueue.remove();
                    arrPos++;
                }
            }
        }
    }
    private static int maxDigs(int[] arr){
        int currVal;
        int currDigs=0;
        int maxDigs = 1;
        for (int i = 0; i < arr.length;i++){
            currDigs = 0;
            currVal = arr[i];
            if (currVal == 0)
                currDigs++;
            while (currVal != 0){
                currVal = currVal / 10;
                currDigs++;
            }
            if (currDigs > maxDigs)
                maxDigs = currDigs;
        }
        return maxDigs;
    }

    private static int lsdIndex(int num, int index){
        for (int i = 0; i < index; i++){
            num /= 10;
        }
        return num % 10;
    }

    public static <T> void printArr(T[] arr){
        String myString = "[";
        for (int i = 0; i < arr.length;i++){
            if (i != 0 && i != arr.length)
                myString += ", ";
            myString += arr[i];
        }
        myString+="]";
        System.out.println(myString);
    }
    public static void printInt(int[] arr){
        String myString = "[";
        for (int i = 0; i < arr.length;i++){
            if (i != 0 && i != arr.length)
                myString += ", ";
            myString += arr[i];
        }
        myString+="]";
        System.out.println(myString);
    }

    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        Integer[] myList = {};
        int[] lsdList   = {1,99,-10, 14, -822, -1, 0 , 0, 100,999,1000,99999999};
        mergeSort(myList,comparator);
        //lsdRadixSort(lsdList);
        printArr(myList);
        //printInt(lsdList);

    }

}