//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class TestKlasse {

	public static void main(String[] args) {
		Point2D[] menge = {
				new Point2D(1,1)
				,new Point2D(2,0)
				,new Point2D(3,2)
//				,new Point2D(2,3)
		};
		System.out.println(GeoMath_Nico.getMinCircumcircle(menge));
	}

}
