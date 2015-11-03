package Recursion;

public class Test {
    private static long[] values = new long[1000];

    private static int binarySearchImplementation(int[] array,
                                                  int element,
                                                  int start,
                                                  int end) {
        int mid = (start + end) / 2;
        if (start == end)
            if (array[start] == element)
                return start;
            else
                return -1;
        if (array[mid] == element)
            return mid;
        else if (element < array[mid]) {
            end = mid - 1;
            return binarySearchImplementation(array, element, start, end);
        } else {
            start = mid + 1;
            return binarySearchImplementation(array, element, start, end);
        }
    }

    public static int factoriel(int n) {
        if (n == 1)
            return 1;
        return n * factoriel(n - 1);
    }

    public static int fibonacci(int n) {
        if (n == 0 || n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int binarySearch(int[] array, int element) {
        return binarySearchImplementation(array, element, 0, array.length);
    }

    public static int kartsubaPow(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 0) {
            int xToZ = kartsubaPow(x, y / 2);
            return xToZ * xToZ;
        } else {
            return kartsubaPow(x, y - 1) * x;
        }
    }

    public static long fibonacciMemoized(int n) {
        if (values[n] == 0) {
            if (n == 0 || n == 1)
                values[n] = 1;
            else
                values[n] = fibonacciMemoized(n - 1) + fibonacciMemoized(n - 2);
        }
        return values[n];
    }

    public static long fibonacciDynamic(int n) {
        long[] values = new long[1000];
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1)
                values[i] = 1;
            else
                values[i] = values[i - 1] + values[i - 2];
        }
        return values[n];
    }
}
