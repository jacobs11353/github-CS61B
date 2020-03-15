/* DListNode.java */

package pj1;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public Object item;
  protected DListNode prev;
  protected DListNode next;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode(Object i, DListNode p, DListNode n) {
    item = (Pixel)i;
    prev = p;
    next = n;
  }

  /**
   *  toString() returns a String representation of this DListNode.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a DListNode as a String.
   *
   *  @return a String representation of this DListNode.
   */
  public String toString() {
    // Replace the following line with your solution.

    String result = "[  "+ item+ "  "+ "]";
    return result;
  }


}
