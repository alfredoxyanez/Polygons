package com.software404.Polygons;

/**
 * Point Class
 * @author alfredoyanez
 * Point class enables users to make a point in a 2D space using an x and y coordinate
 * Currently a point  can only be integers, but it could easily be modified to be doubles or floats 
 *
 */
public class Point {
	
	Integer x;
	Integer y;
	
	/**
	 * Point Constructor
	 * General Point constructor
	 * @param x value 
	 * @param y value
	 */
	public Point(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Override hashCode
	 *  Hashcode relies solely on x and y values
	 *  Meaaning different objects with the same x and y values 
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
		Point other = (Point) obj;
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
		return "P(" + x + "," + y + ")";
	}
	
	
	
}
