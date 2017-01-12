import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{

  private boolean id[];
  private int  vsite1, vsite2;
  private int num;
  private int sites=0;
  private WeightedQuickUnionUF wquf;
  private WeightedQuickUnionUF wquf2;

  // object current should then connect neighbor sites
  // objet should calculate if neighbor sites are filled, fill current site.

  public Percolation(int n) // create n-by-n grid, with all sites blocked
  {
    if (n <= 0)
      throw new java.lang.IllegalArgumentException();

    num = n;
    id = new boolean [n*n]; // store what site are open
    wquf = new WeightedQuickUnionUF ((n*n)+2); // see what sites are connected
    wquf2 = new WeightedQuickUnionUF ((n*n)+1); // see what sites are connected
    vsite1 = n*n;
    vsite2 = vsite1+1;

    //StdOut.println("Initializing Array");
    for (int i = 0; i < (n*n); i++)
        id[i] = false;
  }

  public void open(int row, int col) // open site (row, col) if it is not open already
  {
    if (row <= 0 || col <= 0 || row > num || col > num)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

    int position = (num * (row-1)) + (col-1);

    if (id[position])
      return;

    id[position] = true;
    ++sites;

    //connect to open neighbors

    //row above
    if ((row > 1))
    {
      if  (isOpen(row-1,col))
        {wquf.union(position-num,position);
        wquf2.union(position-num,position);}
    }
    //row below
    if (row < num)
      {
        if  (isOpen(row+1,col))
        {wquf.union(position+num,position);
        wquf2.union(position+num,position);}
      }
    //col to left
    if ((col > 1))
    {
      if  (isOpen(row,col-1))
        {wquf.union(position-1,position);
        wquf2.union(position-1,position);}
    }
    //col to right
    if (col < num)
      {
        if  (isOpen(row,col+1))
          {wquf.union(position+1,position);
          wquf2.union(position+1,position);}
      }

      // Connect beginning virtual site if any site in the first row is open
      if (row == 1)
          {
            wquf.union(position, vsite1);
            wquf2.union(position, vsite1);
          }

    //Connect ending virtual site if any of the site in the last row are open
    if (row == num)
          {wquf.union(vsite2,position);}
  }

  public boolean isOpen(int row, int col) // is site (row, col) open?
  {
    if (row <= 0 || col <= 0 || row > num || col > num)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

      int position = (num * (row-1)) + (col-1);
      //StdOut.println("Position: " + position);
      //StdOut.println("Equals: " + id[position]);

    return (id[position]);
  }

  public boolean isFull(int row, int col) // is site (row, col) full?
  {
    if (row <= 0 || col <= 0 || row > num || col > num)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

    int position = (num * (row-1)) + (col-1);

    return (id[position] && wquf2.connected(vsite1,position));
  }

  public int numberOfOpenSites()
  {
    return sites;
  }

  public boolean percolates() // does the system percolate?
  {
    return (wquf.connected(vsite1,vsite2));
  }

  public static void main(String[] args) // test client (optional)
  {

  }

}
