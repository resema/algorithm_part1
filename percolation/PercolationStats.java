/**
 *  calculate the sample mean, sample standard deviation and the 95% confidence interval
 */
 
import java.lang.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

 
public class PercolationStats {
  private final double[] thresholds;
  private final int trials;
  
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
      
    this.trials = trials;
    this.thresholds = new double[trials];
    
    for (int i = 0; i < this.trials; i++) {
      Percolation p = new Percolation(n);
      int openBlocks = 0;
      while (!p.percolates()) {
        int x = StdRandom.uniform(1, n+1);
        int y = StdRandom.uniform(1, n+1);
        if (!p.isOpen(x, y)) {
          p.open(x, y);
          openBlocks++;
        }
      }
      thresholds[i] = (double) openBlocks/(n*n);
    }
  }
  
  public double mean() {
    return StdStats.mean(thresholds);
  }
  
  public double stddev() {
    return StdStats.stddev(thresholds);
  }
  
  public double confidenceLo() {
    return (mean() - 1.96 * stddev() / Math.sqrt((double) trials));
  }
  
  public double confidenceHi() {
    return (mean() + 1.96 * stddev() / Math.sqrt((double) trials));
  }
  
  public static void main(String[] args) {
    if (args.length == 2) {
      PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      StdOut.println("mean                    = " + stats.mean());
      StdOut.println("stddev                  = " + stats.stddev());
      StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
    else {
      throw new IllegalArgumentException();
    }
  }
}





































