import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import java.lang.*;


public class Deque<Item> implements Iterable<Item>
{
  // Performance requirements.
     // Your deque implementation must support each deque operation in constant worst-case time.
     // A deque containing n items must use at most 48n + 192 bytes of memory.
     // and use space proportional to the number of items currently in the deque.
     // Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.

     // LINKED LIST IMPLEMENTATION

 //private classes used by the iterator class

  private class Node
  {
    Item item;
    Node next;
    Node prev;
  }

  private Node head;
  private Node tail;
  private int size;

   public Deque()                           // construct an empty deque
   {
     head = null;
     tail = null;
     size =0;
   }

   public boolean isEmpty()                 // is the deque empty?
   {
     return (head == null);
   }

   public int size()                        // return the number of items on the deque
   {
     return size;
   }

   public void addFirst(Item item)          // add the item to the front
   {
     if (item == null)
      throw new java.lang.NullPointerException();

      if (size == 0)
      {
        head = new Node();
        head.item = item;
        head.prev = null;
        head.next = null;
        ++size;
      }

      else if (size ==1)
      {
        tail = head;
        head = new Node();
        head.next = tail;
        head.item = item;
        tail.prev = head;
        tail.next = null;
        ++size;
      }

      else
      {
         Node oldHead = head;
         head = new Node();
         oldHead.prev = head;
         head.item = item;
         head.next = oldHead;
         head.prev = null;
         ++size;
      }
   }

   public void addLast(Item item)           // add the item to the end
   {
     if (item == null)
      throw new java.lang.NullPointerException();

      if (size == 0)
        addFirst(item);
      else if (size ==1)
      {
        tail = new Node();
        tail.prev = head;
        head.next = tail;
        tail.item = item;
        ++size;
      }
      else
      {
         Node oldTail = tail;
         tail = new Node();
         tail.prev = oldTail;
         tail.item = item;
         tail.next = null;
         oldTail.next = tail;
         ++size;
      }
   }

   public Item removeFirst()                // remove and return the item from the front
   {
     if (isEmpty())
      throw new java.util.NoSuchElementException();

      Item item = head.item;
     head = head.next;

     if (size == 1)
      {head = null;
        tail = null;}

     --size;

     return item;
   }

   public Item removeLast()                 // remove and return the item from the end
   {
     if (isEmpty())
      throw new java.util.NoSuchElementException();



      if (size == 1)
        return removeFirst();
        else
        {
          Item item = tail.item;
          tail = tail.prev;
          tail.next = null;
          --size;
          return item;
        }


   }

   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
       return new ListIterator();
   }

   private class ListIterator implements Iterator<Item>
   {
     private Node current = head;

     public void remove ()
     {
        throw new java.lang.UnsupportedOperationException();
     }

     public boolean hasNext()
     {
       return (current!=null);
     }

     public Item next()
     {
       if (!hasNext())
        throw new java.util.NoSuchElementException();

       Item item = current.item;
       current = current.next;
       return (item);
     }

   }

   public static void main(String[] args)   // unit testing
   {
     Deque<Integer> deque = new Deque<Integer>();

    deque.addFirst(null);

      StdOut.println("size: " + deque.size());

         for (int n: deque)
          StdOut.println(n);



   }
}
