class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] C;
    int n;
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.C = new BTreeNode[2 * t];
        this.n = 0;
    }

    void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf)
                C[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf)
            C[i].traverse();
    }

    void insertNonFull(int k) {
        int i = n - 1;

        if (leaf) {
            System.out.println("Inserting " + k + " into leaf node");

            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = k;
            n++;

            System.out.print("Leaf after insertion: ");
            printNode();
        } else {
            System.out.println("Inserting " + k + " into internal node");

            while (i >= 0 && keys[i] > k)
                i--;

            if (C[i + 1].n == 2 * t - 1) {
                System.out.println("Child full, splitting required...");
                splitChild(i + 1, C[i + 1]);

                if (keys[i + 1] < k)
                    i++;
            }

            C[i + 1].insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        System.out.println("Splitting node...");

        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.C[j] = y.C[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            C[j + 1] = C[j];

        C[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n++;

        System.out.println("After split:");
        printNode();
    }

    void printNode() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++)
            System.out.print(keys[i] + " ");
        System.out.println("]");
    }
}

class FlipkartBTree {
    BTreeNode root;
    int t;

    FlipkartBTree(int t) {
        this.root = null;
        this.t = t;
    }

    void insert(int k) {
        System.out.println("\n===== INSERTING " + k + " =====");

        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;

            System.out.println("Created root with " + k);
            return;
        }

        if (root.n == 2 * t - 1) {
            System.out.println("Root is full, splitting root...");

            BTreeNode s = new BTreeNode(t, false);
            s.C[0] = root;
            s.splitChild(0, root);

            int i = 0;
            if (s.keys[0] < k)
                i++;

            s.C[i].insertNonFull(k);
            root = s;
        } else {
            root.insertNonFull(k);
        }
    }

    void traverse() {
        System.out.println("\n===== FINAL B-TREE TRAVERSAL =====");
        if (root != null)
            root.traverse();
        System.out.println();
    }

    public static void main(String[] args) {
        FlipkartBTree tree = new FlipkartBTree(3);

        int[] values = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int v : values)
            tree.insert(v);

        tree.traverse();
    }
}