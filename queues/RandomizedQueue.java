/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *  Dependencies: algs4.jar
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
    this.max = RandomizedQueue.QUEUEMAX;
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
      this.nbrItemsLeft = nbrOfItems;
    }
    public boolean hasNext() { return this.nbrItemsLeft != 0; }
    public void remove() { throw new UnsupportedOperationException(); }
    public Item next() {
      if (!this.hasNext()) { throw new NoSuchElementException(); }
      else {
        int rand = 0;
        int cnt = 0;
        do {
          rand = StdRandom.uniform(0, it.length);
        } while (it[rand] == null);
        
        Item item = it[rand];
        it[rand] = null;
        this.nbrItemsLeft--;
        return item;
      }
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
    } else {
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
    this.max = newSz;
    
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
    randQ.enqueue(0);
    randQ.enqueue(1);
    randQ.enqueue(2);
    randQ.enqueue(3);
    randQ.enqueue(4);
    randQ.enqueue(5); 
    randQ.enqueue(6);
    randQ.enqueue(7);
    randQ.enqueue(8);
    randQ.enqueue(9);
    randQ.enqueue(10);
    StdOut.println("nbrOfItems = " + randQ.size());
    StdOut.println(randQ.dequeue());
    StdOut.println(randQ.dequeue());
    StdOut.println(randQ.dequeue());
    StdOut.println(randQ.dequeue());
   
    StdOut.println("***********************************************");
    randQ.enqueue(21);
    randQ.enqueue(22);
    randQ.enqueue(23);
    randQ.enqueue(24);
    StdOut.println("Size RandQ it1 = " + randQ.size());
    
    Iterator it1 = randQ.iterator();
    Iterator it2 = randQ.iterator();
    
    while (it1.hasNext()) {
      StdOut.println("item at it1 = " + it1.next());
    }
    
    StdOut.println("***********************************************");
    StdOut.println("Size RandQ it2 = " + randQ.size());
    while (it2.hasNext()) {
      StdOut.println("item at it2 = " + it2.next());
    }
  }
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 