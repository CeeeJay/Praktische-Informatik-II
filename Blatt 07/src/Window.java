// Abgabe von Ewald Bayer und Lukas Schmidtmann

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Window extends JFrame implements Runnable, ActionListener{

	private Conway conway;
	private boolean animate;

	public Window(){
		super("Conways Spiel des Lebens");

		JMenuBar mb = new JMenuBar();
		mb.add(getDateiMenu());
		mb.add(getPopulationMenu());
		mb.add(getQMenu());
		setJMenuBar(mb);		

		conway = new Conway();
		add(conway);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Thread animation = new Thread(this);
		animate = false;
		animation.start();

		setVisible(true);
	}

	private JMenu getDateiMenu(){
		JMenu m = new JMenu("Datei");
		JMenuItem mi = new JMenuItem("Neue Population");
		JMenuItem mi2 = new JMenuItem("Population speichern...");
		JMenuItem mi3 = new JMenuItem("Population laden...");
		JMenuItem mi4 = new JMenuItem("Beenden");
		mi.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		m.add(mi);
		m.addSeparator();
		m.add(mi2);
		m.add(mi3);
		m.addSeparator();
		m.add(mi4);
		return m;
	}
	
	private JMenu getPopulationMenu(){
		JMenu m = new JMenu("Population");
		JMenuItem mi = new JMenuItem("Nächste Generation");
		JMenuItem mi2 = new JMenuItem("Animation starten/stoppen");
		mi.addActionListener(this);
		mi2.addActionListener(this);
		m.add(mi);
		m.add(mi2);
		return m;
	}
	
	private JMenu getQMenu(){
		JMenu m = new JMenu("?");
		JMenuItem mi = new JMenuItem("About");
		mi.addActionListener(this);
		m.add(mi);
		return m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Neue Population")){
			animate = false;
			conway.init();
			conway.setPreferredSize(new Dimension(conway.size*20, conway.size*20));
			repaint();
			pack();
		}
		if(cmd.equals("Population speichern...")){
			animate = false;
			conway.save();
		}
		if(cmd.equals("Population laden...")){
			animate = false;
			conway.load();
			repaint();
			pack();
		}
		if(cmd.equals("Beenden"))
			if(JOptionPane.showOptionDialog(null, "Wirklich beenden?", "Ich frage mal besser noch mal...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION)
				System.exit(0);
		if(cmd.equals("Nächste Generation")){
			conway.nextGeneration();
			repaint();
		}			
		if(cmd.equals("Animation starten/stoppen"))
			animate = !animate;
		if(cmd.equals("About"))
			JOptionPane.showMessageDialog(null, text);
	}	
	
	@Override
	public void run() {
		while(true){
			if(animate){
				conway.nextGeneration();
				repaint();
			}
			try {
				Thread.sleep(168);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	private String text = 	"Dieses Programm stammt von Ewald Bayer.\n\n" +
							"\"Ich kann es zwar am wenigsten wissen,\n" +
							"aber ich bin mir fast sicher, dass irgendwo\n" +
							"in der Windows Lizenzvereinbarung\n" +
							"steht, dass die Seele deines Erstgeborenen\n" +
							"Bill Gates gehört, wenn man ihr zustimmt.\"";
	
}
