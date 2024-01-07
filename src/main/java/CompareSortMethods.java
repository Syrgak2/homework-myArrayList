import java.util.Arrays;

public class CompareSortMethods {
    public static void main(String[] args) {
        Integer[] array = generateArray();
        Integer[] arrayForTest1 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest2 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest3 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest4 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest5 = Arrays.copyOf(array, array.length);

        long start = System.currentTimeMillis();
        sortBubble(arrayForTest1);
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        sortSelection(arrayForTest2);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        sortInsertion(arrayForTest3);
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        quickSort(arrayForTest4, 0, arrayForTest4.length - 1);
        System.out.println(System.currentTimeMillis() - start3);

        long start4 = System.currentTimeMillis();
        mergeSort(arrayForTest5);
        System.out.println(System.currentTimeMillis() - start4);

    }

    public static Integer[] generateArray() {
        Integer[] array = new Integer[100_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * 100);
        }

        return array;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);

        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);

        return i + 1;
    }

    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
