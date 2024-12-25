class MyCircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private static final int cap = 10;

    public MyCircularQueue() {
        queue = (T[]) new Object[cap];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T element) {
        ensureCapacity();
        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T element = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (!isFull())
            return;

        T[] temp = (T[]) new Object[queue.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(front + i) % queue.length];
        }

        queue = temp;
        front = 0;
        rear = size - 1;
    }

    public void print() {
        printRecursive(0);
        System.out.println();
    }

    private void printRecursive(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(queue[(front + index) % queue.length]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }
}
