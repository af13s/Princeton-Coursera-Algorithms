import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import percolation;

public class PercolationStats
{
  int results[];
  int num =0;

   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
     num = trials;
     results = new double[trials];

     for (int i =0; i < trials; i++)
     {
       results[i] = trial(n);
     }
   }

   private double trial(int n)
   {
     percolation trial (n);

     for (int i =0; i < n; i++)
     {
       trial.open(uniform(1,n), uniform(1,n));
       if (trial.percolates())
        return (i/(n*n));
     }

   }

   public double mean()                          // sample mean of percolation threshold
   {
     return mean(results);
   }

   public double stddev()                        // sample standard deviation of percolation threshold
   {
     return stddev(results);
   }

   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
     return (mean() - ((1.96 * stddev()) / Math.sqrt(num));
   }

   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
     return (mean() + ((1.96 * stddev()) / Math.sqrt(num));
   }

   public static void main(String[] args)        // test client (described below)
   {
     //include a main() method that takes two command-line arguments n and T, performs T independent computational experiments (discussed above) on an n-by-n grid
     //and prints the sample mean, sample standard deviation, and the 95% confidence interval for the percolation threshold.

    Scanner reader = new Scanner(System.in); // Reading from System.in

    System.out.println("java PercolationStats ");
    int n = reader.nextInt(); // Scans the next token of the input as an int.
    int trials = reader.nextInt(); // Scans the next token of the input as an int.

    PercolationStats result (n, trials);

    System.out.println("Mean                    = " , result.mean());
    System.out.println("Stddev                  = " , result.stddev());
    System.out.println("95% confidence interval = " , result.confidenceLo(), " " , result.confidenceHi());

   }
}
