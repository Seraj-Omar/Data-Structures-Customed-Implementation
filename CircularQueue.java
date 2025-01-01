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

    public static void main(String[] args) {
        MyCircularQueue<Integer> queue = new MyCircularQueue<>();

        System.out.println("Enqueueing elements:");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.print();

        System.out.println("\nDequeuing an element: " + queue.dequeue());
        queue.print();

        System.out.println("\nPeek at the front: " + queue.peek());

        System.out.println("\nEnqueueing more elements:");
        queue.enqueue(50);
        queue.enqueue(60);
        queue.print();

        System.out.println("\nIs the queue full? " + queue.isFull());
        System.out.println("Is the queue empty? " + queue.isEmpty());

        System.out.println("\nSize of the queue: " + queue.size());

        System.out.println("\nDequeuing all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }

        System.out.println("\nIs the queue empty now? " + queue.isEmpty());
    }
}
