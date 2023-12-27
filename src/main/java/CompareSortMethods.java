import java.util.Arrays;

public class CompareSortMethods {
    public static void main(String[] args) {
        Integer[] array = generateArray();
        Integer[] arrayForTest1 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest2 = Arrays.copyOf(array, array.length);
        Integer[] arrayForTest3 = Arrays.copyOf(array, array.length);

        long start = System.currentTimeMillis();
        sortBubble(arrayForTest1);
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        sortSelection(arrayForTest2);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        sortInsertion(arrayForTest3);
        System.out.println(System.currentTimeMillis() - start2);
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
}
