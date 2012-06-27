
public class GeoMath_zuerst {
	public static Circle2D getMinCircumcircle(Point2D[] pnts){
		Circle2D tempKreis = new Circle2D();
		boolean kreismodus = true;
		int tempi = -1, tempj = -1, tempj2 = -1;
		boolean gefunden = false;
		boolean abort = false;
		if(pnts.length==1){
			return new Circle2D(pnts[0], 0);
		}
		for (int i = 0; i < pnts.length; i++) {
			if(kreismodus){
				gefunden = false;
				for (int j = 0; j < pnts.length; j++) {
					if(tempi!=-1||tempj!=-1){
						if(tempi < pnts.length&&tempj < pnts.length){
							i = tempi;
							j = tempj;
							tempi = -1;
							tempj = -1;	
						}else{
							tempi = -1;
							tempj = -1;
							break;
						}
					}
					if(i!=j){
						tempKreis = new Circle2D(generiereMittelpunkt(pnts[i], pnts[j]), abstand(pnts[i], pnts[j])/2);
						tempi = i;
						tempj = j+1;
						kreismodus = false;
						i = j;
						break;
					}
				}
			}else{
				if(abstand(pnts[i],tempKreis.mittelpunkt) > tempKreis.radius){
					kreismodus = true;
					i = 0;
				}
				gefunden = true;
			}
		}
		if(!gefunden){
			for (int i = 0; i < pnts.length; i++) {
				if(kreismodus){
					for (int j = 0; j < pnts.length; j++) {
						for (int j2 = 0; j2 < pnts.length; j2++) {
							if(i!=j&&i!=j2&&j!=j2){
								if(tempi!=-1||tempj!=-1||tempj2!=-1){
									if(tempj2 < pnts.length){	
										j2 = tempj2;
									}else{
										tempj++;
										j2 = 0;
									}
									if(tempj < pnts.length){
										j = tempj;
									}else{
										tempi++;
										j2 = 0;
										j = 0;
									}
									if(tempi < pnts.length){
										i = tempi;
									}else{
										tempi = -1;
										tempj = -1;
										tempj2 = -1;
										abort = true;
										break;
									}
									tempi = -1;
									tempj = -1;
									tempj2 = -1;
								}
								tempKreis = dreiecksUmkreis(pnts[i], pnts[j], pnts[j2]); 
								tempi = i;
								tempj = j;
								tempj2 = j2+1;
								kreismodus = false;
								i = j2;
								break;
							}
						}
						if(!kreismodus||abort)
							break;
					}
				}else{
					if(abstand(pnts[i],tempKreis.mittelpunkt) > tempKreis.radius){
						kreismodus = true;
						i = 0;
					}
					gefunden = true;
				}
			}
		}
		return tempKreis;
	}
	private static double abstand(Point2D pi, Point2D pj){
		return Math.sqrt(Math.pow(pj.x-pi.x, 2)+Math.pow(pj.y-pi.y, 2));
	}
	private static Point2D generiereMittelpunkt(Point2D pi, Point2D pj){
		return new Point2D((pi.x+pj.x)/2, (pi.y+pj.y)/2);
	}
	private static Circle2D dreiecksUmkreis(Point2D pi, Point2D pj, Point2D pk){
		double uy = ((pi.x - pj.x)*(Math.pow(pk.x, 2)+Math.pow(pk.y, 2))+(pj.x-pk.x)*(Math.pow(pi.x, 2)+Math.pow(pi.y, 2))+(pk.x-pi.x)*(Math.pow(pj.x, 2)+Math.pow(pj.y, 2)))/(2*(pi.x*(pk.y-pi.y)+pj.x*(pi.y-pk.y)+pk.x*(pj.y-pi.y)));
		double ux = (Math.pow(pi.x, 2)-Math.pow(pj.x, 2)+Math.pow(pi.y, 2)-Math.pow(pj.y, 2)+2*uy*(pj.y - pi.y))/2*(pi.x-pj.x);
		Point2D mittelpunkt = new Point2D(ux, uy);
		return new Circle2D(mittelpunkt, abstand(mittelpunkt, pi));
	}
}
