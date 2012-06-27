//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class TestKlasse {

	public static void main(String[] args) {
//		Point2D[] menge = {
//				new Point2D(10,10)
//				,new Point2D(20,0)
//				,new Point2D(30,20)
//				,new Point2D(20,30)
//		};
		Point2D[] menge = new Point2D[20];
		for (int i = 0; i < menge.length; i++) {
			menge[i] = new Point2D((Math.random()*320) + 160, (Math.random()*240 + 60));
		}
//		System.out.println(GeoMath.getMinCircumcircle(menge));
		new Fenster(menge);
	}

}
