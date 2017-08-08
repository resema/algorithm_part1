/******************************************************************************
 *  Compilation:  javac Deques.java
 *  Execution:    N/A
 *  Dependencies: None
 *
 *  A double-ended queue or deque is a generalization of a stack and a queue
 *    that supports adding and removing item from either the front or the back
 *    of the data structure.
 *
 *  @author resema
 *
 ******************************************************************************/
public class Deque<Item> implements Iterable<Item> {

  // nodes for linked list implementation
  private class Node<Item> {
    private Item value;
    private Node<Item> prev;
    private Node<Item> next;
    
    public Node(Item value, Node<Item> prev = null, Node<Item> last = null) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
    
    // public Node(Item value) {
      // this.value = value;
      // this.prev = null;
      // this.next = null;
    // }
    
    public Item getValue() {
      return this.value;
    }
  }
  
  // linked list variables
  private Node<Item> head;
  private Node<Item> tail;
  private int sz;
  
  // construct an empty deque
  public Deque() {
    this.head = null;
    this.tail = null;
    this.sz = 0;
  }
   
  // is the deque empty?
  public boolean isEmpty() {
    return (this.size == 0);
  }
  
  // return the numbr of items on the deque
  public int size() {
    return sz;
  }
   
  // add the item to the front
  public void addFirst(Item item) {
    // by attempting to add null item throw java.lang.IllegalArgumentException
  }
   
  // add the item to the end
  public void addLast(Item item) {
    // by attempting to add null item throw java.lang.IllegalArgumentException
  }
   
  // remove and return the item from the front
  // public Item removeFirst() {
    // return ...;
  // }
   
  // remvoe and return the item from the end
  // public Item removeLast() {
    // return ...;
  // }
   
  // return an iterator over items in order from front to end
  // public Iterator<Item> iterator() {
    // // calling remove() throws java.ang.UnsupportedOperationException
    // // calling next() at the end of the items throws java.util.NoSuchElementException
    // return ...;
  // }
   
  // unit testing
  public static void main(String[] args) {
  }
  
 }
 
 