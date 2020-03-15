/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 10000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    
    if(q.isEmpty()) 
      return null;

    LinkedQueue result = new LinkedQueue();

    while( !q.isEmpty() ){

      try{
        Object item = q.dequeue();
        LinkedQueue element = new LinkedQueue();
        element.enqueue(item);
        result.enqueue(element);

      }catch(QueueEmptyException e){
        e.printStackTrace();
      }

    }
    return result;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    if(q1.isEmpty() && q2.isEmpty())
      return null;

    LinkedQueue result = new LinkedQueue();

    //System.out.println("after first if");
    //either q1 or q2 is empty
    if(q1.isEmpty() || q2.isEmpty() ){
      result.append( q1.isEmpty() ? q2:q1);
      return result;
    }
    //System.out.println("before while");
    //neither q1 nor q2 is empty
    while( !(q1.isEmpty()) || !(q2.isEmpty()) ){
      //System.out.println("result of mergSortedQueues is: "+result);
      try{
        if(q1.isEmpty()){
          result.enqueue( q2.dequeue() );
        }
        else if(q2.isEmpty()){
          result.enqueue( q1.dequeue() );
        }
        else{
          Comparable elem_q1 = (Comparable) q1.front();
          Comparable elem_q2 = (Comparable) q2.front();

          //q1 <= q2 insert q1
          if( elem_q1.compareTo(elem_q2) <= 0 )
            result.enqueue(q1.dequeue());

          //q1>q2 insert q2
          else 
            result.enqueue(q2.dequeue());

        }

      }catch(QueueEmptyException e){
        e.printStackTrace();
      }

    }

    return result;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    if(qIn.isEmpty())
      return;

    while( !qIn.isEmpty() ){
      try{
        Comparable elem = (Comparable)qIn.dequeue();
        if(elem.compareTo(pivot) < 0 )
          qSmall.enqueue(elem);
        else if(elem.compareTo(pivot) > 0 )
          qLarge.enqueue(elem);
        else
          qEquals.enqueue(elem);

      }catch(QueueEmptyException e){
        e.printStackTrace();
      }

    }

    
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {

  	if (q==null || q.size() == 0) return;
    LinkedQueue linkedQueues = makeQueueOfQueues(q);

    while(linkedQueues.size() > 1){
      try{
        //System.out.println(linkedQueues.size());
        //remove two items from the queue of queues
        LinkedQueue lq_1 = (LinkedQueue)linkedQueues.dequeue();
        LinkedQueue lq_2 = (LinkedQueue)linkedQueues.dequeue();

      //merge them with mergeSortedQueues(), and enqueue the resulting queue on the queue of queues
      linkedQueues.enqueue( mergeSortedQueues(lq_1, lq_2) );

      //System.out.println(linkedQueues);

      }catch(QueueEmptyException e){
        e.printStackTrace();
      }

    }

    //When there is only one queue left on the queue of queues, move its items back
    //to q (preferably using the fast append() method).
    // q.append(linkedQueues);
    try{
    	q.append((LinkedQueue)linkedQueues.front());

    }catch(QueueEmptyException e){
     	e.printStackTrace();
    }
    // Your solution here.
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
  	if (q==null || q.size() == 0 || q.size() == 1) return;
  	//choose a random integer between 1 and q.size() 
  	int pivot_index = (int)(q.size()*Math.random());

  	//System.out.println("now q is: "+q);
  	//Find the corresponding item using the nth() method, and use the item as the pivot in a call to partition().
  	Comparable pivot = (Comparable)q.nth(pivot_index+1);
  	//System.out.println("pivot_index is: "+ pivot_index+", and its value is: "+ pivot);
  	LinkedQueue qSmall = new LinkedQueue();
  	LinkedQueue qEquals = new LinkedQueue();
  	LinkedQueue qLarge = new LinkedQueue();
  	partition(q, pivot, qSmall, qEquals, qLarge);

  	//System.out.println("qSmall is: "+qSmall+",  qEquals is: "+qEquals+",  qLarge is: "+qLarge);
  	//Recursively quickSort() the smaller and larger partitions
  	if(!qSmall.isEmpty()){
	  	quickSort(qSmall);
  	}
  	if(!qLarge.isEmpty()){
  		quickSort(qLarge);
  	}

  	//put all of the items back into q in sorted order by using the append() method.
  	q.append(qSmall);
  	q.append(qEquals);
  	q.append(qLarge);

    // Your solution here.
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
