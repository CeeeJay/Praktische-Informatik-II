// Abgabe von Ewald Bayer und Lukas Schmidtmann
// Ich schreibe diese Woche noch den Namen von Lukas mit drauf, auch wenn er (bedauerlicher Weise) nicht mitwirken konnte.
// Ich mache es jetzt nicht zu meiner Aufgabe, zu erklären warum, aber ich werde wahrscheinlich bald einen neuen Partner brauchen.

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JPanel;


public class Conway extends JPanel {

	int size;
	private boolean[][] creatures;

	public Conway(){
		super();
		this.init();
		this.setPreferredSize(new Dimension(this.size*20, this.size*20));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D)g;
		for (int i = 0; i < this.creatures.length; i++) {
			for (int j = 0; j < this.creatures.length; j++) {
				if(this.creatures[i][j])
					g2d.fillRect(i*20, j*20, 20, 20);
			}
		}
	}

	public void init(){
		this.size = (int)(Math.random() * 21 + 10);
		this.creatures = new boolean[size][size];
		int countCreatures = (int)(Math.random()*(0.75*size*size+1)+0.25*size*size);
		for (int i = 0; i < countCreatures; i++) {
			int j = (int)(Math.random()*size);
			int k = (int)(Math.random()*size);
			this.creatures[j][k] = true;
		}
	}

	public void nextGeneration(){
		boolean[][] nexGen = new boolean[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if(this.creatures[i][j]){
					int livingNeighbours = getLivingNeighbours(i, j);
					if(livingNeighbours > 3 || livingNeighbours < 2)
						nexGen[i][j] = false;
					else
						nexGen[i][j] = true;
				}else if(getLivingNeighbours(i, j) == 3)
					nexGen[i][j] = true;
			}
		}
		this.creatures = nexGen;
	}

	private int getLivingNeighbours(int x, int y){
		int livingNeighbours = 0;
		int[] directions = new int[4]; //0 = left; 1 = right; 2 = up; 3 = down; x,y = center
		directions[0] = x-1;
		directions[1] = x+1;
		directions[2] = y-1;
		directions[3]= y+1;
		for (int i = 0; i < directions.length; i++) {
			if(directions[i] < 0)
				directions[i] = this.size - 1;
			else if(directions[i] >= size)
				directions[i] = 0;
		}
		if(creatures[directions[0]][y])
			livingNeighbours++;
		if(creatures[directions[1]][y])
			livingNeighbours++;
		if(creatures[x][directions[2]])
			livingNeighbours++;
		if(creatures[x][directions[3]])
			livingNeighbours++;
		if(creatures[directions[0]][directions[2]])
			livingNeighbours++;
		if(creatures[directions[0]][directions[3]])
			livingNeighbours++;
		if(creatures[directions[1]][directions[2]])
			livingNeighbours++;
		if(creatures[directions[1]][directions[3]])
			livingNeighbours++;
		return livingNeighbours;
	}
	public void save() {
		FileDialog fd = new FileDialog(new Frame(), "Population speichern...", FileDialog.SAVE);
		fd.setVisible(true);
		String chosenDir = fd.getDirectory();
		String chosenFile = fd.getFile();
		if (chosenDir != null && chosenFile != null){
			String toFile = this.size + " ";
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					if(creatures[i][j])
						toFile += i + " " + j + " ";
				}
			}
			FileWriter fw;
			try {
				fw = new FileWriter(chosenDir+chosenFile);
				fw.write(toFile);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fd.dispose();
	}

	public void load() {
		FileDialog fd = new FileDialog(new Frame(), "Population speichern...", FileDialog.LOAD);
		fd.setVisible(true);
		String chosenDir = fd.getDirectory();
		String chosenFile = fd.getFile();
		if (chosenDir != null && chosenFile != null){
			String fromFile = "";
			FileReader fr;
			try {
				fr = new FileReader(chosenDir+chosenFile);
				int c;
				while((c = fr.read()) != -1)
					fromFile+=(char)c;
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringTokenizer st = new StringTokenizer(fromFile);
			this.size = Integer.valueOf(st.nextToken());
			this.creatures = new boolean[this.size][this.size];
			this.setPreferredSize(new Dimension(this.size*20, this.size*20));
			while(st.hasMoreTokens()){
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				creatures[x][y] = true;
			}
		}
		fd.dispose();
	}

}