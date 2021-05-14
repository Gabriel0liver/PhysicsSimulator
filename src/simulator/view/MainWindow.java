package simulator.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
			
			// TODO complete this method to build the GUI
			ControlPanel Cpanel= new ControlPanel(_ctrl);
			mainPanel.add(Cpanel,BorderLayout.PAGE_START);
			
			
			
			//Table
			tabla= new BodiesTable(_ctrl);
			mainPanel.add(tabla,BorderLayout.NORTH);
			
			//viewer
			Viewer V= new Viewer(_ctrl);
			mainPanel.add(V,BorderLayout.CENTER);
			
			//status bar
			StatusBar stBar= new StatusBar(_ctrl);
			mainPanel.add(stBar,BorderLayout.PAGE_END);
		}
		// other private/protected methods
		// ...
	}
