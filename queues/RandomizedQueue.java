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
import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class RandomizedQueue<Item> implements Iterable<Item> {
  private static final int QUEUEMAX = 10;
  private Item[] resizingArray;
  private int nbrOfItems;
  private int max;
  
  // consturct an empty reandomized queue
  public RandomizedQueue() {
    this.nbrOfItems = 0;
    this.max = QUEUEMAX;
    this.resizingArray = (Item[])new Object[this.max];
  }
  
  private class RQIterator implements Iterator<Item> {
    private Item[] it;
    private int nbrItemsLeft;
    
    public RQIterator() {
      it = (Item[])new Object[nbrOfItems];
      int j = 0;
      for (int i = 0; i < max; i++) {
        if (j == nbrOfItems) { break; }
        if (resizingArray[i] != null) {
          this.it[j++] = resizingArray[i];
        }
      }
    }
    public boolean hasNext() { return this.nbrItemsLeft != 0; }
    public void remove() { throw new UnsupportedOperationException(); }
    public Item next() {
      if (!this.hasNext()) { throw new NoSuchElementException(); }
      
      int rand = 0;
      do {
        rand = StdRandom.uniform(this.it.length);
      } while (it[rand] == null);
      
      Item item = it[rand];
      it[rand] = null;
      this.nbrItemsLeft--;
      
      return item;
    }
  }  
  
  // is the queue empty
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  // return the number of items on the queue
  public int size() {
    return this.nbrOfItems;
  }
  
  // add the item
  public void enqueue(Item item) {
    if (item == null) { throw new IllegalArgumentException(); }
    
    if (this.nbrOfItems == this.max) {
      this.max = this.max * 2;
      this.resizingArray = this.resizeArray(this.max);
    }
    this.resizingArray[nbrOfItems++] = item;
  }
  
  // remove and return a random item
  public Item dequeue() {
    if (this.isEmpty()) { throw new NoSuchElementException(); }
    
    // randomly
    int rand = 0;
    do {
      rand = StdRandom.uniform(this.nbrOfItems);
    } while (this.resizingArray[rand] == null);
    
    Item toRemove = this.resizingArray[rand];
    this.resizingArray[rand] = null;
    this.nbrOfItems--;
    
    if (this.fracOccupied() < 0.25) {
      this.max = this.max / 2;
      this.resizingArray = this.resizeArray(this.max);
    }
    return toRemove;
  }
  
  // return (but do not remove) a random item
  public Item sample() {
    if (this.isEmpty()) { throw new NoSuchElementException(); }
    
    int rand = 0;
    do {
      rand = StdRandom.uniform(this.nbrOfItems);
    } while (this.resizingArray[rand] == null);
    
    return this.resizingArray[rand];
  }
  
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RQIterator();
  }

  private Item[] resizeArray(int newSz) {
    Item[] newArray = (Item[]) new Object[newSz];
    int oldSz = this.resizingArray.length;
    
    int j = 0;
    for (int i = 0; i < oldSz; i++) {
      if (j == newSz) { break; }
      if (this.resizingArray[i] != null) {
        newArray[j++] = this.resizingArray[i];
      }
    }
    return newArray;
  }
  
  private float fracOccupied() {
    float den = (float)this.max;
    float num = (float)this.nbrOfItems;
    return num/den;
  }
  
  // Unit testing
  public static void main(String[] args) {
    StdOut.println("***********************************************");
    RandomizedQueue<Integer> randQ = new RandomizedQueue<Integer>();
    randQ.enqueue(11);
    randQ.enqueue(12);
    randQ.enqueue(13);
    randQ.enqueue(14);
    randQ.enqueue(15); 
    StdOut.println("nbrOfItems = " + randQ.size());
    
    
  }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 