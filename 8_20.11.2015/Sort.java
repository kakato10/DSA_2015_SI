public class Sort {
//    }
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void bubbleSort(int[] array) {
        boolean swapped = true;
        while(swapped) {
            swapped = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void bubbleSort2(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            int j = i;
            while(j > 0 && array[j] < array[j - 1]) {
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
        }
    }

    public static int[] mergeSort(int[] array) {
        if (array.length == 1)
            return array;

        int mid = array.length / 2;

        int[] leftHalf = mergeSort(copy(array, 0, mid)),
                rightHalf = mergeSort(copy(array, mid, array.length)),
                result = new int[array.length];

        int leftIndex = 0, rightIndex = 0, index = 0;

        while(leftIndex < leftHalf.length || rightIndex < rightHalf.length) {
            if (leftIndex < leftHalf.length &&
                    rightIndex == rightHalf.length) {
                result[index++] = leftHalf[leftIndex++];
            } else if (leftIndex == leftHalf.length &&
                    rightIndex < rightHalf.length) {
                result[index++] = rightHalf[rightIndex++];
            } else if (leftHalf[leftIndex] < rightHalf[rightIndex]) {
                result[index++] = leftHalf[leftIndex++];
            } else {
                result[index++] = rightHalf[rightIndex++];
            }
        }
        return result;
    }
    
    private static int[] copy(int[] array, int start, int end) {
        int[] copy = new int[end - start];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[start + i];
        }
        return copy;
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = end - 1;
        int storage = start;

        for (int i = start; i < end - 1; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[i];
                array[i] = array[storage];
                array[storage++] = temp;
            }
        }
        int temp = array[storage];
        array[storage] = array[pivot];
        array[pivot] = temp;
        return storage;
    }

    public static void quickSort(int array[], int start, int end) {
        if (end - start <= 1)
            return;
        int pivot = partition(array, start, end);
        quickSort(array, start, pivot);
        quickSort(array, pivot + 1, end);
    }
}
