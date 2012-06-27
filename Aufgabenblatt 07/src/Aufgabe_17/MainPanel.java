package Aufgabe_17;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	private int size, windowSize;
	private Game_Of_Life gofl;
	
	public MainPanel(int size, Game_Of_Life gofl, int windowSize){
		this.size = size;
		this.windowSize = windowSize;
		this.gofl = gofl;
	}
	
	public void paint(Graphics g){
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j ++) {
				if (gofl.getField(i, j)) {
					g.setColor(Color.GREEN);
				}else {
					g.setColor(Color.RED);
				}
				g.fillRect(i*(windowSize/size)+i+20, j*(windowSize/size)+j+10,(int)(windowSize/size), (int)(windowSize/size));
			}
		}
	}

}
