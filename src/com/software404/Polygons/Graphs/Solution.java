package com.software404.Polygons.Graphs;

import java.util.ArrayList;
import java.util.List;


/**
 * Solution Class that uses PointNodes and PointGraphs
 * This solution returns a list of points that make up a polygon
 * For this solution we assume that a point can only have 2 max connections (polygons don't share points)
 * And we return a list of points not a list of lines
 * @author alfredoyanez
 *
 */
public class Solution {
	
	public static List<List<PointNode>> GetPolygons(PointGraph pg) {
		List<PointNode> list = pg.getAllNodes(); // List of all nodes we will iterate 
		List<PointNode> usedList = new ArrayList<PointNode>(); // List of nodes that have been used to form a polygon
		List<List<PointNode>> allPolygons =  new ArrayList<>(); //List of all polygons we will return
		//Iterate through all points in the point graph
		for(PointNode point: list) {
			List<PointNode> polygon =  new ArrayList<PointNode>(); // list of possible polygon
			// If a point has less than 2 connections it cannot be a polygon
			// where n >= 3, so we pass/continue
			if(point.connections.size() < 2) {
				continue;
			}
			// if the point has not been used and its not already in the possible polygon
			else if(!usedList.contains(point) && !polygon.contains(point)) {
				if(polygon.isEmpty()) {
					polygon.add(point);  // if the possible polygon is empty you add this point to it
				}
				// the current point then becomes the first connection of the point we are iterating over
				// At this point polygon should only have one node so we could also use polygon.get(0)
				PointNode currentPoint = polygon.get(polygon.size() -1).connections.get(0);
				// We do a while loop to iterate through a possible polygon
				// the if and else statements ensure we don't get stuck in a infinite loop
				// because we are either a new point that we haven't seen or the starting point
				while(true) {
					// if the current point is not the starting point we simply get a new current point
					// in the possible polygon by finding a connection to the current point that is not the previous point
					// this allows us to uniquely move in a clockwise or counterclockwise direction when finding points
					// in a polygon so that we don't get stuck in a loop by going back and forth point connections
					if(currentPoint != polygon.get(0)) {
						PointNode interPoint = currentPoint;
						// Get a new point that is not the last point in the polygon, thus allowing us to move 
						//through a polygon via a consistent direction
						currentPoint = currentPoint.getConectionFrom(polygon.get(polygon.size() -1)); 
						// This enables us to break if case their is an internal loop in a polygon
						// ie two polygons that share a point
						if(polygon.contains(currentPoint) && !currentPoint.equals(polygon.get(0))) {
							break;
						}
						polygon.add(interPoint); // we then add the previous point to the polygon
					}else { // if we arrive back to the first point in the polygon
						allPolygons.add(polygon); // we add the found polygon to the return list
						usedList.addAll(polygon); // we add all these points to the used list 
						break;	// we exit the while loop	
					}	
				}
			}
		}
		System.out.println(allPolygons);
		return allPolygons; // returns list of points that make a polygon
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// We initialize the Point Graph
		PointGraph pg = new PointGraph();
		
		// Here we create all connections
		PointNode n1 = pg.getOrCreateNode(1,5);
		PointNode n2 = pg.getOrCreateNode(2,2);
		PointNode.connectNodes(n1,n2);
		
		PointNode n3 = pg.getOrCreateNode(4,3);
		PointNode n4 = pg.getOrCreateNode(4,2);
		PointNode.connectNodes(n3,n4);
		
		PointNode n5 = pg.getOrCreateNode(5,1);
		PointNode n6 = pg.getOrCreateNode(7,6);
		PointNode.connectNodes(n5,n6);
		
		PointNode n7 = pg.getOrCreateNode(4,3);
		PointNode n8 = pg.getOrCreateNode(5,3);
		PointNode.connectNodes(n7,n8);
				
		PointNode n9 = pg.getOrCreateNode(2,2);
		PointNode n10 = pg.getOrCreateNode(4,2);
		PointNode.connectNodes(n9,n10);

		PointNode n11 = pg.getOrCreateNode(5,3);
		PointNode n12 = pg.getOrCreateNode(4,5);
		PointNode.connectNodes(n11,n12);
		
		PointNode n13 = pg.getOrCreateNode(1,5);
		PointNode n14 = pg.getOrCreateNode(3,5);
		PointNode.connectNodes(n13,n14);

		PointNode n15 = pg.getOrCreateNode(3,5);
		PointNode n16 = pg.getOrCreateNode(4,5);
		PointNode.connectNodes(n15,n16);
		
		PointNode n17 = pg.getOrCreateNode(7,7);
		PointNode n18 = pg.getOrCreateNode(5,5);
		PointNode.connectNodes(n17,n18);
		
		PointNode n19 = pg.getOrCreateNode(5,5);
		PointNode n20 = pg.getOrCreateNode(3,7);
		PointNode.connectNodes(n19,n20);
		
		PointNode n21 = pg.getOrCreateNode(7,7);
		PointNode n22 = pg.getOrCreateNode(3,7);
		PointNode.connectNodes(n21,n22);
		
		// Get solution
		GetPolygons(pg);
		//[[PointNode (2,2), PointNode (1,5), PointNode (3,5), PointNode (4,5), PointNode (5,3), PointNode (4,3), PointNode (4,2)], [PointNode (5,5), PointNode (7,7), PointNode (3,7)]]

		
		
		
		

	}

}
