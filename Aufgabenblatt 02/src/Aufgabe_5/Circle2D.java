package Aufgabe_5;

public class Circle2D {

	private Point2D		mittelpunkt	;
	private double		radius;
	
	public Circle2D(Point2D mittelpunkt, double radius){
		this.mittelpunkt	= mittelpunkt;
		this.radius			= radius;
	}
	
	public boolean contains(Point2D punkt){
		return radius >= punkt.getAbstand(mittelpunkt);
	}
	
}
