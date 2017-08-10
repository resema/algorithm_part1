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
 
import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class Deque<Item> /*implements Iterable<Item>*/   {

  // nodes for linked list implementation
  @SuppressWarnings("hiding")
  private class Node<Item> {
    private Item value;
    private Node<Item> prev;
    private Node<Item> next;
    
    public Node(Item value, Node<Item> prev, Node<Item> next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
    
    public Node(Item value) {
      this.value = value;
      this.prev = null;
      this.next = null;
    }
    
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
    return (this.sz == 0);
  }
  
  // return the numbr of items on the deque
  public int size() {
    return sz;
  }
   
  // add the item to the front
  public void addFirst(Item item) {
    // by attempting to add null item throw java.lang.IllegalArgumentException
    if (item == null) throw new IllegalArgumentException();
    
    if (this.isEmpty()) {
      this.head = new Node(item);
    } else {
      Node oldHead = this.head;
      this.tail = oldHead;
      this.head = new Node(item, null, this.tail);
      this.tail.prev = this.head;
    }
    sz++;
  }
   
  // add the item to the end
  public void addLast(Item item) {
    // by attempting to add null item throw java.lang.IllegalArgumentException
    if (item == null) throw new IllegalArgumentException();
    
    if (this.isEmpty()) {
      this.tail = new Node(item);
    } else {    
      Node oldTail = this.tail;
      this.tail = new Node(item, oldTail, null);
      oldTail.next = this.tail;
    }
    sz++;
  }
   
  // remove and return the item from the front
  public Item removeFirst() {
    Item retItem = null;
    if (!this.isEmpty()) {
      retItem = this.head.getValue();
      this.head = this.head.next;
    }
    return retItem;
  }
   
  // remvoe and return the item from the end
  public Item removeLast() {
    Item retItem = null;
    if (!this.isEmpty()) {
      retItem = this.tail.getValue();
      this.tail = this.tail.prev;
    }
    return retItem;
  }
   
  // return an iterator over items in order from front to end
  // public Iterator<Item> iterator() {
    // // calling remove() throws java.ang.UnsupportedOperationException
    // // calling next() at the end of the items throws java.util.NoSuchElementException
    // return ...;
  // }
   
  // unit testing
  public static void main (String[] args) {
    Deque<Integer> d = new Deque<Integer>();
    d.addFirst(1);
    d.addFirst(2);
    d.addLast(3);
    StdOut.println("Size = " + d.size());
    
    // should be "2"
    StdOut.println("First element = " + d.removeFirst() + ", should be '2'");
    StdOut.println("Last element = " + d.removeLast() + ", should be '3'");
    StdOut.println("First element = " + d.removeFirst() + ", should be '1'");
  }
  
 }
 
 