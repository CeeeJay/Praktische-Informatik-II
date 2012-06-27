package Aufgabe_5;

public class GeoMath {

	public static Circle2D getMinCircumcircle(Point2D[] pnts){
		
		//SCHRITT 01
		for (int i = 0; i < pnts.length; i++) {
			boolean enthaeltAlle = true;
			for (int j = i; j < pnts.length; j++) {
				Circle2D test = new Circle2D(new Point2D((pnts[i].getX()+pnts[j].getX())/2, (pnts[i].getY()+pnts[j].getY())/2), pnts[i].getAbstand(pnts[j])/2);
				for (int j2 = 0; j2 < pnts.length; j2++) {
					if(!test.contains(pnts[j2])){
						enthaeltAlle = false;
						break;
					}
				}
				if(enthaeltAlle){
					return test;
				}
			}
		}
		//SCHRITT 02
		for (int i = 0; i < pnts.length; i++) {
			boolean enthaeltAlle = true;
			for (int j = i; j < pnts.length; j++) {
				for (int k = j; k < pnts.length; k++) {
					Circle2D test = new Circle2D(new Point2D((pnts[i].getX()+pnts[j].getX()+pnts[k].getX())/3, (pnts[i].getY()+pnts[j].getY()+pnts[k].getX())/3), pnts[i].getAbstand(pnts[j])/(2*(pnts[i].getAbstand(pnts[k])/pnts[j].getAbstand(pnts[k]))));
					for (int l = 0; l < pnts.length; l++) {
						if(!test.contains(pnts[l])){
							enthaeltAlle = false;
							break;
						}
					}
					if(enthaeltAlle){
						return test;
					}
				}
			}
		}
		return null;
	}
	
}
