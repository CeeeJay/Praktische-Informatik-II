import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Fenster extends JFrame{
	private Point2D[] menge;
	private Zeichnen z;
	public Fenster(Point2D[] menge){
		super("Kleinster umschlieﬂender Kreis - Bayer Schmidtmann");
		z = new Zeichnen();
		this.menge = menge;
		JMenuBar mb = new JMenuBar();
		mb.add(getDateiMenu());
		setJMenuBar(mb);
		if(menge!=null){
			for (int i = 0; i < menge.length; i++) {
				z.addShape(Zeichnen.punkt(menge[i]), Color.black);
			}
		}
		add(z);
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	private JMenu getDateiMenu(){
		JMenu m = new JMenu("Datei");
		JMenuItem mi = new JMenuItem("Generiere kleinsten umschlieﬂenden Kreis");
		JMenuItem mi2 = new JMenuItem("Beenden");
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Circle2D kreis = GeoMath.getMinCircumcircle(menge);
				z.addShape(Zeichnen.kreis(kreis), Color.red);
				z.drawLine(kreis.mittelpunkt, new Point2D(kreis.mittelpunkt.x, kreis.mittelpunkt.y + kreis.radius), Color.red);
				z.addShape(Zeichnen.punkt(kreis.mittelpunkt), Color.red);
				z.setKoordinaten(kreis.mittelpunkt);
				z.setKoordinaten2(new Point2D(kreis.mittelpunkt.x, kreis.mittelpunkt.y + (kreis.radius/2)), "r=" + kreis.radius);
			}
		});
		mi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		m.add(mi);
		m.addSeparator();
		m.add(mi2);
		return m;
	}
	private JMenu getAnsichtMenu(){
		
		return null;
	}
}
