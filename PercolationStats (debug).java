import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats
{
  double results[];
  int repeats;
  boolean samenumber[][];


   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
     repeats = trials;
     results = new double[trials];

     for (int i =0; i < trials; i++)
     {
       StdOut.println("Running Trial: " +i);
       results[i] = trial(n);
     }

     for (int i =0; i < trials; i++)
      StdOut.print(" - " + results[i]);

   }

   private double trial(int n)
   {
      Percolation trial = new Percolation (n);
      int rand1=0, rand2=0;
      double runs =0 , count =0;

      samenumber = new boolean[n][n];

      for (int i = 0; i < n; i++)
         for (int j = 0; j < n; j++)
             samenumber[i][j] = false;

     while(!trial.percolates())
     {
       ++runs;
       StdOut.println("Attempt to open new site number: " + runs);
       rand1 = StdRandom.uniform(0,n);
       rand2 = StdRandom.uniform(0,n);

       if(!samenumber[rand1][rand2])
       {
         ++count;
         StdOut.println("Opening Site: " + rand1 + " " + rand2);
         samenumber[rand1][rand2] = true;
         trial.open(rand1+1, rand2+1);
       }
       else
        StdOut.println("Already Open");
        
     }

    StdOut.println("Trial Ending,Sites open : " + count + " " + "attempts: " + runs);
    StdOut.println();
     return (count/(n*n));
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

    StdOut.println("java PercolationStats ");
    StdOut.print ("Enter N: ");
    int n = StdIn.readInt();
    StdOut.print("Enter Trials: ");
    int trials = StdIn.readInt(); // Scans the next token of the input as an int.

    PercolationStats result = new PercolationStats (n, trials);

    StdOut.println("Mean                    = " + result.mean());
    StdOut.println("Stddev                  = " + result.stddev());
    StdOut.println("95% confidence interval = " + result.confidenceLo()+ " " +result.confidenceHi());

   }
}
