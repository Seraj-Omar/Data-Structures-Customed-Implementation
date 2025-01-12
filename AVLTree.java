public class AVLTree<T extends Comparable<T>> {

    private class Node {
        T key;
        int height;
        Node left;
        Node right;

        Node(T key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    public void insert(T key) {
        root = insert(root, key);
    }

    private Node insert(Node node, T key) {

        if (node == null)
            return new Node(key);

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key);

        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key);

        else
            return node; // Duplicate keys are not allowed


        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case (LL)
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);


        // Right Right Case (RR)
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);


        // Left Right Case (LR)
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }


        // Right Left Case (RL)
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(T key) {
        root = delete(root, key);
    }

    private Node delete(Node node, T key) {

        if (node == null)
            return node;


        if (key.compareTo(node.key) < 0)
            node.left = delete(node.left, key);

        else if (key.compareTo(node.key) > 0)
            node.right = delete(node.right, key);

        else 
        {
            if ((node.left == null) || (node.right == null)) 
            {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null)
                    node = null;
                else
                    node = temp;
            } 
            else 
            {
                Node temp = getMinValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null)
            return node;


        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case (LL)
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Left Right Case (LR)
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case (RR)
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Right Left Case (RL)
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getMinValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Preorder traversal of AVL tree is :");
        tree.preOrder();
        
        System.out.println("\n\nDeleting 20");
        tree.delete(20);
        System.out.println("Preorder traversal after deletion:");
        tree.preOrder();

        System.out.println("\n\nDeleting 30");
        tree.delete(30);
        System.out.println("Preorder traversal after deletion:");
        tree.preOrder();
    }
}
