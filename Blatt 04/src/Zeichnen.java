import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Zeichnen extends JComponent{

	private String text;
	private Point2D koordinaten;
	private String text2;
	private Point2D koordinaten2;
	
	public Zeichnen(){
		this.shapes = new ArrayList<Shape>();
		this.colors = new ArrayList<Color>();
		koordinaten = new Point2D();
		koordinaten2 = new Point2D();
		text = "";
		text2 = "";
	}

	public Zeichnen(Point2D punkt){
		this.shapes = new ArrayList<Shape>();
		this.colors = new ArrayList<Color>();
		this.addShape(punkt(punkt), Color.black);
		koordinaten = new Point2D();
		koordinaten2 = new Point2D();
		text = "";
		text2 = "";
	}

	public Zeichnen(Circle2D kreis){
		this.shapes = new ArrayList<Shape>();
		this.colors = new ArrayList<Color>();
		this.addShape(kreis(kreis), Color.red);
		koordinaten = new Point2D();
		koordinaten2 = new Point2D();
		text = "";
		text2 = "";
	}

	//Von hier
	private ArrayList<Shape> shapes;
	private ArrayList<Color> colors;
	
	public void addShape(Shape shape, Color c) {
		this.shapes.add(shape);
		this.colors.add(c);
		repaint();
	}
	
	public void drawLine(Point2D p1, Point2D p2, Color c){
		this.shapes.add(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
		this.colors.add(c);
		repaint();
	}
	
	public void setKoordinaten(Point2D mittelpunkt){
		this.text = "(" + mittelpunkt.x + "|" + mittelpunkt.y + ")";
		this.koordinaten = new Point2D(mittelpunkt.x + 5, mittelpunkt.y + 5);
	}
	
	public void setKoordinaten2(Point2D punkt, String text){
		this.text2 = text;
		this.koordinaten2 = new Point2D(punkt.x + 5, punkt.y);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//dient dazu den Hintergrund zu säubern wie wir es vorher bereits mit
		//clearRect getan haben.
		super.paintComponent(g);
		//AWT/Swing bestimmt wann paintComponent aufgerufen wird, wir müssen
		//nun also überprüfen ob shape und color noch gar nicht gesetzt wurden
		for (int i = 0; i < shapes.size(); i++) {
			if(shapes.get(i)!=null){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(colors.get(i));
				g2d.draw(shapes.get(i));
			}
		}
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawString(text, (int)koordinaten.x, (int)koordinaten.y);
		g2d.drawString(text2, (int)koordinaten2.x, (int)koordinaten2.y);
	}
	//bis hier inspiriert aus: http://www.java-forum.org/awt-swing-swt/43939-zeichnen-swing-tutorial.html

	public static Shape kreis(Circle2D kreis){
		if(kreis.radius == 0){
			return punkt(kreis.mittelpunkt);
		}
		return new Ellipse2D.Double(kreis.mittelpunkt.x-kreis.radius, kreis.mittelpunkt.y-kreis.radius, kreis.radius*2, kreis.radius*2);
	}

	public static Shape punkt(Point2D punkt){
		return new Ellipse2D.Double(punkt.x-2, punkt.y-2, 4, 4);
	}
}
