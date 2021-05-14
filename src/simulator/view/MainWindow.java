package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import extra.jtable.EventsTableModel;
import simulator.control.Controller;

public class MainWindow extends JFrame {
		
	
	
		private static final long serialVersionUID = 1L;
		// ...
		Controller _ctrl;
		BodiesTable tabla;
		
		
		public MainWindow(Controller ctrl) {
			super("Physics Simulator");
			_ctrl = ctrl;
			initGUI();
		}
		private void initGUI() {
			JPanel mainPanel = new JPanel(new BorderLayout());
			setContentPane(mainPanel);
			setBounds(200, 200, 700, 600);
			setVisible(true);
			
			// TODO complete this method to build the GUI
			
			ControlPanel Cpanel= new ControlPanel(_ctrl);
			mainPanel.add(Cpanel,BorderLayout.PAGE_START);
			
			
			
			//Table
			tabla= new BodiesTable(_ctrl);
			mainPanel.add(tabla,BorderLayout.NORTH);
			
			//viewer
			
			//status bar
			StatusBar stBar= new StatusBar(_ctrl);
			mainPanel.add(stBar,BorderLayout.PAGE_END);
		}
		// other private/protected methods
		// ...
	}
