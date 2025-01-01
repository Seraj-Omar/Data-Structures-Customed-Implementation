class MyArrayList<T> {
    private T[] a;
    private int size;
    private static final int cap = 10;

    public MyArrayList() {
        a = (T[]) new Object[cap];
        size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        a[size++] = element;
    }

    public T get(int index) {
        checkIndex(index);
        return a[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            a[i] = a[i + 1];
        }
        a[--size] = null;
    }

    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (a[i].equals(value)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == a.length) {
            T[] temp = (T[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void print() {
        printRecursive(0);
        System.out.println();
    }

    private void printRecursive(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(a[index]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println("Adding elements:");
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.print();

        System.out.println("\nSize of the list: " + list.size());

        System.out.println("\nGetting element at index 2:");
        System.out.println(list.get(2));

        System.out.println("\nRemoving element at index 1:");
        list.remove(1);
        list.print();

        System.out.println("\nRemoving element with value 30:");
        list.remove(Integer.valueOf(30));
        list.print();

        System.out.println("\nSize of the list after removals: " + list.size());

        System.out.println("\nAdding more elements:");
        list.add(50);
        list.add(60);
        list.print();
    }
}
