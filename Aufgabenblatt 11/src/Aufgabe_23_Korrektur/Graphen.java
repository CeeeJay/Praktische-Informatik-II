//Jan Philipp Bamberger, Ewald Bayer, Stephan Malzkorn, Leon Peulings
package Aufgabe_23_Korrektur;

public class Graphen {
	private double[][] am;

	public Graphen(int n){
		//TODO Korrigieren
		am = new double[n][n];
		for (int i = 0; i < am.length; i++) {
			for (int j = 0; j < am[i].length; j++) {
				am[i][j] = 0;
			}
		}
	}

	//es sollte einen gewichteten Graphen ergeben!!
	//-0.5 Pkt
	public void setEdge(int id1, int id2, double value){
		//TODO Korrigieren
		if ((id1 < am.length && id1 >= 0)&&(id2 < am[id1].length && id2 >= 0)) {
			if (value < 1) {
				am[id1][id2] = 0;
				am[id2][id1] = 0;
			} else if(value >=1){
				am[id1][id2] = 1;
				am[id2][id1] = 1;
			}
		}
	}

	public String toString(){
		//TODO Korrigieren
		String temp = "";
		for (int i = 0; i < am.length; i++) {
			for (int j = 0; j < am[i].length; j++) {
				temp += am[i][j] + "\t";
			}
			temp += "\n";
		}
		return temp;
	}
	
	//Ihr solltet den Algorithmus auf symmetrische Matritzen optimieren
	//-1 Pkt
	public Graphen generateTransitiveClosure(){
		//TODO Korrigieren
		Graphen temp = new Graphen(am.length);
		for (int i = 0; i < temp.am.length; i++) {
			temp.am[i] = am[i].clone();
		}
		for (int k = 0; k < am.length; k++) {
			for (int i = 0; i < am.length; i++) {
				if (am[i][k] == 1) {
					for (int j = 0; j < am.length; j++) {
						if (am[k][j] == 1) {
						//Hier sollte eine Kantengewichtung nach Pythagoras stattfinden!
						//-0.5 Pkt
							temp.setEdge(i, j, 1);
							temp.setEdge(j, i, 1);
						}
					}
				}
			}
		}
		return temp;
	}

	//0 Pkt, da kein Kommentar!
	public String doDepthFirstSearch(){
		//TODO Korrigieren
		int startknoten = (int)(Math.random()*am.length);
		int firstStartknoten = startknoten;
		int next = (startknoten+1)%am.length;
		int startknoten_bak = -1;
		int next_bak = -1;
		int max = 0;
		int newMax = 1;
		do{
			newMax = 1;
			while(next != startknoten){
				if(am[startknoten][next]==1){
					newMax++;
					startknoten_bak = startknoten;
					next_bak = next;
					startknoten = next;
					next = startknoten_bak;
					next = (next+1)%am.length == startknoten?(next+1)%am.length:next;
				}
				next = (next+1)%am.length;
			}
			if(newMax > max){
				max = newMax;
				newMax++;
			}else{
				if(startknoten_bak != -1){
					newMax = max + 1;
					startknoten = startknoten_bak;
					next = next_bak;
				}
			}
			startknoten_bak = -1;
			next_bak = -1;
		}while(max < newMax && startknoten != firstStartknoten);
		return "Die Tiefe beträgt ausgehend von " + firstStartknoten + ": " + max;
	}

	//man sollte alle Zykel im Graphen eliminieren, das sind nicht nur die Zykel mit Länge 1!
	//0 Pkt
	public void eliminateCycle(){
		//TODO Korrigieren
		for (int i = 0; i < am.length; i++) {
			if(am[i][i] > 0)
				am[i][i] = 0;
		}
	}
}
