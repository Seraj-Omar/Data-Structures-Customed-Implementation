import java.util.NoSuchElementException;

class MyQueue<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = front;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        System.out.println("Enqueue elements: 10, 20, 30");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Queue: " + queue);

        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Queue after dequeue: " + queue);

        System.out.println("Peek: " + queue.peek());
        System.out.println("Queue size: " + queue.size());

        System.out.println("Dequeue remaining elements");
        queue.dequeue();
        queue.dequeue();

        System.out.println("Is queue empty? " + queue.isEmpty());
    }
}
