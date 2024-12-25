class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public boolean isEmpty()
    {
        return size==0;
    }
    public T get(int index) {
        checkIndex(index);
        return getRecursive(head, index, 0);
    }

    private T getRecursive(Node<T> node, int targetIndex, int currentIndex) {
        if (currentIndex == targetIndex) {
            return node.data;
        }
        return getRecursive(node.next, targetIndex, currentIndex + 1);
    }

    public void remove(int index) {
        checkIndex(index);
        head = removeRecursively(head, index, 0);
        size--;
    }

    private Node<T> removeRecursively(Node<T> node, int i, int curr) {
        if (curr == i) {
            return node.next;
        }
        node.next = removeRecursively(node.next, i, curr + 1);
        return node;
    }

    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return true;
        }
        return removeRecursively(head, value);
    }

    private boolean removeRecursively(Node<T> node, T value) {
        if (node.next == null) {
            return false;
        }
        if (node.next.data.equals(value)) {
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            size--;
            return true;
        }
        return removeRecursively(node.next, value);
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void print() {
        printRecursive(head);
        System.out.println();
    }

    private void printRecursive(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data);
        if (node.next != null) {
            System.out.print(", ");
        }
        printRecursive(node.next);
    }
}
