package Aufgabe_17;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI extends JFrame{

	private JMenuBar mb;
	private int size;
	private JMenu m;
	private JMenuItem start, step, save, load;
	private Game_Of_Life gofl;
	private int windowSize;
	private FileDialog fd;
	private MainPanel mainPanel;
	private FileWriter fw;
	private FileReader fr;
	private StringTokenizer st;

	public GUI(){
		super("Game of Life");
		size = (int)((Math.random()*20)+10);
		gofl = new Game_Of_Life(size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowSize = 600;
		this.setSize(windowSize+(size*2)+50, windowSize+(size*2)+50);
		mb = new JMenuBar();
		createGUI();
		mainPanel = new MainPanel(size, gofl, windowSize);
		this.getContentPane().add(mainPanel);
		this.setJMenuBar(mb);
		this.setPreferredSize(getSize());
		this.pack();
		this.setVisible(true);
	}

	private void createGUI() {
		m = new JMenu("Optionen");
		start = new JMenuItem("Neue Population");
		step = new JMenuItem("Nächste Populationsstufe");
		save = new JMenuItem("Speichern");
		load = new JMenuItem("Laden");
		start.addActionListener(new MenuActionListener());
		step.addActionListener(new MenuActionListener());
		save.addActionListener(new MenuActionListener());
		load.addActionListener(new MenuActionListener());
		m.add(start);
		m.add(step);
		m.add(save);
		m.add(load);
		mb.add(m);
		//All done
	}

	public class MenuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			if (e.getActionCommand() == start.getText()) {
				gofl.init();
				mainPanel.repaint();
			}
			if (e.getActionCommand() == step.getText()) {
				gofl.step();
				mainPanel.repaint();
			}
			if (e.getActionCommand() == save.getText()) {
				save();
			}
			if (e.getActionCommand() == load.getText()) {
				load();
				mainPanel.repaint();
			}

		}
	}

	private void load(){
		fd = new FileDialog(this,"Bitte wählen Sie eine Datei", FileDialog.LOAD);
		try {
			fd.setVisible(true);
			fr = new FileReader(fd.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		st = new StringTokenizer(fr.toString());
		size = (int)Math.sqrt(st.countTokens());
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (st.nextToken().equalsIgnoreCase("true")) {
					gofl.setField(true, i, j);
				}else {
					gofl.setField(false, i, j);
				}
			}
		}
	}


	private void save() {
		fd = new FileDialog(this,"Bitte wählen Sie einen Speicherort", FileDialog.SAVE);
		String temp = "";
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++) {
				temp += gofl.getField(i, j) + (j==size-1?"":" ");
			}
		}
		try {
			fd.setVisible(true);
			fw = new FileWriter(fd.getFile());
			fw.write(temp);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}