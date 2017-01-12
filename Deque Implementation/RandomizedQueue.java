import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.*;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item>
{

  //Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.
  // Create a generic data type RandomizedQueue

  //Performance requirements. Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time.
  // That is, any sequence of m randomized queue operations (starting from an empty queue) should take at most cm steps in the worst case, for some constant c.
  // A randomized queue containing n items must use at most 48n + 192 bytes of memory.
  // Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time; and construction in linear time;
  // you may (and will need to) use a linear amount of extra memory per iterator.


  //ARRAY IMPLEMENTATION
  private Item items[];
  private int capacity;
  private int size;

   public RandomizedQueue()                 // construct an empty randomized queue
   {
     items = (Item[]) new Object[1];
     capacity = 1;
     size = 0;
   }

   public boolean isEmpty()                 // is the queue empty?
   {
     return (size == 0);
   }

   public int size()                        // return the number of items on the queue
   {
     return size;
   }

   public void enqueue(Item item)           // add the item
   {
     //Throw a java.lang.NullPointerException if the client attempts to add a null item
     if (item == null)
     throw new java.lang.NullPointerException();

     // account for new item, check size and resize if too small (is greater than capacity)
     ++size;

     if (size>capacity)
      {
        Item tmp[] = (Item[]) new Object[capacity*2];
        capacity = capacity*2;
        for (int i = 0; i < size-2; i++)
        tmp[i] = items[i];
        items = tmp;
      }

    items[size-1] = item;

   }

   public Item dequeue()                    // remove and return a random item
   {
     //regroup elements after random dequeue

     if (size == 0)
      throw new java.util.NoSuchElementException();

     int pick = StdRandom.uniform(0,(size));
     Item item = items[pick];

    if (pick != size-1)
        items[pick]=items[size-1];

     --size;

     if (size <= (capacity/4) && size != 0)
        {
          Item tmp[] = (Item[]) new Object[capacity/2];
          capacity = capacity/2;
          for (int i = 0; i < size-1; i++)
          tmp[i] = items[i];
          items = tmp;
        }

     return item;
   }

   public Item sample()                     // return (but do not remove) a random item
   {

     if (size == 0)
      throw new java.util.NoSuchElementException();

     return items[StdRandom.uniform(0,(size))];

   }

   public Iterator<Item> iterator()        // return an independent iterator over items in random order
   {
     return (new ListIterator());
   }

   private class ListIterator implements Iterator<Item>
   {
       int count =0;

       public ListIterator()
       {
         if (size != 0)
          StdRandom.shuffle(items, 0, size-1);
       }

       public boolean hasNext()
       {
         return (count < size);
       }

       public void remove()
       {
         throw new java.lang.UnsupportedOperationException();
       }

       public Item next ()
       {
         if (!hasNext())
          throw new java.util.NoSuchElementException();

         ++count;
         return (items[count-1]);
       }
     }

   public static void main(String[] args)   // unit testing
   {

       }
}
