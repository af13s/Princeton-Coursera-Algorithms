public class Percolation
{

  int id[][];
  boolean vsite;
  int num=0;
  WeightedQuickUnionUF(int n);

  // object current should then connect neighbor sites
  // objet should calculate if neighbor sites are filled, fill current site.
  //add error handling warning

  public Percolation(int n) // create n-by-n grid, with all sites blocked
  {
    num = n;
    id = new int [n][n];

    for(int i =0; i < n; i++)
      for (j=0;j<n;j++)
        id[i][j] =-1;
  }

  public void open(int row, int col) // open site (row, col) if it is not open already
  {
    if (row || col <=0)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

    else if (row ==1)
      id[row-1][col-1] = 1;
    else
      id[row-1][col-1] = 0

      if (if (row-1 == num) && isFull(row,col))
        if vsite = true;
  }

  public boolean isOpen(int row, int col) // is site (row, col) open?
  {
    if (row || col <=0)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

    return (id[row-1][col-1] > -1)
  }

  public boolean isFull(int row, int col) // is site (row, col) full?
  {
    if (row || col <=0)
      throw new java.lang.IndexOutOfBoundsException("Row and Colummn must be between greater than 0");

    return (id[row-1][col-1] > 0)
  }

  public boolean percolates() // does the system percolate?
  {
    return vsite;
  }

  public static void main(String[] args) // test client (optional)
  {

  }

}
