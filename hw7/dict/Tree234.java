/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.

    if(root == null){
    	root = new Tree234Node(null, key);
    	return;
    }

    Tree234Node node = root;

    //iterate nodes in tree
    while(node != null){
    	//System.out.println("========================");
    	//System.out.println("current node is: "+ node+ ", parent node is: "+ node.parent);
    	if(node.keys == 3){
    		//pop up middle key to its parents node
    		node = middleKeyUpstairs(node);
    		//System.out.println("after split 3-key node, the tree is: "+ this);
    	}    	
    	//check if it is in leaf node
    	//only insert in the leaf node, so there is no need to change child nodes
    	if(node.child1 == null){
    		//System.out.println("inside loop for a leaf node: "+node);
    		//System.out.println("node.keys : "+ node.keys);
    		assert(node.child2 == null && node.child3 == null && node.child4 == null);
    		assert(node.keys == 1 || node.keys == 2);

    		//compare node keys with insert key to find its spot
    		//case1: this node has only 1 key
    		if(node.keys == 1){
    			//case1.1: < key1
    			if(key < node.key1){
    				node.key2 = node.key1;
    				node.key1 = key;
    			}
      			//case1.2: > key1
    			else if(key > node.key1){
    				//System.out.println("inside case 1.2 condition");
    				node.key2 = key;
    			}
    			//case1.3: == key1
    			else
    				return;
    			// update key field
    			node.keys ++;
    			//System.out.println("after insertion, node is: "+node);
    		}
    		//case2: this node has two keys, kyes == 2
    		else{
    			//case2.1: <key1
    			if(key < node.key1){
    				node.key3 = node.key2;
    				node.key2 = node.key1;
    				node.key1 = key;
    			}
    			//case2.2: == key1 || == key2
    			else if(node.key1 == key || node.key2 == key)
    				return;
    			//case2.3 between key1 and key2
    			else if(key < node.key2){
   					node.key3 = node.key2;
   					node.key2 = key;
   				}
   				//case2.4 large than key2
   				else
   					node.key3 = key;
   				// update key field
    			node.keys ++;
    		}
    		return;
    	}

    	else{
	    	//compare node keys with insert key
	    	// case1: < key1
	    	if(key < node.key1)
	    		node = node.child1;
	    	// case2: == key1
	    	else if(key == node.key1)
	    		return;
	    	// case3: < key2 || keys == 1
	    	else if(node.keys == 1 || key < node.key2)
	    		node = node.child2;
	    	// case4: == key2
	    	else if(key == node.key2)
	    		return;
	    	// case5: >key2
	    	else if(node.keys == 2 || key < node.key3)
	    		node = node.child3;
	    	// case6: == key3
	    	else if(key == node.key3)
	    		return;
	    	// case7: >key3
	    	else
	    		node = node.child4;
    	}
    }//end of while loop
  }

  /**
   * middleKeyUpstairs() move the middle key of the node to its parent node
   */
  public Tree234Node middleKeyUpstairs(Tree234Node node){
  	//System.out.println("inside middleKeyUpstairs() method");
  	assert(node.keys == 3);
  	//System.out.println("node.keys = : "+node.keys);
  	if(node.parent == null){
  		// creat new root node
   		this.root = new Tree234Node(null, node.key2);
  		this.root.child1 = new Tree234Node(this.root, node.key1);
  		this.root.child2 = new Tree234Node(this.root, node.key3);

  		//split node into two node
  		this.root.child1.child1 = node.child1;
  		this.root.child1.child2 = node.child2;

  		//if node is not leaf node, rebuild its four child's parent pointer
  		if(node.child1 != null)
  			node.child1.parent = this.root.child1;
  		if(node.child2 != null)
  			node.child2.parent = this.root.child1;

  		this.root.child2.child1 = node.child3;
  		this.root.child2.child2 = node.child4;

   		//if node is not leaf node, rebuild its four child's parent pointer 		  		
  		if(node.child3 != null)
  			node.child3.parent = this.root.child2;
  		if(node.child4 != null)
  			node.child4.parent = this.root.child2;

  		return this.root;
  	}

  	else{
  		assert(node.parent.keys == 2 || node.parent.keys == 1);
  		//case1: current node is child1 of its parent
  		//need to shift child2, child3(if there is) right to child3, child4
  		//create new node for child1, child2(if there is)
  		if(node == node.parent.child1){
  			//special: parent only has two node
  			//System.out.println("node.parent.keys is: "+node.parent.keys);
  			if(node.parent.keys == 2){
  				// copy parent node's key2 to key3 position, free key2 position
  				node.parent.key3 = node.parent.key2;
  				// copy parent node's child3 to child4 position, free child3 position
  				node.parent.child4 = node.parent.child3;
  			}

  			//copy parent node's key1 to key2 position, free key1 position
  			node.parent.key2 = node.parent.key1;
  			//add middle node(key2) to key1 position
  			node.parent.key1 = node.key2;

  			node.parent.child3 = node.parent.child2;

  			//create two new child node
  			node.parent.child2 = new Tree234Node(node.parent, node.key3);
  			node.parent.child1 = new Tree234Node(node.parent, node.key1);

  			node.parent.child1.child1 = node.child1;
  			node.parent.child1.child2 = node.child2;

  			//if node is not leaf node, rebuild its four child's parent pointer
  			if(node.child1 != null)
  				node.child1.parent = node.parent.child1;
  			if(node.child2 != null)
  				node.child2.parent = node.parent.child1;

  			node.parent.child2.child1 = node.child3;
  			node.parent.child2.child2 = node.child4;

  			//if node is not leaf node, rebuild its four child's parent pointer
  			if(node.child3 != null)
  				node.child3.parent = node.parent.child2;
  			if(node.child4 != null)
  				node.child4.parent = node.parent.child2;


  		}
  		//case2: current node is child2 of its parent
  		//no need to adjust child1 of its parent, shift child2(if there is) right
  		//create new node for child2, child3(if there is)
  		else if(node == node.parent.child2){
  			//System.out.println("inside case2");
  			//special case: parent only has two node
  			if(node.parent.keys == 2){
  				node.parent.key3 = node.parent.key2;

  				node.parent.child4 = node.parent.child3;

  			}
  			//add middle node(key2) to key2 position
  			node.parent.key2 = node.key2;

  			node.parent.child2 = new Tree234Node(node.parent, node.key1);
  			node.parent.child3 = new Tree234Node(node.parent, node.key3);

  			node.parent.child2.child1 = node.child1;
  			node.parent.child2.child2 = node.child2;

  			//if node is not leaf node, rebuild its four child's parent pointer
  			if(node.child1 != null)
  				node.child1.parent = node.parent.child2;
  			if(node.child2 != null)
  				node.child2.parent = node.parent.child2;

  			node.parent.child3.child1 = node.child3;
  			node.parent.child3.child2 = node.child4;

  			//if node is not leaf node, rebuild its four child's parent pointer
  			if(node.child3 != null)
   				node.child3.parent = node.parent.child3;
  			if(node.child4 != null)
  				node.child4.parent = node.parent.child3;

  		}
  		//case3: current node is child3 of its parent
  		//no need to adjust child1 ,child2(if there is) of its parent
  		//create new node for child3, child4(if there is)
  		else{
  			assert(node == node.parent.child3);

  			//case3.1 : parent only has one node
  			if(node.parent.keys == 1){
  				node.parent.key2 = node.key2;

  				node.parent.child2 = new Tree234Node(node.parent, node.key1);
  		 		node.parent.child3 = new Tree234Node(node.parent, node.key3);

  				node.parent.child2.child1 = node.child1;
  				node.parent.child2.child2 = node.child2;

	  			//if node is not leaf node, rebuild its four child's parent pointer
	  			if(node.child1 != null)
  					node.child1.parent = node.parent.child2;
	  			if(node.child2 != null)
	  				node.child2.parent = node.parent.child2;

  				node.parent.child3.child1 = node.child3;
  				node.parent.child3.child2 = node.child4;

	  			//if node is not leaf node, rebuild its four child's parent pointer
	  			if(node.child3 != null)
  					node.child3.parent = node.parent.child3;
	  			if(node.child4 != null)
  					node.child4.parent = node.parent.child3;

  			}
  			//case3.2 : parent has two nodes
  			else{
  				assert(node.parent.keys == 2);
  				node.parent.key3 = node.key2;

  				node.parent.child3 = new Tree234Node(node.parent, node.key1);
  		 		node.parent.child4 = new Tree234Node(node.parent, node.key3);

  				node.parent.child3.child1 = node.child1;
  				node.parent.child3.child2 = node.child2;

 	  			//if node is not leaf node, rebuild its four child's parent pointer
	  			if(node.child1 != null)
  					node.child1.parent = node.parent.child3;
	  			if(node.child2 != null)
	  				node.child2.parent = node.parent.child3;

  				node.parent.child4.child1 = node.child3;
  				node.parent.child4.child2 = node.child4;

  				//if node is not leaf node, rebuild its four child's parent pointer
	  			if(node.child3 != null)
  					node.child3.parent = node.parent.child4;
	  			if(node.child4 != null)
	  				node.child4.parent = node.parent.child4;

  			}
  			// finish case 3
  		}
  		node.parent.keys ++;
  		return node.parent;
  	}

  }



  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
      System.out.println("but the current result is: "+treeString);
      System.out.println("result of printSubtree() is :");
      this.printTree();
      System.out.println(" ");
      System.out.println(" ");
      System.out.println(" ");
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
