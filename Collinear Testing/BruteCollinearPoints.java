import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints
{
    int lines=0;
    ArrayList<LineSegment> segs;

   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
     int count = 0;
     int index = 0;
     ArrayList<Double> slopes = new ArrayList<Double>();

     Arrays.sort(points);

     for (int i =0; i < points.length -3; i++) { // starts first loop
      for (int j = i+1; j < points.length; j++) //loops through remaining elements
      {
        slopes.add(points[i].slopeTo(points[j])); // collects all slopes from first point i

        for (double d: slopes.subList(1, slopes.size())) // loops to checks to find match in all slopes
          if (slopes.get(0) == d)
            count++;

          if (count == 3) // if 3 matching slopes are found we have a collinear points
            segs.add(new LineSegment(points[i],points[j])); /* add that lines segement to arraylist starting at i ending at the last point found*/
      }
      count =0;
      index = 0;
    }

   }

   public int numberOfSegments()        // the number of line segments
   {
     return lines;
   }

   public LineSegment[] segments()      // the line segments
   {
     LineSegment [] segms = new LineSegment [segs.size()];
     segs.toArray(segms);
     return segms;
   }

}
