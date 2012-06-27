//Abgabe von Ewald Bayer und Lukas Schmidtmann
public class Aufgabe03 {
	//3 unterschiedliche Möglichkeiten Aufgabe a) zu lösen
	public static int ex(int n){
		return (int)Math.pow(2, n);
	}
	
	public static int zweiHoch(int n){
		int zweiHochN = 1;
		for (int i = 0; i < n; i++) {
			zweiHochN*=2;
		}
		return zweiHochN;
	}
	public static int zweiHochRek(int n){
		if(n > 0){
			return 2*zweiHochRek(n-1);
		}else{
			return 1;
		}
	}
	
	//Danke, aber eine reicht. Entscheidet euch! Nehmt meinetwegen die effizienteste!
	
	
	//Aufgabe b)
	public static int dez(int n){

		int zahl = ex(n);
		int zaehler = 0;
		
		//Schleife zum Zaehlen den Dezimalstellen.
		while (zahl > 0) {
			zahl = zahl / 10;
			zaehler++;
		}
		
		zahl = (int)Math.pow(2, n);
		
		return zaehler;
	}
}


//Euer Code braucht eine Main-Mehtode => Compiliert nicht, wird nächstes mal genullt! 
//Was ist mit N >= 32 und N=0? 
//0.5 + 2 Pkt
