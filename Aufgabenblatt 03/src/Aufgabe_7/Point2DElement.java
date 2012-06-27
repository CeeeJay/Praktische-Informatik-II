package Aufgabe_7;

public class Point2DElement {
	
	public Point2DElement(Point2D element, Point2DElement before, Point2DElement after) {
		super();
		this.element = element;
		this.before = before;
		this.after = after;
	}
	
	private Point2D element;
	private Point2DElement before, after;
	
	public Point2DElement getBefore() {
		return before;
	}
	public void setBefore(Point2DElement before) {
		this.before = before;
	}
	public Point2DElement getAfter() {
		return after;
	}
	public void setAfter(Point2DElement after) {
		this.after = after;
	}
	public Point2D getElement() {
		return element;
	}
	
	
}
