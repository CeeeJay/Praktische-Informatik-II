package Aufgabe_5;

public class Point2D {

	private double	x,y;
	
	public Point2D(){
		x = 0;
		y = 0;
	}
	
	public Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getAbstand(Point2D punkt){
		return Math.sqrt(Math.abs(punkt.getX()-x)*Math.abs(punkt.getX()-x)+Math.abs(punkt.getY()-y)*Math.abs(punkt.getY()-y));
	}
	
}
