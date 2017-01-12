import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.*;

public class Permutation
{
  //Write a client program Permutation.java that takes a command-line integer k;
  // reads in a sequence of strings from standard input using StdIn.readString();
  // and prints exactly k of them, uniformly at random. Print each item from the sequence at most once.
  // You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input.

   public static void main(String[] args)
   {
     int k = Integer.parseInt(args[0]);

     RandomizedQueue<String> stack = new RandomizedQueue<String>();

    String [] strings = StdIn.readAllStrings();

    for (String s: strings )
    stack.enqueue(s);

      Iterator<String> iter = stack.iterator();
      for (int i = 0; i < k; i++)
      StdOut.println(iter.next());

   }
}
