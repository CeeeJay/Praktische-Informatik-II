//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class Circle2D {
	public Point2D mittelpunkt;
	public double radius;
	public Circle2D(){
		this.mittelpunkt = new Point2D(0,0);
		radius = 0;
	}
	public Circle2D(Point2D mittelpunkt, double radius){
		this.mittelpunkt = mittelpunkt;
		this.radius = radius;
	}
	@Override
	public String toString() {
		return "Mittelpunkt: " + mittelpunkt.toString() + "\nRadius: " + radius; 
	}
}
