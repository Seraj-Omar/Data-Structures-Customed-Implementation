class MyStack<T> {
    private T[] stack;
    private int size;
    private static final int cap = 10;

    public MyStack() {
        stack = (T[]) new Object[cap];
        size = 0;
    }

    public void push(T element) {
        ensureCapacity();
        stack[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = stack[--size];
        stack[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == stack.length) {
            T[] temp = (T[]) new Object[stack.length * 2];
            for (int i = 0; i < stack.length; i++) {
                temp[i] = stack[i];
            }
            stack = temp;
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
        System.out.print(stack[index]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        System.out.println("Push elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.print();

        System.out.println("Peek: " + stack.peek());

        System.out.println("Pop: " + stack.pop());
        stack.print();

        System.out.println("Stack size: " + stack.size());

        System.out.println("Is stack empty? " + stack.isEmpty());

        System.out.println("Pop remaining elements");
        stack.pop();
        stack.pop();

        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
