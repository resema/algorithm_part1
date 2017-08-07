/**
 *  Percolation data type
 */

import edu.princeton.cs.algs4.*;

public class Percolation {
    
    private int size = 0;
    private int top = 0;
    private int bottom = 0;
    private boolean[][] opened;
    private WeightedQuickUnionUF qf;
    
    private int getQFIndex(int row, int col) {
        return size * (row-1) + col;
    }
    
    // create n-by-n grid with all sites blocked
    public Percolation (int n) {
        size = n;
        bottom = size * size + 1; // one after the last site
        opened = new boolean[size][size];
        qf = new WeightedQuickUnionUF(size*size + 2);  // imaginary top and bottom
    }
    // open site (row, col) if it is not open already
    public void open (int row, int col) {
        opened[row-1][col-1] = true;
        
        if (row == 1) {
            qf.union(getQFIndex(row, col), top);
        }
        if (row == size) {
            qf.union(getQFIndex(row, col), bottom);
        }
        
        if (col > 1 && isOpen(row, col-1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col-1));
        }
        if (col < size && isOpen(row, col+1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col+1));
        }
        
        if (row > 1 && isOpen(row-1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row-1, col));
        }
        if (row < size && isOpen(row+1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row+1, col));
        }
    }
    // is site (row, col) open?
    public boolean isOpen (int row, int col) {
        return opened[row-1][col-1];
    }
    // is site (row, col) full?
    public boolean isFull (int row, int col) {
        if (row > 0 && row <= size && col >0 && col <= size) {
            return qf.connected(top, getQFIndex(row, col));
        } 
        else {
            throw new IndexOutOfBoundsException();
        }
    }
    // number of open sites
    public int numberOfOpenSites() {
        return 0;
    }
    // does the system percolate?
    public boolean percolates() {
        return qf.connected(top, bottom);
    }
    
    public static void main (String[] args) {
        
    }
}