/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    N/A
 *  Dependencies: None
 *
 *  Similar to a stack or queue, except that the item removed is chosen uniformly
 *    at random fro items in the data structure.
 *
 *  @author resema
 *
 ******************************************************************************/

import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
 
public class RandomizedQueue<Item> implements Iterable<Item> {
  private class RQIterator implements Iterable<Item> {
    private Node cur;
    
    public RQIterator() {
      this.cur = this.head;
    }
  }
  
  // consturct an empty reandomized queue
  public RandomizedQueue() {
    
  }
  
  // is the queue empty
  public boolean isEmpty() {
    
  }
  
  // return the number of items on the queue
  public int size() {
    
  }
  
  // add the item
  public void enqueue(Item item) {
    if (item == null) { throw new IllegalArgumentException(); }
  }
  
  // remove and return a random item
  public Item dequeue() {
    if (this.isEmpty()) { throw new NoSuchElementException(); }
  }
  
  // return (but do not remove) a random item
  public Item sample() {
    if (this.isEmpty()) { throw new NoSuchElementException(); }
  }
  
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RQIterator();
  }
  
  // Unit testing
  public static void main(String[] args) {
    
  }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 