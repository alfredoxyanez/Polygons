package com.software404.Polygons.Graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * PointNode Class
 * @author alfredoyanez
 * This class is used model a point and its connections
 * its connections re other points that it is connected two
 * To make this easy to use i didn't set a limit of connections 
 * that are valid, generally a point node should have 2 connections if 
 * its part of a polygon and only one if it's not
 * Currently a point  can only be integers, but it could easily be modified 
 * to be doubles or floats 
 */
public class PointNode {
	
	Integer x; // x value of the point
	Integer y; // y value of the point
	List<PointNode> connections; // list of connections that a point has
	
	/**
	 * PointNode Constructor
	 * @param x value of point
	 * @param y value of point
	 */
	public PointNode(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
		this.connections = new ArrayList<PointNode>(); //initialize empty arraylist for connections
	}
	/**
	 * addConnection PRIVATE Method 
	 * General function to add a point as a connection to another
	 * It's private because in our instance the points are non-directional
	 * meaning if a point (p1) has a connection to another point (p2)
	 * the other point (p2) is also connected to the first point (p1)
	 * @param node node to connect to this point
	 */
	private void addConnection(PointNode node) {
		this.connections.add(node);
	}
	/**
	 * connectNodes Method
	 * This methods enable us to connect to points to each other
	 * via direct connection (p1) <--> (p2)
	 * @param n1 node to be connected 
	 * @param n2 node to be connected 
	 * For thread safety we may want to may this transactions atomic
	 */
	public static void connectNodes(PointNode n1, PointNode n2) {
		n1.addConnection(n2);
		n2.addConnection(n1);
	}
	/**
	 * getConnections Method
	 * General get method to get all connections for a node
	 * @return  List<PointNode> list of connections (PointNode)
	 */
	public  List<PointNode> getConnections(){
		return this.connections;
	}
	
	/**
	 * getConectionFrom Method
	 * This method allows us to get the other point that this node is connected to
	 * This again has the assumption that a point can only have max 2 connection
	 * (p1)<--> (thisPoint) <--> (p2)
	 * calling: (thisPoint).getConectionFrom(p1) would return => (p2)
	 * and if only has one connection  it will return null
	 * (p1)<--> (thisPoint)
	 * calling: (thisPoint).getConectionFrom(p1) would return => null
	 * this is because if it only has one connection it cannot be part of a polygon
	 * where n >= 3
	 * @param n from node
	 * @return PointNode that this is connected to not being node (n) 
	 * if if only has one connection node (n) it will return null
	 */
	public PointNode getConectionFrom(PointNode n) {
		if(this.connections.size() > 1) {
			if(this.connections.get(0).equals(n)) {
				return this.connections.get(1);
			}else {
				return this.connections.get(0);
			}
		}else {
			return null;
		}
		 
	}
	
	/**
	 *  Override hashCode
	 *  Hashcode relies solely on x and y values
	 *  Maeaning different objects with the same x and y values 
	 *  are equal
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}
	/**
	 * Override equals
	 * Checks that two items are equal 
	 * if they are of the same class,
	 *  have x and y values and those x and y values
	 *  are equal 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointNode other = (PointNode) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	/**
	 * Override toString
	 * General to string method override that makes
	 * points easy to read; helps with debugging
	 */
	@Override
	public String toString() {
		return "PointNode (" + x + "," + y + ")";
	}
	
	
	
	
	
	

}
