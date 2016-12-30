import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{

  boolean id[];
  int  vsite1, vsite2;
  int num=0;
  WeightedQuickUnionUF wquf;

  // object current should then connect neighbor sites
  // objet should calculate if neighbor sites are filled, fill current site.

  public Percolation(int n) // create n-by-n grid, with all sites blocked
  {
    num = n;

    id = new boolean [n*n]; // store what site are open
    wquf = new wquf ((n*n)+2); // see what sites are connected

    vsite1 = (n*n);
    vsite2 = (n*n)+1;

    for (int i =0; i < (n*n); i++)
      id[i] = false;
  }

  public void open(int row, int col) // open site (row, col) if it is not open already
  {
    if (row || col <=0)
      exception();

    int position = (num * (row-1)) + (col-1);

    id[position] = true;

    //connect to open neighbors

    //row above
    if  (isOpen(row-1,col) && (row > 1))
    wquf.union(postion-n,position);
    //row below
    if  (isOpen(row+1,col) && (row < num))
    wquf.union(postion+n,position);
    //col to left
    if  (isOpen(row,col-1) && (col > 1))
    wquf.union(postion-1,position);
    //col to right
    if  (isOpen(row,col+1) && (col < n))
    wquf.union(postion+1,position);

    // Connect beginning virtual site if any site in the first row is open
    if (row == 1)
      wquf.union(num * (row-1)) + (col-1), vsite1);

    //Connect ending virtual site if any of the site in the last row are open
    if (row = num)
      wquf.union ((num * (row-1)) + (col-1),vsite2);
  }

  public boolean isOpen(int row, int col) // is site (row, col) open?
  {
    if (row || col <=0)
      exception();

    return (id[(num * (row-1)) + (col-1)]);
  }

  public boolean isFull(int row, int col) // is site (row, col) full?
  {
    if (row || col <=0)
      exception();

    return (id[(num * (row-1))] + (col-1)] && wquf.connected(vsite1,(num * (row-1))] + (col-1));
  }

  public boolean percolates() // does the system percolate?
  {
    return (wquf.connected(vsite1,vsite2));
  }

  public static void main(String[] args) // test client (optional)
  {

  }

  private void exception()
  {
    throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");
  }

}
