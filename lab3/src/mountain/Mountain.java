package mountain;

import java.util.Iterator;
import java.util.LinkedList;

import fractal.*;

public class Mountain extends Fractal {
	private Point p1, p2, p3;
	private double dev;
	private LinkedList<Side> sides;

	/** Creates an object that handles Koch's fractal. 
	 * @param length the length of the triangle side
	 */
	public Mountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		this.sides = new LinkedList<Side>();
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Bergsmassiv";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		fractalMountain(turtle, p1, p2, p3, order, dev);
	}

	/* 
	 * Reursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalMountain(TurtleGraphics turtle, Point p1, Point p2, Point p3, int order, double dev) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.penDown();
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			
			Point mp1p2 = middlePoint(p1,p2,dev);
			Point mp1p3 = middlePoint(p1,p3,dev);
			Point mp2p3 = middlePoint(p2,p3,dev);
			
			dev /=2;
			fractalMountain(turtle, p1, mp1p2, mp1p3, order-1, dev);
			fractalMountain(turtle, mp1p2, p2, mp2p3, order-1, dev);
			fractalMountain(turtle, mp2p3, p3, mp1p3, order-1, dev);
			fractalMountain(turtle, mp1p2, mp2p3, mp1p3, order-1, dev);
		}
	}
	
	private Point middlePoint(Point p1, Point p2, double dev) {
		Iterator<Side> itr = sides.iterator();
		
		while(itr.hasNext()){
			Side s = itr.next();
			if(s.equalPoints(p1,p2)){
				itr.remove();
				return s.getM();
			}
		}
		double x = (p1.getX() + p2.getX()) / 2;
		double y = (p1.getY() + p2.getY()) / 2;
		Point m = new Point(x,y);
		m = new Point(m.getX(),m.getY() + randFunc(dev));
		sides.add(new Side(p1,p2,m));
		return m;
	}
	
	private static double randFunc(double dev) {
		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
		if (Math.random() < 0.5) {
			t = -t;
		}
		return t;
	}

}
