package com.software404.Polygons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Solution Class using Points, Lines and Polygons
 * @author alfredoyanez
 *
 */
public class Solution {
	
	public static List<Polygon> GetPolygons(List<Line> lines) throws Exception{
		
		Map<Point, List<Point>> points = new HashMap<Point, List<Point>>();
		// We create a map of all points and those points connections
		for(Line l: lines) {
			// if a point is not in the map of points we add it as a key and the
			// connection point is added as a single value to the list of connection points
			// If the point does exist we simple add the connection point to the list of connections
			List<Point> c_1 = points.getOrDefault(l.p1, new ArrayList<Point>());
			c_1.add(l.p2);
			points.put(l.p1, c_1);
			
			List<Point> c_2 = points.getOrDefault(l.p2, new ArrayList<Point>());
			c_2.add(l.p1);
			points.put(l.p2, c_2);	
		}
		
		List<Line> usedLines = new ArrayList<Line>(); // List of used lines
		List<Polygon> returnPoly = new ArrayList<Polygon>(); // List of polygons we will return
		
		// We iterate through every line
		for(Line l: lines) {	
			// if the lines hasn't been used
			if(!usedLines.contains(l)) {
				// We create a new list of points
				List<Point> polygonPoints = new ArrayList<Point>();
				
				// we add the first point of the line to the possible polygonPoints
				polygonPoints.add(l.p1);
				Point currentPoint = l.p2; // the current point is the second point of the lines
				while(true) { // we begin to find points that are connected to the current point
					List<Point> currentPointList = points.get(currentPoint); // here we get a list of connections to the current point
					if(currentPointList.size()< 1) { 
						// if this point has no connections then we know the current list of polygonPoints
						// cannot be a connected polygon
						break; // exit while loop
					}else {
						// we use streams and a filter  o find points that are currently not in the proposed polygon points
						Optional<Point> haventVisitedPoint = currentPointList.stream().filter(point -> !polygonPoints.contains(point)).findFirst();
						
						// If there are not more points that we haven't visited
						// Then we know we either have a complete shape or an incomplete one
						if(haventVisitedPoint.isEmpty()) { 
							// If the current point is connected to the start point
							// We then know we have a complete polygon
							if(currentPointList.contains(polygonPoints.get(0))) {
								polygonPoints.add(currentPoint); // we add the current point to the polygon points
								// If the PolygonPoints do not contain more than 3 points we know that 
								// the polygon cannot be n >= 3
								if(polygonPoints.size() < 3) {
									break; // break
								}
								// We initialize the list of lines that make  up a polygon
								List<Line> proposedPolygon = new ArrayList<Line>();
								// We iterate through all points and make lines from connecting points
								for(int i = 0; i < polygonPoints.size(); i++) {
									if(i != polygonPoints.size() -1) {
										proposedPolygon.add(new Line(polygonPoints.get(i), polygonPoints.get(i+1)));
										
									}else {
										proposedPolygon.add(new Line(polygonPoints.get(i), polygonPoints.get(0)));
									}
								}
								// We add all these lines in the whole polygon to the isedLines
								// because lines cannot be shared.
								usedLines.addAll(proposedPolygon);
								// We add this polygon to the list of polygons we will return
								returnPoly.add(new Polygon(proposedPolygon));
								break;	// exit while loop
							}
						}else { // if we did find a connected point that we haven't visited we make tha our current point
							polygonPoints.add(currentPoint);
							currentPoint = haventVisitedPoint.get();
						}
						
					}
				}
			}
			
		}
		System.out.println(returnPoly);
		return returnPoly;  // return list of polygons
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Here we create one List of lines  that have 2 polygons
		List<Line> lines = Arrays.asList(
				new Line(new Point(1,5), new Point(2,2)),
				new Line(new Point(4,3), new Point(3,5)),
				new Line(new Point(5,1), new Point(7,6)),
				new Line(new Point(4,3), new Point(5,3)),
				new Line(new Point(2,2), new Point(4,2)),
				new Line(new Point(5,3), new Point(4,5)),
				new Line(new Point(1,5), new Point(4,2)),
				new Line(new Point(3,5), new Point(4,5))
				);
		GetPolygons(lines);
		//[Polygon [lines=[Line: (P(1,5),P(2,2)), Line: (P(2,2),P(4,2)), Line: (P(1,5),P(4,2))]], Polygon [lines=[Line: (P(3,5),P(4,3)), Line: (P(4,3),P(5,3)), Line: (P(4,5),P(5,3)), Line: (P(3,5),P(4,5))]]]

		//Here we create another list of lines that has one polygon
		List<Line> lines2 = Arrays.asList(
				new Line(new Point(1,5), new Point(2,2)),
				new Line(new Point(4,3), new Point(4,2)),
				new Line(new Point(5,1), new Point(7,6)),
				new Line(new Point(4,3), new Point(5,3)),
				new Line(new Point(2,2), new Point(4,2)),
				new Line(new Point(5,3), new Point(4,5)),
				new Line(new Point(1,5), new Point(3,5)),
				new Line(new Point(3,5), new Point(4,5))
				);
		GetPolygons(lines2);
		//[Polygon [lines=[Line: (P(1,5),P(2,2)), Line: (P(2,2),P(4,2)), Line: (P(4,2),P(4,3)), Line: (P(4,3),P(5,3)), Line: (P(4,5),P(5,3)), Line: (P(3,5),P(4,5)), Line: (P(1,5),P(3,5))]]]

		

	}


}
