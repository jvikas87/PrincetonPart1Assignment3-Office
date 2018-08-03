import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
	private List<Point> points;
	private int numberOfSegment;
	private List<LineSegment> segmentList;

	public FastCollinearPoints(Point[] points) { // finds all line segments
		if (points == null) {
			throw new IllegalArgumentException();
		}
		for (Point p : points) {
			if (p == null) {
				throw new IllegalArgumentException();
			}
		}
		for (int outer = 0; outer < points.length; outer++) {
			for (int inner = outer + 1; inner < points.length; inner++) {
				if (points[outer].compareTo(points[inner]) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.points = new ArrayList<>(Arrays.asList(points)); // containing 4
																// point
	}

	public int numberOfSegments() {
		return numberOfSegment; // the number of line segments

	}

	public LineSegment[] segments() {
		if (segmentList != null) {
			return segmentList.toArray(new LineSegment[0]).clone();
		}
		segmentList = new ArrayList<>();

		if (points.size() <= 3) {
			return segmentList.toArray(new LineSegment[0]);
		}
		for (Point current : points) {
			List<Point> newList = new ArrayList<>(points);
			newList.remove(current);
			Collections.sort(newList, current.slopeOrder());
			int initIndex = 0;
			int index = 1;
			int count = 2;
			double slope = current.slopeTo(newList.get(0));
			while (index < newList.size()) {
				double currentSlope = current.slopeTo(newList.get(index));
				if (slope == currentSlope) {
					count++;
				} else if (count >= 4) {
					List<Point> list = new ArrayList<>();
					list.add(current);
					for (int idx = initIndex; idx < index; idx++) {
						list.add(newList.get(idx));
					}
					Collections.sort(list);
					LineSegment segment = new LineSegment(list.get(0), list.get(list.size() - 1));
					if (!isSegmentExist(segmentList, segment)) {
						segmentList.add(segment);
						numberOfSegment++;
					}
					initIndex = index;
					count = 2;
					slope = currentSlope;
				} else {
					initIndex = index;
					count = 2;
					slope = currentSlope;
				}
				index++;
			}
			if (count >= 4) {
				List<Point> list = new ArrayList<>();
				list.add(current);
				for (int idx = initIndex; idx < index; idx++) {
					list.add(newList.get(idx));
				}
				Collections.sort(list);
				LineSegment segment = new LineSegment(list.get(0), list.get(list.size() - 1));
				if (!isSegmentExist(segmentList, segment)) {
					segmentList.add(segment);
					numberOfSegment++;
				}
			}
		}
		return segmentList.toArray(new LineSegment[0]);
	}

	private boolean isSegmentExist(List<LineSegment> segmentList, LineSegment segment) {
		for (LineSegment seg : segmentList) {
			if (seg.toString().equals(segment.toString())) {
				return true;
			}
		}
		return false;
	}
}