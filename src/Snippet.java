import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Snippet {
	public static void main(String[] args) {
	
	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }
	
	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();
	
	    // print and draw the line segments
	    //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	     FastCollinearPoints collinear = new FastCollinearPoints(points);
	     LineSegment[] array = collinear.segments();
	    for (LineSegment segment : array) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    System.out.println(array.length);
	    StdDraw.show();
	}
}