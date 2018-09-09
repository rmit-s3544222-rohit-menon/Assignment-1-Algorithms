import java.io.PrintStream;

// T type must implement Comparable interface
public class BstMultiset<T extends Comparable<T>> extends Multiset<T> {
    private Node bstRoot;
    
    //private functions implemented to compare two parameters(for eg: tree root and new item to be added)

    public BstMultiset() {
        bstRoot = null;
    } // end of BstMultiset()

    public void add(T item) {
    	
        bstRoot = add(bstRoot, item);
    } // end of add()


    public int search(T item) {
        int count = 0;
        count = search(bstRoot, item, count);
        return count;
    } // end of add()


    public void removeOne(T item) {
        bstRoot = removeOne(bstRoot, item);
    } // end of removeOne()


    public void removeAll(T item) {
        bstRoot = removeAll(bstRoot, item);
    } // end of removeAll()


    public void print(PrintStream out) {
        print(bstRoot, out);
    } // end of print()

    private Node add(Node node, T item) {
    	
    	//if node exists
        if (node != null) {
        	
        	//if item to add is less than current node, then add as left child
            if (item.compareTo(node.getValue()) < 0) {
                node.left = add(node.left, item);
            }
            
            //else we add as right child
            else if (item.compareTo(node.getValue()) > 0) {
                node.right = add(node.right, item);
            }
            
            //or if value is same then counter increased
            else {
                node.addOne();
            }

        }
        
        //if nothing present we just add it
        else {
            node = new Node(item);
        }

        return node;
    }

    private int search(Node node, T item, int count) {
        if (node != null) {
            if (item.compareTo(node.getValue()) < 0) {
                return search(node.getLeft(), item, count);
            }
            else if (item.compareTo(node.getValue()) > 0){
                return search(node.getRight(), item, count);
            }
            else {
                count = node.getCount();
                return count;
            }
        }
        return count;
    }

    private Node removeOne(Node node, T item) {
        if (node != null) {

            if (item.compareTo(node.getValue()) < 0) {
                node.left = removeOne(node.getLeft(), item);
            }
            else if (item.compareTo(node.getValue()) > 0){
                node.right = removeOne(node.getRight(), item);
            }
            else {
                node.minusOne();
                if (node.getCount() == 0) {
                    node = removeHelper(node);
                }
            }
        }
        return node;
    }


    private Node removeAll(Node node, T item) {
        if (node != null) {
            if (item.compareTo(node.getValue()) < 0) {
                node.left = removeAll(node.left, item);
            }
            else if (item.compareTo(node.getValue()) > 0) {
                node.right = removeAll(node.right, item);
            }
            else {
                node = removeHelper(node);
            }
        }
        return node;
    }

    private Node removeHelper(Node node) {
        if (node.left == null)
            node = node.right;
        else if (node.right == null)
            node = node.left;
        else {
            Node big = node.left;
            Node last = null;

            while (big.right != null) {
                last = big;
                big = big.right;
            }

            node.value = big.getValue();
            node.count = big.getCount();

            if (last == null) {
                node.left = big.left;
            }
            else {
                last.right = big.left;
            }
        }
        return node;
    }

    private void print(Node root, PrintStream out) {
        if (root != null) {
            print(root.getLeft(), out);
            out.printf("%s | %d\n", root.getValue(), root.getCount());
            print(root.getRight(), out);
        }
    }

    private class Node {
        public T value;
        public int count;

        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
            count = 1;
            left = null;
            right = null;
        }

        public int getCount() {
            return count;
        }

        public T getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void addOne() {
            count++;
        }

        public void minusOne() {
            count--;
        }
    }

} // end of class BstMultiset
