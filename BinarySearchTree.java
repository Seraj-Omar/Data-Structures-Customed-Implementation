package bst;
public class MyBST<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> left, right;

        public Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private Node<T> root;
    public MyBST() {
        this.root = null;
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) 
            return new Node<>(value);
      
        if (value.compareTo(node.data) < 0)
            node.left = insertRec(node.left, value);
          
        else if (value.compareTo(node.data) > 0)
            node.right = insertRec(node.right, value);
      
        return node;
    }

    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> node, T value) {
        if (node == null)
            return false;
      
        if (value.compareTo(node.data) == 0)
            return true;
      
        if (value.compareTo(node.data) < 0)
            return searchRec(node.left, value);
        
        return searchRec(node.right, value);
    }

    public void delete(T value) {
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> node, T value) {
        
        if (node == null)
            return null;
        
        if (value.compareTo(node.data) < 0)
            node.left = deleteRec(node.left, value);
        
        else if (value.compareTo(node.data) > 0)
            node.right = deleteRec(node.right, value);
        
        else 
        {
            if (node.left == null)
                return node.right;
            
            if (node.right == null)
                return node.left;

            node.data = findMin(node.right);

            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }

    private T findMin(Node<T> node) {
        T minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    public void inOrderTraversal() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node<T> node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        MyBST<Integer> bst = new MyBST<>();
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(50);
        bst.insert(70);
        bst.insert(40);
        bst.insert(80);
        bst.insert(60);

        System.out.println("In-order Traversal:");
        bst.inOrderTraversal();
        System.out.println();
        
        System.out.println("Pre-order Traversal:");
        bst.preOrderTraversal();
        System.out.println();

        System.out.println("Post-order Traversal:");
        bst.postOrderTraversal();
        System.out.println();

        System.out.println("Search for 40: " + bst.search(40));
        System.out.println("Search for 100: " + bst.search(100));
        System.out.println();

        System.out.println("Delete 20");
        bst.delete(20);
        System.out.println();
        
        System.out.println("In-order Traversal after deletion:");
        bst.inOrderTraversal();
        System.out.println();

        System.out.println("Delete 30");
        bst.delete(30);
        System.out.println();
        
        System.out.println("In-order Traversal after deletion:");
        bst.inOrderTraversal();
        System.out.println();

        System.out.println("Delete 50");
        bst.delete(50);
        System.out.println();
        
        System.out.println("In-order Traversal after deletion:");
        bst.inOrderTraversal();
        System.out.println();
    }
}
