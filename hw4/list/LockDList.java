/* LockDList.java */

package list;

/**
 *  A LockDList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList{


   /**
	*Your LockDList class should override just enough methods to ensure that
      *(1) LockDListNodes are always used in LockDLists (instead of DListNodes), and
      *(2) locked nodes cannot be removed from a list.
    */
   
  public void lockNode(DListNode node){
		((LockDListNode)node).isLocked = true;
	}


  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    return new LockDListNode(item, prev, next);
  }


  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
    if( ((LockDListNode)node).isLocked == false ){
    	super.remove(node);
    }
  }

    public static void main(String[] args){
    	System.out.println("Testing Constructor");
		LockDList testList = new LockDList();
		System.out.println("Is empty? Should be true: " + testList.isEmpty());
		System.out.println("Should be zero length: " + testList.length());

		System.out.println("\nTesting insertFront");
		testList.insertFront(1);
		System.out.println("Is empty? Should be false: " + testList.isEmpty());
		System.out.println("Should be one length: " + testList.length());
		System.out.println("Should be [ 1 ]: " + testList);
		testList.insertFront(3);
		testList.insertFront(6);
		testList.insertFront(9);
		System.out.println(testList);


		//LockDListNode currNode = (LockDListNode) ((DList) testList).front();
		//currNode = (LockDListNode) testList.next(currNode);
		//currNode = (LockDListNode) testList.next(currNode);
		////testList.lockNode(currNode);
		//testList.remove(currNode);

		//System.out.println(testList);


        LockDList l = new LockDList();
		System.out.println("\n\n###Testing insertFront ###\nEmpty list is" + l);
		l.insertFront(9);
		System.out.println("\nInserting 9 at front. \nList with 9 is " + l);
		l.insertFront(8);
		l.insertFront(7);
		System.out.println("\nInserting 7, 8 at the front. \nList with 7, 8, 9 is " + l);
		l.insertAfter(6, l.head);
		System.out.println("\nInserting 6 after head. nList with 6, 7, 8, 9 is "+l);
		l.remove(l.head.next);
		System.out.println("Removed head.next, should return a list of 7, 8, 9. nList with 7, 8, 9 is " + l);
		LockDList m = new LockDList();
		m.insertFront(9);
		m.insertFront(8);
		m.insertFront(7);
		System.out.println("\nInserting 7, 8, 9 at the front. List with 7, 8, 9 is " + m);
		m.lockNode(m.head.next);
		m.remove(m.head.next);
		System.out.println("Locked the first element of the DList, should not be removed. List with 7, 8, 9 is " + m);
  		
    }


}