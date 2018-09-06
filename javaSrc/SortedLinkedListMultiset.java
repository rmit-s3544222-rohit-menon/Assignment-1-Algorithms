import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T> {

	protected Node listHead;
	protected int listLength;

	public SortedLinkedListMultiset() {
		// Implement me!
		listHead = null;
		listLength = 0;

	} // end of LinkedListMultiset()

	public void add(T item) {
		// Implement me!
		
		 Node NodeNew = new Node((String) item);
		 
		 // if the list is empty or the list head is smaller than the new node, then the new node is now the list head.
	        
	        if ((listHead == null) || Integer.parseInt(listHead.getValue()) > Integer.parseInt(NodeNew.getValue()))
	        {
	        	NodeNew.setNext(listHead);
	            listHead = NodeNew;
	        }

	      // otherwise we set the current node as the list head, and the previous node as a null.   
	        else 
	        {
	            Node currNode = listHead;
	            Node prevNode = null;
	   
	     // we then cycle through the list until the current node is null and the current node is smaller than the new node
	            while (currNode != null && Integer.parseInt(currNode.getValue()) < Integer.parseInt(NodeNew.getValue())) 
	            {
	            	
	            	// if the node we wish to enter is already present in the list then we add 1 to the counter of that node.
	                if (currNode.getValue().equals(NodeNew.getValue())) 
	                {
	                    currNode.addOne();
	                    return;
	                }
	                
	                //set the previous node to the current and the current to the next (to cycle through the list).
	                prevNode = currNode;
	                currNode = currNode.getNext();
	            }
	            
	            NodeNew.nodeNext = currNode;
	            prevNode.nodeNext = NodeNew;
	            
	        }
	        
	        //increase the length of the list by 1.
	        listLength++;
		
	} // end of add()

	public int search(T item) {
		// Implement me!
		Node currNode = listHead;
		Node NodeSearch = new Node((String) item);

		// cycle through nodes in the list again
		while (currNode != null) {

			// if the value of the search node exists in the list, then return that nodes
			// ctr value (how many times that item is in the list)
			if (currNode.getValue().equals((String) item)) {
				return currNode.getCtr();
			}
			
			// if we reach a node higher than the search node, then node doesn't exist
			// so break out of search
			else if (Integer.parseInt(currNode.getValue()) > Integer.parseInt(NodeSearch.getValue())) {
				break;
			}

			currNode = currNode.getNext();
		}

		return 0;

		// default return, please override when you implement this method

	} // end of add()

	public void removeOne(T item) {
		// Implement me!
		Node currNode = listHead;
		Node prevNode = null;

		// cycle through list again until we reach
		while (currNode != null) {

			// check to see if current node's value equals the value of the item to remove
			if (currNode.getValue().equals((String) item)) {

				// subtract one from that node's ctr value.
				currNode.minusOne();

				// check and see if all of that particular item is over, if so then the node has
				// to be removed from list
				if (currNode.getCtr() == 0) {

					// if that node was at the head of the list then set the head as the next node
					// in the list
					if (currNode == listHead) {
						listHead = currNode.getNext();
					}

					// if the node was in the middle of the list then set the previous nodes next as
					// the current nodes next.
					else {
						prevNode.setNext(currNode.getNext());
					}

					// decrease the length of the list by one.
					listLength--;

				}

				return;
			}

			prevNode = currNode;
			currNode = currNode.getNext();
		}
	} // end of removeOne()

	public void removeAll(T item) {

		Node currNode = listHead;
		Node prevNode = null;

		// cycle through the list
		while (currNode != null) {

			// if the value of the current node is the value of the item to be completely
			// removed
			if (currNode.getValue().equals((String) item)) {

				// if the node is at head of list then set head as the next node in list
				if (currNode == listHead) {

					listHead = currNode.getNext();

				}

				// if the node is in the middle of the list, set the previous node's next as the
				// current node's next.
				else {

					prevNode.setNext(currNode.getNext());

				}

				// reduce list length by one.
				listLength--;

				return;

			}

			prevNode = currNode;
			currNode = currNode.getNext();

		}
	} // end of removeAll()

	public void print(PrintStream out) {
		// Implement me!

		Node currNode = listHead;

		// Cycle through list printing all the nodes and their counter values.
		while (currNode != null) {
			System.out.printf("%s | %d\n", currNode.getValue(), currNode.getCtr());
			currNode = currNode.getNext();
		}

	} // end of print()

	private class Node {

		int ctr;

		protected String nodeValue;

		protected Node nodeNext;

		public Node(String value) {
			nodeValue = value;
			nodeNext = null;
			ctr = 1;
		}

		public int getCtr() {
			return ctr;
		}

		public String getValue() {
			return nodeValue;
		}

		public Node getNext() {
			return nodeNext;
		}

		public void setNext(Node next) {
			nodeNext = next;
		}

		public void addOne() {
			ctr++;
		}

		public void minusOne() {
			ctr--;
		}

	}
} // end of class SortedLinkedListMultiset