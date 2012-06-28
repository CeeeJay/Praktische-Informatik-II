// Im Projekt "Aufgabenblatt 11" weiterarbeiten!
public class Graphen {
	private double[][] am;

	public Graphen(int n){
		//n1
		am = new double[n][n];
		for (int i = 0; i < am.length; i++) {
			for (int j = 0; j < am[i].length; j++) {
				am[i][j] = 0;
			}
		}
	}
	public void setEdge(int id1, int id2, double value){
		//TODO
		if ((id1 < am.length && id1 >= 0)&&(id2 < am[id1].length && id2 >= 0)) {
			if (value < 1) {
				am[id1][id2] = 0;
				//    am[id2][id1] = 0;
			} else if(value >=1){
				am[id1][id2] = 1;
				//    am[id2][id1] = 1;
			}
		}
	}
	public String toString(){
		String temp = "";
		for (int i = 0; i < am.length; i++) {
			for (int j = 0; j < am[i].length; j++) {
				temp += "Value at: " + i + "/" + j + "=" + am[i][j] + "\n";
			}
		}
		return temp;
	}
	public Graphen generateTransitiveClosure(){
		//TODO
		Graphen temp = this;
		for (int k = 0; k < am.length; k++) {
			for (int i = 0; i < am.length; i++) {
				if (am[i][k] == 1) {
					for (int j = 0; j < am.length; j++) {
						if (am[k][j] == 1) {
							temp.setEdge(i, j, 1);
							//       temp.setEdge(j, i, 1);
						}
					}
				}
			}
		}
		return temp;
	}
	public String doDepthFirstSearch(){
		//TODO
		return null;
	}
	public void eliminateCycle(){
		//TODO
	}
}