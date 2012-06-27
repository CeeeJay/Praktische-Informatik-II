
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class GUI_Trees extends JFrame {
	
	private final static String titlePROG	= "Philipps-Universitaet Marburg (AVL-Baeume)";
	private AVLTree avlTree;

	private JPanel contPanel = new JPanel();
	private JPanel drawPanel = new JPanel();
	private JPanel funcPanel = new JPanel();
	private JPanel avlPanel	 = new JPanel();

	private JTextField fldAVLInput	= new JTextField();
	
	private JButton btnInsert	= new JButton("Knoten einfuegen");
	private JButton btnDelete	= new JButton("Knoten loeschen");
	private JButton btnClear	= new JButton("Baum loeschen");
	private JButton btnClose	= new JButton("Beenden");

	public GUI_Trees() {
		super(titlePROG);
		avlTree = new AVLTree();
		createGUI();
	}
	
	private void createGUI() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contPanel = (JPanel)getContentPane();

		btnInsert.setFocusPainted(false);
		btnDelete.setFocusPainted(false);
		btnClear.setFocusPainted(false);
		btnInsert.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(ActionEvent e) { insertNodeToAVLTree(); }});
		btnDelete.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(ActionEvent e) { deleteNodeToAVLTree(); }});
		btnClear.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(ActionEvent e) { clearAVLTree(); }});

		avlPanel.setLayout(new GridBagLayout());
		avlPanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder(
				BorderFactory.createEtchedBorder(Color.white, contPanel.getBackground().darker()), "AVL-Baeume"),
				BorderFactory.createEmptyBorder( 0, 0, 0, 0)));
		avlPanel.add(fldAVLInput, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		avlPanel.add(new JLabel("(als Eingabe werden nur Zahlenwerte"), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 4), 0, 0));
		avlPanel.add(new JLabel("akzeptiert - fuer jeden Knoten separat"), new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 4), 0, 0));
		avlPanel.add(new JLabel("Duplikate werden ignoriert)"), new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		avlPanel.add(btnInsert, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		avlPanel.add(btnDelete, new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		avlPanel.add(btnClear, new GridBagConstraints(0, 6, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		
		btnClose.setFocusPainted(false);
		btnClose.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); }});
		
		funcPanel.setLayout(new GridBagLayout());
		funcPanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder(
				BorderFactory.createEtchedBorder(Color.white, contPanel.getBackground().darker()), "Sonstiges"),
				BorderFactory.createEmptyBorder( 0, 0, 0, 0)));
		funcPanel.add(btnClose,		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));

		drawPanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder(
				BorderFactory.createEtchedBorder(Color.white, contPanel.getBackground().darker()), "Zeichenflaeche"),
				BorderFactory.createEmptyBorder( 0, 0, 0, 0)));
		
		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridBagLayout());
		combPanel.setBorder(BorderFactory.createEmptyBorder( 0, 0, 0, 2));
		combPanel.add(avlPanel,		new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 4, 0), 0, 0));
		combPanel.add(funcPanel,	new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		contPanel.setLayout(new BorderLayout());
		contPanel.setBorder(BorderFactory.createEmptyBorder( 4, 4, 2, 2));
		contPanel.add(combPanel,	BorderLayout.WEST);
		contPanel.add(drawPanel,	BorderLayout.CENTER);
		
		pack();
		setMinimumSize(new Dimension(900, getHeight()));
		setVisible(true);
	}
	
	private void insertNodeToAVLTree() {
		try { avlTree.insertNode(Integer.parseInt(fldAVLInput.getText())); }
		catch(NumberFormatException exp) {System.out.println("Nur Zahlenwerte sind als Eingabe zulaessig!");}
		fldAVLInput.setText("");
		updateDrawPanel();
	}
	
	private void deleteNodeToAVLTree() {
		try { avlTree.deleteNode(Integer.parseInt(fldAVLInput.getText())); }
		catch(NumberFormatException exp) {System.out.println("Nur Zahlenwerte sind als Eingabe zulaessig!");}
		fldAVLInput.setText("");
		updateDrawPanel();
	}
	
	private void clearAVLTree() {
		avlTree.clear();
		fldAVLInput.setText("");
		updateDrawPanel();
	}
	
	private void updateDrawPanel() {
		Graphics gr = drawPanel.getGraphics();
		drawPanel.update(gr);
		if (!avlTree.isEmpty()) avlTree.drawAVLTree(gr, drawPanel.getWidth()/2, Math.max(50, drawPanel.getHeight()/10));
	}

	@Override
    public void paint(Graphics g) {
        super.paint(g);
        updateDrawPanel();
    }

	public static void main(String[] args) {
		new GUI_Trees();
    }
}
