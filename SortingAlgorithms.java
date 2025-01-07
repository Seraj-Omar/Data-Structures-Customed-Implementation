public class SortingAlgorithms<T extends Comparable<T>> {

    // Bubble Sort
    public void bubbleSort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    T temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort
    public void selectionSort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo(a[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            T temp = a[minIdx];
            a[minIdx] = a[i];
            a[i] = temp;
        }
    }

    // Insertion Sort
    public void insertionSort(T[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].compareTo(key) > 0) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
        }
    }

    // Merge Sort
    public void mergeSort(T[] a) {
        if (a.length < 2) {
            return;
        }
        int mid = a.length / 2;
        T[] left = (T[]) new Comparable[mid];
        T[] right = (T[]) new Comparable[a.length - mid];

        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, a.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(a, left, right);
    }

    private void merge(T[] a, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
        }
        while (i < left.length) {
            a[k++] = left[i++];
        }
        while (j < right.length) {
            a[k++] = right[j++];
        }
    }

    // Quick Sort
    public void quickSort(T[] a, int l, int r) {
        if (l < r) {
            int pi = partition(a, l, r);
            quickSort(a, l, pi - 1);
            quickSort(a, pi + 1, r);
        }
    }

    private int partition(T[] a, int l, int r) {
        T pivot = a[r];
        int i = (l - 1);
        for (int j = l; j < r; j++) {
            if (a[j].compareTo(pivot) < 0) {
                i++;
                T temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        T temp = a[i + 1];
        a[i + 1] = a[r];
        a[r] = temp;
        return i + 1;
    }

    // Heap Sort
    public void heapSort(T[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            T temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapify(a, i, 0);
        }
    }

    private void heapify(T[] a, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && a[left].compareTo(a[largest]) > 0) {
            largest = left;
        }
        if (right < n && a[right].compareTo(a[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            T swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;
            heapify(a, n, largest);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        SortingAlgorithms<Integer> sorter = new SortingAlgorithms<>();
        Integer[] a = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original array:");
        for (int n : a) {
            System.out.print(n + " ");
        }
        System.out.println();

        sorter.bubbleSort(a);
        System.out.println("Sorted array using Bubble Sort:");
        for (int n : a) {
            System.out.print(n + " ");
        }
        System.out.println();

        // Add calls to other sorting methods here for testing
    }
}
