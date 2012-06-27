
import java.awt.Graphics;

class TTFNode {
	private int count;
	public int values[];
	public TTFNode parent;
	public TTFNode childNodes[];
	
	public TTFNode(TTFNode parent) {
		count = 0;
		values = new int[3];
		childNodes = new TTFNode[4];
		this.parent = parent;
	}
	
	public boolean isLeaf() {
		return (childNodes[0] == null);
	}
	
	public boolean isFull() {
		return (count >= 3);
	}
	
	public int getCount() {
		return count;
	}
	
	public void drawNode(Graphics g, int xPos, int yPos) {
		int value = 20;
		for (int i = 0; i < count; i++) value += Math.round((Integer.toString(values[i]).length()) * 7.3f);   
		g.drawRect(xPos-value/2, yPos-15, value, 20);
		
		String text = "";
		for (int i = 0; i < count-1; i++) text += Integer.toString(values[i])+"|";
		text += Integer.toString(values[count-1]);
		g.drawString(text, xPos+10-value/2, yPos);
	}
}

public class TTFTree {

	public void deleteNode(int nichts){
		// muhaha
	}
	
	public TTFNode root;
	
	public TTFTree() {
		clear();
	}
	
	public void clear() {
		root = null;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	/*********************************************************************************************************/
	/** Methoden zu Aufgabe 20 (Uebungsblatt10) **************************************************************/
	/*********************************************************************************************************/
	
	public void insertNode(int value) {
		if(this.isEmpty()) {
			root = new TTFNode(null);
			return;
		}
		
		TTFNode currentNode = root;
		TTFNode lastNode = null;
		TTFNode tempNode1 = currentNode.childNodes[0];
		TTFNode tempNode2 = currentNode.childNodes[1];
		TTFNode tempNode3 = currentNode.childNodes[2];
		TTFNode tempNode4 = currentNode.childNodes[3];
		while(currentNode != null) {			
			//Erzeugen des Root-Elementes, da dieses Lehr ist
			if(currentNode == root && currentNode == null) {
				currentNode.values[0] = value;
			
			//Das Element ist bereits vorhanden
			} else if(currentNode.values[0] == value) {
				System.out.println("Das Element ist bereits vorhanden!");
				return;
			
			//Abbruch, da der Baum voll ist. es werden jedoch Elemente gegen neuere groessere ausgetauscht!
			} else if(currentNode.parent != root && currentNode.childNodes[3] != null) {
				System.out.println("Der Baum ist bereits voll!");
				return;

			//Die Aktuelle Node ist kleiner als Value und wird ersetzt!
			} else if(currentNode.values[0] < value) {
				if(lastNode.childNodes[0] == currentNode) {
					int tmp = currentNode.values[0];
					currentNode.values[0] = value;
					lastNode.childNodes[0] = currentNode;
					insertNode(tmp);
				} else if(lastNode.childNodes[1] == currentNode) {
					int tmp = currentNode.values[0];
					currentNode.values[0] = value;
					lastNode.childNodes[1] = currentNode;
					lastNode.values[1] = value;
					insertNode(tmp);
				} else if(lastNode.childNodes[2] == currentNode) {
					int tmp = currentNode.values[0];
					currentNode.values[0] = value;
					lastNode.childNodes[2] = currentNode;
					lastNode.values[2] = value;
					insertNode(tmp);
				} else if(lastNode.childNodes[3] == currentNode) {
					int tmp = currentNode.values[0];
					currentNode.values[0] = value;
					lastNode.childNodes[3] = currentNode;
					insertNode(tmp);
				}

			//Kontrolle ob zum ersten Kind gesprungen wird. Wenn dieses Null ist, wird dort eine eneu Node erzeugt!
			} else if(tempNode1 == null) {
				tempNode1 = new TTFNode(currentNode);
				tempNode1.values[0] = value;
				currentNode.childNodes[0] = tempNode1;
			} else if(tempNode1.values[0] > value) {
				currentNode = tempNode1;			
			
			//Kontrolle ob zum ersten Kind gesprungen wird. Wenn dieses Null ist, wird dort eine eneu Node erzeugt!
			} else if(tempNode2 == null) {
				tempNode2 = new TTFNode(currentNode);
				tempNode2.values[0] = value;
				currentNode.childNodes[0] = tempNode1;
				currentNode.values[1] = tempNode2.values[0];
			} else if(tempNode2.values[0] > value) {
				currentNode = tempNode2;
			} else if(lastNode == root) {
				currentNode = tempNode2;
				
			} else if(tempNode3 == null) {
				tempNode3 = new TTFNode(currentNode);
				tempNode3.values[0] = value;
				currentNode.childNodes[0] = tempNode3;
				currentNode.values[2] = tempNode3.values[0];
			} else if(lastNode.parent == root) {
				currentNode = tempNode3;
			} else if(tempNode3.values[0] > value) {
				currentNode = tempNode3;
				
			} else if(tempNode4 == null) {
				tempNode4 = new TTFNode(currentNode);
				tempNode4.values[0] = value;
				currentNode.childNodes[0] = tempNode1;
			} else currentNode = tempNode4;
			lastNode = currentNode;
		}
	}
	
	/*********************************************************************************************************/
	/** Zusaetzliche Methoden zu Aufgabe 20 (Uebungsblatt10) *************************************************/
	/*********************************************************************************************************/
	
	public void drawTTFTree(Graphics g, int x, int y) {
		drawTTFTree_rek(g, root, x, y, x);
	}
	
	private void drawTTFTree_rek(Graphics g, TTFNode n, int xPos, int yPos, int middle) {
		if (n != null) {
			n.drawNode(g, xPos, yPos);
			
			int cnt = n.getCount()+1;
			middle /= cnt;
			if (cnt == 2) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos+middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos+middle, yPos+50, middle);
				}
			} else if (cnt == 3) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-2*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-2*middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos, yPos+50, middle);
				}
				if (n.childNodes[2] != null) {
					g.drawLine(xPos, yPos+5, xPos+2*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[2], xPos+2*middle, yPos+50, middle);
				}
			} else if (cnt == 4) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-3*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-3*middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos-middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos-middle, yPos+50, middle);
				}
				if (n.childNodes[2] != null) {
					g.drawLine(xPos, yPos+5, xPos+middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[2], xPos+middle, yPos+50, middle);
				}
				if (n.childNodes[3] != null) {
					g.drawLine(xPos, yPos+5, xPos+3*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[3], xPos+3*middle, yPos+50, middle);
				}
			}
		}
	}
}

