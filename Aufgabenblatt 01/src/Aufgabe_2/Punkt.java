package Aufgabe_2;
//Hilfsklasse f�r Aufgabe 2.
//Stephan Malzkorn, Jan Philipp Bamberger, Leon Peulings

public class Punkt {
	
	//Variablen zum Speichern der X und der Y Koordinate
	private double x, y;
	
	//Maximalwert und Minimalwert f�r x und y
	private final int max = 200;
	private final int min = 0;
	
	//Konstuktor
	public Punkt(double x, double y){
		//Initialisieren der Variablen wobei sie in einem gewissen Wertebereich bleiben m�ssen der durch min und max definiert wird.
		this.x = x > max ? max : x < min ? min : x;
		this.y = y > max ? max : y < min ? min : y;
	}
	
	//Gettermethode f�r x
	public double getX(){
		return x;
	}

	//Gettermethode f�r y
	public double getY(){
		return y;
	}
}
