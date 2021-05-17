package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class BodiesTable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BodiesTableModel modelo;
	private JTable eventos_tabla;


	BodiesTable(Controller ctrl) {
		modelo= new BodiesTableModel(ctrl);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),"Bodies",TitledBorder.LEFT, TitledBorder.TOP));
		setVisible(true);
		eventos_tabla= new JTable(modelo);
		eventos_tabla.setVisible(true);
		eventos_tabla.setFillsViewportHeight(true);
		this.add(new JScrollPane(eventos_tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		
	}
}
