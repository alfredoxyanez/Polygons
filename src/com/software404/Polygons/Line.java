package com.software404.Polygons;

/**
 * Line Class
 * @author alfredoyanez
 * Line class enables users to create a line with a simple constructor that takes
 * in two points. If the points are the same it throws an exception.
 * Currently points must be Integers, could be later expanded to be Doubles or Floats 
 *
 */
public class Line {

	Point p1; // first point of the line which should be <= p2
	Point p2; // second point of the line which should br >= p1
	
	/**
	 * Line Constructor
	 * @param p1 point 1
	 * @param p2 Point 2
	 * @throws Exception: throws exception if p1 and p2 are the same
	 * One interesting characteristic of this class is that it always organizes the 
	 * points based on the lower x value, if the points have the same x value then it resolves the 
	 * tie breaker by using the y value.
	 */
	public Line(Point p1, Point p2) throws Exception {
		super();
		// here we ensure that the two points in the line must be unique
		if(p1.equals(p2)) {
			throw new Exception("Points must be different");
		}
		// checks to see if the x value of p1 is < p2
		if(p1.x < p2.x) {
			//if it is you set the points appropriately
			this.p1 = p1;
			this.p2 = p2;
		}
		else if(p2.x < p1.x) { // if the x value of p2 is < p1.x the you set p2 as p1 etc.
			this.p1 = p2;
			this.p2 = p1;
		}else { // if the x values are equal you set the order of the points by the y value
			if(p1.y <= p2.y) {
				this.p1 = p1;
				this.p2 = p2;
			}else {
				this.p1 = p2;
				this.p2 = p1;
			}
		}
		
	}



	/**
	 * Override hashCode
	 * This method overrides the hashcode so that we can compare two lines by simply comparing the x 
	 * and y values and not the pointer to the object. Meaning that two different instances of a line 
	 * with the same x and y values will always be equal using the (dot) equals method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	/**
	 * Override equals
	 * The equals method compares two different objects by ONLY using the x and y value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}
	
	/**
	 * Override toString
	 * This override method allows users to print this class in an easy to read manner.
	 */
	@Override
	public String toString() {
		return "Line: (" + p1 + "," + p2 + ")";
	}


}
