
import java.awt.Graphics;

class AVLNode {

	public AVLNode parent, left, right;
	public int data, balance;

	public AVLNode(int data) {
		this(data, null, null, null);
	}

	public AVLNode(int data, AVLNode parent) {
		this(data, parent, null, null);
	}

	public AVLNode(int data, AVLNode parent, AVLNode left, AVLNode right) {
		this.data	= data;
		this.parent	= parent;
		this.left	= left;
		this.right	= right;
		balance		= 0;
	}

	public void drawNode(Graphics g, int xPos, int yPos) {
		int value = Math.round((Integer.toString(data).length()-1) * 3.45f);
		g.drawString(Integer.toString(data), xPos-3-value, yPos);
		g.drawOval(xPos-15-value, yPos-15, 30+2*value, 20);
	}
}

public class AVLTree {

	public AVLNode root;

	public AVLTree() {
		//
	}

	public void clear() {
		root = null;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	/*********************************************************************************************************/
	/** Methoden zu Aufgabe 19 (Uebungsblatt09) **************************************************************/
	/*********************************************************************************************************/

	// Eine Methode zum Erhalten des Balancewertes
	private int getBalance(AVLNode node){
		// TODO Bewerten
		if(node==null)
			return 0;
		else
			return getHeight(node.right)-getHeight(node.left);
	}

	// Eine Methode zum Erhalten der Höhe
	private int getHeight(AVLNode node){
		// TODO Bewerten
		if(node==null)
			return 0;
		else if(node.left==null&&node.right==null)
			return 1;
		else if(node.left==null)
			return 1+getHeight(node.right);
		else if(node.right==null)
			return 1+getHeight(node.left);
		else{
			int temp1 = getHeight(node.left);
			int temp2 = getHeight(node.right);
			if(temp1 > temp2)
				return 1+temp1;
			else
				return 1+temp2;
		}
	}

	// Die Methode zum Einfuegen von Knoten.
	public void insertNode(int value) {
		// TODO Bewerten
		// TODO Bis jetzt Crap
		if(this.isEmpty()){
			root = new AVLNode(value);
			return;
		}
		AVLNode currentNode = root;
		AVLNode lastParent = null;
		while(currentNode != null){
			lastParent = currentNode;
			if(currentNode.data < value){
				currentNode = currentNode.right;
			}else if(currentNode.data > value){
				currentNode = currentNode.left;
			}else if(currentNode.data == value){
				break;
			}
		}
		if(lastParent.data > value)
			lastParent.left = new AVLNode(value, lastParent);
		else if(lastParent.data < value)
			lastParent.right = new AVLNode(value, lastParent);

		currentNode = root;
		int currBal;
		while((currBal=getBalance(currentNode)) > 1||currBal < -1){
			int currRightBal = getBalance(currentNode.right);
			int currLeftBal = getBalance(currentNode.left);
			if(currBal < -1){
				if(currLeftBal <= 1&&currLeftBal >=-1){
					doRightRotation(currentNode);
					if(currentNode != root)
						currentNode = currentNode.parent;
				}else
					currentNode = currentNode.left;
			}else{
				if(currRightBal <= 1&&currRightBal >=-1){
					doLeftRotation(currentNode);
					if(currentNode != root)
						currentNode = currentNode.parent;
				}else
					currentNode = currentNode.right;
			}
		}
	}

	// Methode zum Loeschen einzelner Knoten (anhand des Zahlenwertes)
	public void deleteNode(int value) {
		// TODO...
	}

	// Darstellung zur Linksrotation:
	// (p = node.parent; n = node; r = node.right)
	// 		p				  p
	// 		|				  |
	// 		n		 =>		  r
	//		 \				 /
	//		  r				n
	// Die Methode liefert den Knoten zurueck, durch den 'node' ersetzt wurde.
	public AVLNode doLeftRotation(AVLNode node) {
		// TODO Bewerten
		AVLNode returnNode = new AVLNode(node.right.data, node.parent);
		returnNode.left =  new AVLNode(node.data, returnNode, node.left, node.right.left);
		returnNode.right = node.right.right;
		returnNode.right.parent = returnNode;
		return returnNode;
	}

	// Darstellung zur Rechtsrotation:
	// (p = node.parent; n = node; l = node.left)
	// 		  p				p
	// 		  |				|
	// 		  n		 =>		l
	//		 /				 \
	//		l				  n
	// Die Methode liefert den Knoten zurueck, durch den 'node' ersetzt wurde.
	public AVLNode doRightRotation(AVLNode node) {
		// TODO Bewerten
		AVLNode returnNode = new AVLNode(node.left.data, node.parent);
		returnNode.left =  node.left.left;
		returnNode.left.parent = returnNode;
		returnNode.right = new AVLNode(node.data, returnNode, node.left.right, node.right);
		return returnNode;
	}

	// Darstellung zur doppelten Rotation (Rechts-Links):
	// (p = node.parent; n = node; r = node.right; l = node.right.left)
	// 		p				p				  p
	// 		|				|				  |
	// 		n		=>		n		 =>		  l
	//		 \				 \				 / \
	//		  r				  l				n   r
	//		 /				   \
	//		l				    r
	// Die Methode liefert den Knoten zurueck, durch den 'node' ersetzt wurde.
	public AVLNode doDoubleRotationRightLeft(AVLNode node) {
		// TODO Bewerten
		AVLNode returnNode = new AVLNode(node.data, node.parent, node.left, node.right);
		returnNode.right = doRightRotation(returnNode.right);
		returnNode = doLeftRotation(returnNode);
		return returnNode;
	}

	// Darstellung zur doppelten Rotation (Rechts-Links):
	// (p = node.parent; n = node; l = node.left; r = node.left.right)
	// 		  p				    p				  p
	// 		  |				    |				  |
	// 		  n		  =>		n		 =>		  r
	//		 /				   /				 / \
	//		l				  r					l   n
	//		 \				 /
	//		  r				l
	// Die Methode liefert den Knoten zurueck, durch den 'node' ersetzt wurde.
	public AVLNode doDoubleRotationLeftRight(AVLNode node) {
		// TODO Bewerten
		AVLNode returnNode = new AVLNode(node.data, node.parent, node.left, node.right);
		returnNode.left = doLeftRotation(returnNode.left);
		returnNode = doRightRotation(returnNode);
		return returnNode;
	}

	/*********************************************************************************************************/
	/** Zusaetzliche Methoden zu Aufgabe 19 (Uebungsblatt09) *************************************************/
	/*********************************************************************************************************/

	// Diese Methode zeichnet den aktuell generierten Baum auf der durch
	// 'Graphics g' gegebenen Zeichenflaeche
	public void drawAVLTree(Graphics g, int x, int y) {
		drawAVLTree_rek(g, root, x, y, x);
	}

	private void drawAVLTree_rek(Graphics g, AVLNode n, int xPos, int yPos, int middle) {
		if (n != null) {
			n.drawNode(g, xPos, yPos);

			middle /= 2;
			int tmp;
			if (n.left != null) {
				tmp = xPos-middle;
				g.drawLine(xPos, yPos+5, tmp, yPos+35);
				drawAVLTree_rek(g, n.left, tmp, yPos+50, middle);
			}
			if (n.right != null) {
				tmp = xPos+middle;
				g.drawLine(xPos, yPos+5, tmp, yPos+35);
				drawAVLTree_rek(g, n.right, tmp, yPos+50, middle);
			}
		}
	}
}
