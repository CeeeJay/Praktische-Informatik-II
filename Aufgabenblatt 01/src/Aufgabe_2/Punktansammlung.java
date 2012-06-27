package Aufgabe_2;

import java.util.ArrayList;

//Klasse für Aufgabe 2.
//Stephan Malzkorn, Jan Philipp Bamberger, Leon Peulings

public class Punktansammlung{

	//Liste zum Verwalten aller Punkte
	private ArrayList<Punkt> punkte = new ArrayList<Punkt>();
	private Punkt mittelpunkt;
	private double radius;
	
	//Konstruktor
	public Punktansammlung(){
		mittelpunkt =  new Punkt(0, 0);
		radius = 0;
	}
	
	//Methode um einen Punkt hinzuzufügen
	public void addPunkt(Punkt neu){
		punkte.add(neu);
		this.gibMitteUndRadius();
		this.alleImKreis();
	}
	
	private void alleImKreis() {
		
		boolean alleImKreis = true;
		
		for (Punkt p : punkte) {
			
			if(Math.sqrt(Math.pow(Math.abs(p.getX() - mittelpunkt.getX()), 2) + Math.pow(Math.abs(p.getY() - mittelpunkt.getY()), 2)) > radius) alleImKreis = false;
			
		}
		System.out.println(alleImKreis);
	}

	public void gibMitteUndRadius(){
		
		this.getMittelpunkt();
		System.out.println("Die Koordinaten des Mittelpunktes sind: X = " + mittelpunkt.getX() + " Y = " + mittelpunkt.getY());
		System.out.println("Der Radius des Kreises beträgt: " + radius);
		
	}
	
	private void getRadius(double kleinX, double kleinY, double grossX, double grossY){
		
		grossX -= mittelpunkt.getX();
		grossY -= mittelpunkt.getY();
		kleinX = mittelpunkt.getX() - kleinX;
		kleinY = mittelpunkt.getY() - kleinY;
		
		kleinX = grossX < kleinX ? kleinX : grossX;
		kleinY = grossY < kleinY ? kleinY: grossY;
		
		radius = Math.sqrt(Math.pow(kleinX, 2) + Math.pow(kleinY, 2));		
	}
	
	private void getMittelpunkt(){
		
		double kleinX = 200, kleinY = 200, grossX = 0, grossY = 0;
	
		if(punkte.size() < 2){
			
			if (punkte.size() == 1) {
				

				mittelpunkt = punkte.get(0);
				kleinX = mittelpunkt.getX();
				grossX = mittelpunkt.getX();
				kleinY = mittelpunkt.getY();
				grossY = mittelpunkt.getY();
				
			} else {
				
				kleinX = 0;
				kleinY = 0;
				mittelpunkt = new Punkt(0,0);

			}
			
		}else{
			
			//kleinster und grösster x und y-Wert wird gesucht
			for (Punkt p : punkte) {
				if(p.getX() < kleinX) kleinX = p.getX();
				if(p.getY() < kleinY) kleinY = p.getY();
				if(p.getX() > grossX) grossX = p.getX();
				if(p.getY() > grossY) grossY = p.getY();
			}
			//Mittelpunkt wird berechnet
			mittelpunkt = new Punkt(kleinX + ((grossX - kleinX) / 2), kleinY + ((grossY - kleinY) / 2));
		}
		
		this.getRadius(kleinX, kleinY, grossX, grossY);
	}
	
	public static void main(String[] args) {
		
		Punktansammlung test = new Punktansammlung();
		
		for (int i = 0; i < Math.random() * 1000; i++) {
			
			test.addPunkt(new Punkt(Math.random() * 200,Math.random() * 200));
			
		}

	}

}
