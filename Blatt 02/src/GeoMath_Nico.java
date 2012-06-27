
public class GeoMath_Nico {

	/**********************************************************************************************************/	
	/*** Aufgaben vom Uebungsblatt02 **************************************************************************/  
	/**********************************************************************************************************/

	public static Circle2D getMinCircumcircle(Point2D[] pnts) {
		Circle2D result = null;
		Circle2D tmp = new Circle2D();
		// Der boolesche Wert 'line' beschreibt, ob waehrend der Berechnungen die Gerade zwischen
		// Pi und Pj oder das Dreieck bzgl. dem Tripel (Pi, Pj, Pk) betrachtet wird. Die Variable
		// 'allIn' trifft Aussagen darueber, ob sich die Punktmenge innerhalb des Kreises befindet 
		boolean line, allIn;
		double hlp;

		for (int i = 0; i < pnts.length; i++) {
			// Die Werte fuer j bzw. k koennen bei i+1 beginnen, da andernfalls jedes Punktpaar bzw. -tripel
			// doppelt betrachtet und Berechnungen doppelt durchgefuehrt werden
			for (int j = i+1; j < pnts.length; j++) {
				for (int k = j; k < pnts.length; k++) {
					// 01. Berechnung der Kreisdaten:
					// Im Fall von k gleich j, wird lediglich das Punktpaar (Pi, Pj) betrachtet:  
					if (k == j) {
						tmp.mittelpunkt.x = 0.5 * (pnts[i].x + pnts[j].x);
						tmp.mittelpunkt.y = 0.5 * (pnts[i].y + pnts[j].y);
						tmp.radius = 0.5 * getDistance(pnts[i], pnts[j]);
						line = true;
					// Andernfalls wird das Dreieck ueber das Tripel (Pi, Pj, Pk) betrachtet:
					} else {
						hlp = (((pnts[i].x * (pnts[k].y - pnts[j].y))
							+   (pnts[j].x * (pnts[i].y - pnts[k].y))
							+   (pnts[k].x * (pnts[j].y - pnts[i].y))) * 2);
						if (hlp == 0.0) continue;

						tmp.mittelpunkt.y = ((pnts[i].x - pnts[j].x) * (sqr(pnts[k].x) + sqr(pnts[k].y))
									 +  (pnts[j].x - pnts[k].x) * (sqr(pnts[i].x) + sqr(pnts[i].y))
									 +  (pnts[k].x - pnts[i].x) * (sqr(pnts[j].x) + sqr(pnts[j].y))) / hlp;

						hlp = (2 * (pnts[i].x - pnts[j].x));
						if (hlp == 0.0) continue;

						tmp.mittelpunkt.x = (sqr(pnts[i].x) - sqr(pnts[j].x) 
									 +  sqr(pnts[i].y) - sqr(pnts[j].y)
									 +  2 * tmp.mittelpunkt.y * (pnts[j].y - pnts[i].y)) / hlp;
						tmp.radius = getDistance(tmp.mittelpunkt, pnts[i]);
						line = false;
					}
					// 02. Befinden sich die restlichen Punkte innerhalb des berechneten Kreises?
					allIn = true;
					for (int l = 0; l < pnts.length; l++) {
						if ((l != i) && (l != j) && (l != k) && (getDistance(tmp.mittelpunkt, pnts[l]) > tmp.radius)) {
							allIn = false;
							break;
						}
					}
					if (allIn) {
						// Wenn das Dreieck betrachtet wurde, wird ueberprueft ob der
						// aktuell ermittelte Kreis kleiner ist als ein zuvor Gefundener
						if (!line) {
							if (result != null) {
								if (result.radius > tmp.radius) {
									result.mittelpunkt.x = tmp.mittelpunkt.x;
									result.mittelpunkt.y = tmp.mittelpunkt.y;
									result.radius = tmp.radius;
								}
							} else result = new Circle2D(new Point2D(tmp.mittelpunkt.x, tmp.mittelpunkt.y), tmp.radius);
						// Wenn die Gerade zwischen Pi und Pj betrachtet und damit ein
						// umschliessender Kreis gefunden wurde, ist dieser der kleinste und
						// die Berechnung kann mit der Rueckgabe beendet werden
						} else return tmp;
					}
				}
			}
		}
		
		return result;
	}

	/**********************************************************************************************************/	
	/*** Hilfsmethoden zu Uebungsblatt02 **********************************************************************/  
	/**********************************************************************************************************/

	private static double sqr(double value) {
		return (value * value);
	}

	public static double getDistance(Point2D a, Point2D b) {
		return (Math.sqrt(sqr(b.x - a.x) + sqr(b.y - a.y)));
	}
	
}
