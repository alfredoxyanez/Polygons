package com.software404.Polygons;

import java.util.List;

/**
 * Polygon CLass
 * @author alfredoyanez
 * General polygon class that is made up of a list of lines
 *
 */
public class Polygon {
	
	List<Line> lines; // List of lines that make up polygon

	/**
	 * Polygon Constructor
	 * Constructor that takes in a list of lines as a param
	 * @param lines
	 */
	public Polygon(List<Line> lines) {
		super();
		this.lines = lines;
	}

	/**
	 * Override toString
	 * General toString method that makes polygons easy to read/debug
	 */
	@Override
	public String toString() {
		return "Polygon [lines=" + lines + "]";
	}
	
	
	

}
