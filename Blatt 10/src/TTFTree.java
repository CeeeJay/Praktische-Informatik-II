
import java.awt.Graphics;

import javax.swing.JOptionPane;

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
		for(TTFNode n: childNodes)
			if(n != null)
				return false;
		return true;
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
	
	public boolean addValue(int value){
		if(!isFull()){
			switch (count) {
			case 0:
				values[0] = value;				
				break;
			case 1:
				if(values[0] < value)
					values[1] = value;
				else if(values[0] > value){
					values[1] = values[0];
					values[0] = value;
				}else
					return true;
				break;
			case 2:
				if(values[1] < value)
					values[2] = value;
				else if(values[1] > value){
					values[2] = values[1];
					if(values[0] < value)
						values[1] = value;
					else if(values[0] > value){
						values[1] = values[0];
						values[0] = value;
					}else
						return true;
				}else
					return true;
				break;
			default:
				break;
			}
			count++;
			return true;
		}else
			return false;
	}
	
	public void split(int fuer){
		TTFNode currentNode = this;
		try{
			if(currentNode.values[2] < fuer){
				
			}
		}catch(NullPointerException e){
			
		}
	}
	
}

public class TTFTree {
	
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
		// TODO: Einfuegen von neuen Knoten in den 2-3-4 Baum
		if(isEmpty()){
			root = new TTFNode(null);
			root.addValue(value);
		}else{
			if(!root.addValue(value)){
				if(value < root.values[0]){
					if(root.childNodes[0] == null)
						root.childNodes[0] = new TTFNode(root);
					root.childNodes[0].addValue(value);
				}else if(value > root.values[0]){
					try{
						if(value > root.values[0]&&value < root.values[1]){
							if(root.childNodes[1] == null)							
								root.childNodes[1] = new TTFNode(root);
							root.childNodes[1].addValue(value);
						}else if(value > root.values[1]){
							try{
								if(value > root.values[1]&&value < root.values[2]){
									if(root.childNodes[2] == null)
										root.childNodes[2] = new TTFNode(root);
									root.childNodes[2].addValue(value);
								}else if(value > root.values[2]){
									if(root.childNodes[3] == null)
										root.childNodes[3] = new TTFNode(root);
									root.childNodes[3].addValue(value);
								}
							}catch(NullPointerException e){
								if(value > root.values[1]){
									if(root.childNodes[2] == null)
										root.childNodes[2] = new TTFNode(root);
									root.childNodes[2].addValue(value);
								}
							}
						}
					}catch(NullPointerException e){
						if(value > root.values[0]){
							if(root.childNodes[1] == null)
								root.childNodes[1] = new TTFNode(root);
							root.childNodes[1].addValue(value);
						}
					}
				}
			}
		}
		
	}
	
	//Nicht beachten
	public void deleteNode(int value){
		JOptionPane.showMessageDialog(null, "Nicht von der Aufgabenstellung vorgesehen.\nWollte GUI-Klasse nicht zu stark verändern.");
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
