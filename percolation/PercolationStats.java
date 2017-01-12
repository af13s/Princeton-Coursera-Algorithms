import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats
{
  private double results[];
  private int repeats;

   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
     if (n <=0)
       throw new java.lang.IllegalArgumentException();

       if (trials <=0)
         throw new java.lang.IllegalArgumentException();

     repeats = trials;
     results = new double[trials];

     for (int i =0; i < trials; i++)
     {
       Percolation trial = new Percolation (n);

        while(!trial.percolates())
            trial.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));

        results[i] = ((double) trial.numberOfOpenSites() / (double) (n*n));
      }


    }

   public double mean()                          // sample mean of percolation threshold
   {
     return StdStats.mean(results);
   }

   public double stddev()                        // sample standard deviation of percolation threshold
   {
     return StdStats.stddev(results);
   }

   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
     return (mean() - ((1.96 * stddev()) / Math.sqrt(repeats)));
   }

   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
     return (mean() + ((1.96 * stddev()) / Math.sqrt(repeats)));
   }

   public static void main(String[] args)        // test client (described below)
   {
     //include a main() method that takes two command-line arguments n and T, performs T independent computational experiments (discussed above) on an n-by-n grid
     //and prints the sample mean, sample standard deviation, and the 95% confidence interval for the percolation threshold.

    StdOut.print("java PercolationStats ");
    int n = StdIn.readInt();
    int trials = StdIn.readInt(); // Scans the next token of the input as an int.

    PercolationStats result = new PercolationStats (n, trials);
    StdOut.println("Mean                    = " + result.mean());
    StdOut.println("Stddev                  = " + result.stddev());
    StdOut.println("95% confidence interval = " + result.confidenceLo()+ " " +result.confidenceHi());

   }
}
