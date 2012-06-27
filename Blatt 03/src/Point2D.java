//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class Point2D {
	public double x;
	public double y;
	public Point2D(){}
	public Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
