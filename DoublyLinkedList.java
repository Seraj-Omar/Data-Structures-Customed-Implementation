class MyDoublyLinkedList<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head; 
    private Node<T> tail;
    private int size;   

    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null)
            head = tail = newNode;
        else 
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null)
            head = tail = newNode;
        else 
        {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new IllegalStateException("List is empty");
        
        T data = head.data;
        head = head.next;
        
        if (head == null)
            tail = null;
        else
            head.prev = null;
        
        size--;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void traverseForward() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void traverseBackward() {
        Node<T> curr = tail;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.prev;
        }
        System.out.println();
    }
}
