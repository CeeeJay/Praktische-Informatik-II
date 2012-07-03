package Aufgabe_23_Korrektur;

public class Main {

	public static void main(String[] args) {
		Graphen gr = new Graphen(6);
		gr.setEdge(0, 1, 1);
		gr.setEdge(1, 4, 1);
		gr.setEdge(1, 2, 1);
		gr.setEdge(1, 3, 1);
		gr.setEdge(3, 5, 1);
		System.out.println("Adjazenzmatrix:");
		System.out.println(gr);
		System.out.println(gr.doDepthFirstSearch());
		System.out.println("Transitive Hülle:");
		System.out.println(gr.generateTransitiveClosure());
	}

}
