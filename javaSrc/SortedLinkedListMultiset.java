import java.io.PrintStream;

// T type must implement Comparable interface
public class SortedLinkedListMultiset<T  extends Comparable<T>> extends Multiset<T> {
    protected Node listHead;
    protected int listLength;

    public SortedLinkedListMultiset() {
        listHead = null;
        listLength = 0;
    } // end of SortedLinkedListMultiset()


    public void add(T item) {
        Node newNode = new Node(item);

        //if the list is empty then the new node is now the list head.
        if (listHead == null) {
            listHead = newNode;
        }
        else if (item.compareTo(listHead.getValue()) < 0) {
            newNode.setNext(listHead);
            listHead = newNode;
        }
        //if its the same then we just add to the counter.
        else if (item.compareTo(listHead.getValue()) == 0) {
            listHead.addOne();
        }
        //otherwise we search for correct position to insert node.
        else {
            Node prevNode = getNodeBeforeGreater(item);
            Node currNode = prevNode.getNext();

            if (currNode != null && currNode.getValue().compareTo(item) == 0) {
                currNode.addOne();
                return;
            }
            else {
                newNode.setNext(currNode);
                prevNode.setNext(newNode);
            }
        }

        //increase the length of the list by 1 once added.
        listLength++;
    } // end of add()


    public int search(T item) {
        if (listHead == null) {
            return 0;
        }

        if (item.compareTo(listHead.getValue()) == 0) {
            return listHead.getCount();
        }
        
        Node node = getNodeBeforeGreater(item);

        if (node.getNext() != null && node.getNext().getValue().compareTo(item) == 0)
            return node.getNext().getCount();

        return 0;
    } // end of add()


    public void removeOne(T item) {
        if (listHead == null) {
            return;
        }

        if (listHead.getValue().compareTo(item) == 0) {
            listHead.minusOne();
            if (listHead.getCount() == 0) {
                listHead = listHead.getNext();
                listLength--;
            }
        }

        Node prevNode = getNodeBeforeGreater(item);
        Node currNode = prevNode.getNext();

        if (currNode != null && currNode.getValue().compareTo(item) == 0) {
            currNode.minusOne();
            if (currNode.getCount() == 0) {
                prevNode.setNext(currNode.getNext());
                listLength--;
            }
        }
    } // end of removeOne()


    public void removeAll(T item) {
        if (listHead == null) {
            return;
        }

        if (listHead.getValue().compareTo(item) == 0) {
                listHead = listHead.getNext();
        }

        Node prevNode = getNodeBeforeGreater(item);
        Node currNode = prevNode.getNext();

        if (currNode != null && currNode.getValue().compareTo(item) == 0) {
                prevNode.setNext(currNode.getNext());
        }

        listLength--;
    } // end of removeAll()


    public void print(PrintStream out) {
        Node currNode = listHead;

        //Cycle through list printing all the nodes and their counter values.
        while (currNode != null) {
            out.printf("%s | %d\n", currNode.getValue(), currNode.getCount());
            currNode = currNode.getNext();
        }
    } // end of print()

    private Node getNodeBeforeGreater(T item) {
        if (listHead == null)
            return null;

        Node node = listHead;

        while (node.getNext() != null && (item.compareTo(node.getNext().getValue()) > 0)) {
            node = node.getNext();
        }
        return node;
    }

    private class Node {
        public T nodeValue;
        public int count;
        public Node nodeNext;


        public Node(T value) {
            nodeValue = value;
            nodeNext = null;
            count = 1;
        }


        public int getCount() {
            return count;
        }

        public T getValue() {
            return nodeValue;
        }

        public Node getNext() {
            return nodeNext;
        }

        public void setNext(Node next) {
            nodeNext = next;
        }

        public void addOne() {
            count++;
        }

        public void minusOne() {
            count--;
        }
    }
} // end of class SortedLinkedListMultiset