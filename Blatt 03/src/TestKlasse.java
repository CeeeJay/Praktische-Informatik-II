//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class TestKlasse {

	public static void main(String[] args) {
		Point2D[] menge = {
				new Point2D(1,1)
				,new Point2D(2,0)
				,new Point2D(3,2)
				,new Point2D(2,3)
		};
		Point2DListe liste = new Point2DListe();
		System.out.println(liste.isEmpty());
		System.out.println(liste.getLength());
		for (int i = 0; i < menge.length; i++) {
			liste.add(menge[i]);
		}
		System.out.println(liste.isEmpty());
		System.out.println(liste.getLength());
		for (int i = 0; i < liste.getLength(); i++) {
			System.out.println(liste.getElementAt(i).getIns());
		}
		liste.sort();
		System.out.println();
		for (int i = 0; i < liste.getLength(); i++) {
			System.out.println(liste.getElementAt(i).getIns());
		}
	}

}
