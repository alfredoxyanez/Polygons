package com.software404.Polygons.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PointGraph Class
 * @author alfredoyanez
 * This class keeps track of all points
 *
 */
public class PointGraph {
	
	// Map of points, where the key is a string "x y" where x and y are the points
	Map<String, PointNode> nodes = new HashMap<String, PointNode>(); 
	
	/**
	 * getOrCreateNode Method
	 * This method created a new node if it does not exist
	 * If it does exist it returns the existing point
	 * This could have been implemented using: return getOrDefault(key, new PointNode(x,y));
	 * but for readability we used if/else statements
	 * @param x value of point
	 * @param y value of point
	 * @return returns a new PointNodeif one does not exist
	 */
	public PointNode getOrCreateNode(Integer x, Integer y) {
		String key = x.toString() + " "+ y.toString();
		if(nodes.containsKey(key)) {
			return nodes.get(key);
		}else {
			PointNode newPoint = new PointNode(x,y);
			nodes.put(key, newPoint);
			return newPoint;
		}
	}
	
	/**
	 * getAllNodes Method
	 * Simply gets all PointNode(s) and return a list
	 * @return List<PointNode> 
	 */
	public List<PointNode> getAllNodes(){
		List<PointNode> l = new ArrayList<PointNode>(this.nodes.values());
		return l;
	}
	

}

