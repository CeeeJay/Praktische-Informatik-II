//Abgabe von Ewald Bayer, Jan-Philipp Bamberger, Leon Peulings und Stephan Malzkorn

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class DrawTree extends JPanel{
	
	private Tree tree;
	
	public void setTree(Tree tree) {
		this.tree = tree;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		try{
			g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
			g2d.drawString(tree.getObject(), 150, 25);
			drawTheTree(g2d, tree, 150, 25);
		}catch(NullPointerException e){}
	}

	private void drawTheTree(Graphics2D g2d, Tree tree, int x, int y){
		int i = 30;
		int j = 30;
		if(tree.child1==null||tree.child2==null)
			return;
		if(tree.child1.getOptreeDepth() > 1)
			i = 60;
		g2d.drawLine(x, y, x-i+2, y+j-12);
		g2d.drawString(tree.child1.getObject(), x-i, y+j);
		drawTheTree(g2d, tree.child1, x-i, y+j);
		if(tree.child2.getOptreeDepth() > 1)
			i = 60;
		else
			i = 30;
		g2d.drawLine(x, y, x+i, y+j-12);
		g2d.drawString(tree.child2.getObject(), x+i, y+j);
		drawTheTree(g2d, tree.child2, x+i, y+j);
	}

}
