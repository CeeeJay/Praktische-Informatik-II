//Abgabe von Ewald Bayer, Jan-Philipp Bamberger, Leon Peulings und Stephan Malzkorn

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Window extends JFrame {
	
	private JTextField infix;
	private JTextField postfix;
	private JLabel erg;
	private DrawTree tree;
	private JTextArea ta;
	
	public Window() {
		super("Stackrechner mit Bäumen");
		JPanel panel = new JPanel();
		tree = new DrawTree();
		infix = new JTextField();
		JButton calc = new JButton("Berechnen");
		postfix = new JTextField();
		erg = new JLabel();
		ta = new JTextArea(3,100);
	
		
		panel.setLayout(new GridLayout(2,2));
		postfix.setEditable(false);
		ta.setEditable(false);
		
		panel.add(infix);
		panel.add(calc);
		panel.add(postfix);
		panel.add(erg);
		add(ta, BorderLayout.SOUTH);
		add(panel, BorderLayout.NORTH);
		add(tree, BorderLayout.CENTER);
		
		calc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StackRechner r = new StackRechner();
				postfix.setText(r.infixToPostfix_Klammerung(infix.getText()));
				erg.setText(" = " + r.getValueOf_Klammerung(infix.getText()));
				r.generateTree(postfix.getText());
				tree.setTree(r.tree);
				tree.repaint();
				ta.setText(	"Inorder: " + r.tree.getInorder() 
							+ "\nPreorder: "+ r.tree.getPreorder()
							+ "\nPostorder: " + r.tree.getPostorder());
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
	}
	
}
