/* HashTableChained.java */
package dict;


import list.*;
import java.text.DecimalFormat;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected List[] data;
  protected static final int DEFAULT_SIZE = 100;
  protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
  protected int size;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    if(sizeEstimate < 0) throw new IllegalArgumentException("Illegal size");

    int capacity = (int)(sizeEstimate / DEFAULT_LOAD_FACTOR);
    while(!isPrime(capacity))
      capacity++;

    this.data = new List[capacity];
    this.size = 0;

  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(DEFAULT_SIZE);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    //System.out.println("hashCode() value in compFunction()"+code);
    int a = 7;
    int b = 19;
    int p = this.data.length*5;
    while(!isPrime(p))
      ++p;
    if(code<0)
      return ((a*code + b) % p) % this.data.length + this.data.length;
    else
      return ((a*code + b) % p) % this.data.length;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return this.size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return this.size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    // 
    //System.out.println("hashCode() first time: "+key.hashCode());
    //System.out.println("hashCode() second time: "+key.hashCode());

    int keyHash = compFunction(key.hashCode());

    //System.out.println("key is: "+ key + ", and hashCode is: "+ keyHash);
    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;

    //if the bucket is null, create a new List
    if(data[keyHash] == null){
      data[keyHash] = new SList();
    }

    //add entry to the existed List
    data[keyHash].insertFront(entry);

    size ++;

    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    //System.out.println("hashCode() first time: "+key.hashCode());
    //System.out.println("hashCode() second time: "+key.hashCode());
    int keyHash = compFunction(key.hashCode());

    //System.out.println("key is: "+ key + ", and hashCode is: "+ keyHash);

    if(data[keyHash] == null)
      return null;
    else{
        ListNode head = data[keyHash].front();
        try{

          while(head.isValidNode()){
            Entry element =(Entry)head.item();
            if( (element.key()).equals(key) )
              return (Entry)(head.item());
            
            else
              head = head.next();
          }

        }catch(InvalidNodeException e){
          e.printStackTrace();
        }
      
    }

    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int keyHash = compFunction(key.hashCode());

    if(data[keyHash] != null){

        ListNode head = data[keyHash].front();
        try{
          while(head.isValidNode()){

            Entry element = (Entry)(head.item());
            if( (element.key()).equals(key) ){
              head.remove();
              size --;
              return element;
            }
            head = head.next();
          }

        }catch(InvalidNodeException e){
          e.printStackTrace();
        }

    }

    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for(int i=0; i<data.length; ++i){
      if(data[i] != null)
        data[i] = new SList();
    }

    size = 0;
    
  }

  /**
   * convert HashTableChained to Array of String
   */
  public String toString(){
    String result ="";
    for(int i=0; i<data.length; ++i){
      if(data[i] != null)
        result += data[i].toString()+", \r\n \r\n ";
      else
        result += "[ ],  \r\n \r\n ";
    }

    return result;
  }

  /**
   * show number of entry in each bucket 
   */
  public String toHashString(){
    String result =" { ";
    for(int i=0; i<data.length; ++i){
      if(data[i] != null)
        result += "["+data[i].length()+"], ";
      else
        result += "[0], ";

      if( (i+1)%10 == 0 )
        result +=" } \r\n { ";
    }

    return result;
  }


  /**
   * judge whether a number is prime or not
   */
  protected boolean isPrime(int num){

    if(num<2)
      return false;

    for(int i=2; i*i<=num; ++i){
      if(num%i == 0)
        return false;
    }
    return true; 
  }

  /**
   * calcualte the collision rate
   * n - N + N (1 - 1/N)^n
   */
     public void histograph(){
       System.out.println("Number of Entries : " + data.length);
       System.out.println("Number of Buckets :" + size);
       double collisions=this.size - data.length + data.length*Math.pow((1- (double)(1.0/(double)data.length) ),(double) size);
       DecimalFormat df=new DecimalFormat("#.00");
       System.out.println("The expected collisions are: "+df.format(collisions));
       int count=1;
       int realCollisions=0;
       for(int i=0;i<data.length;i++){
          if(data[i] != null){
           System.out.print("["+data[i].length()+"], ");
           if(data[i].length()>1)
               realCollisions+=data[i].length()-1;
          }else{
            System.out.print("[0], ");
          }

          if(count%10==0){
            System.out.println();
            count=0;
          }
          count++;
       }
       System.out.println();
       System.out.println("The real collisions are: "+realCollisions);
   }


  /**
   * Test code
   */
  public static void main(String[] args){

        HashTableChained table = new HashTableChained(9);

        System.out.println("=====================size, isEmpty=========================");
        System.out.println("table's size is: " + table.size());
        System.out.println("(should be true) table is Empty: " + table.isEmpty());
        
        System.out.println("");
        System.out.println("");
        System.out.println("=====================insert================================");

        table.insert("1", "The first one");
        table.insert("2", "The second one");
        table.insert("3", "The third one");
        table.insert("ECE", "Carienge Mellon University");
        table.insert("CSE","University of California, Berkely");
        table.insert("CS","Stanford University");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());

        System.out.println("HashTableChained is: "+ table);
        
        System.out.println("");
        System.out.println("");
        System.out.println("====================find===========================");

        Entry e1 = table.find("6");
        if(e1 != null)
          System.out.println("The item found is: [ " + e1.toString() + " ]");
        else
          System.out.println("There is no such item in the table to be found.");

        System.out.println("");
        e1 = table.find("CSE");
        if(e1 != null)
          System.out.println("The item found is: [ " + e1.toString() + " ]");
        else
          System.out.println("There is no such item in the table to be found.");

        System.out.println("");
        e1 = table.find("2");
        if(e1 != null)
          System.out.println("The item found is: [ " + e1.toString() + " ]");
        else
          System.out.println("There is no such item in the table to be found.");
        
        System.out.println("");
        System.out.println("");
        System.out.println("====================remove===========================");

        System.out.println("");
        Entry e2 = table.remove("ECE");
        if(e2 != null)
          System.out.println("The item deleted is: [ " + e2.toString() + " ]");
        else
          System.out.println("The is no such item in the table to be deleted.");

        System.out.println("");
        System.out.println("After remove *ECE* HashTableChained is: ");
        System.out.println(table);
        

        e2 = table.remove("FE");
        if(e2 != null)
          System.out.println("The item deleted is: [ " + e2.toString() + " ]");
        else
          System.out.println("The is no such item in the table to be deleted.");

        System.out.println("");
        System.out.println("After remove *FE* HashTableChained is: ");
        System.out.println(table);


        e2 = table.remove("2");
        if(e2 != null)
          System.out.println("The item deleted is: [ " + e2.toString() + " ]");
        else
          System.out.println("The is no such item in the table to be deleted.");

        System.out.println("");
        System.out.println("After remove *2* HashTableChained is: ");
        System.out.println(table);

        System.out.println("");
        System.out.println("=====================makeEmpty=============================");
        table.makeEmpty();

        System.out.println("After makeEmpty(), the HashTableChained is:");
        System.out.println(table);

  }

}
