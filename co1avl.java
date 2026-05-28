class Node {
    int data, height;
    Node left, right;

    Node(int data) {
        this.data = data;
        height = 1;
    }
}

public class co1avl {

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }

    static int balance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data)
            root.left = insert(root.left, key);
        else if (key > root.data)
            root.right = insert(root.right, key);

        root.height = 1 + max(height(root.left), height(root.right));

        int bf = balance(root);

        if (bf > 1 && key < root.left.data) {
            System.out.println("Rotation: LL at " + root.data);
            return rightRotate(root);
        }

        if (bf < -1 && key > root.right.data) {
            System.out.println("Rotation: RR at " + root.data);
            return leftRotate(root);
        }

        if (bf > 1 && key > root.left.data) {
            System.out.println("Rotation: LR at " + root.data);
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (bf < -1 && key < root.right.data) {
            System.out.println("Rotation: RL at " + root.data);
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static void printTree(Node root, String prefix, boolean isLeft) {
        if (root == null)
            return;

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.data);

        printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    public static void main(String[] args) {
        int[] times = {120, 85, 200, 65, 150, 95, 180, 75, 110, 240, 90};

        Node root = null;

        for (int i = 0; i < times.length; i++) {
            System.out.println("\nStep " + (i + 1) + ": Insert " + times[i]);
            root = insert(root, times[i]);
            printTree(root, "", false);
        }

        System.out.println("\nFinal Result:");
        System.out.println("AVL Tree insertion completed successfully.");
        System.out.println("Time Complexity:");
        System.out.println("Insertion: O(log n)");
        System.out.println("Search: O(log n)");
        System.out.println("Rotation: O(1)");
    }
}