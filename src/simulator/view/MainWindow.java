package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


import simulator.control.Controller;

public class MainWindow extends JFrame {
		
	
	
		private static final long serialVersionUID = 1L;
		// ...
		Controller _ctrl;
		
		
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
			
			
			JPanel boxPanel = new JPanel();
			boxPanel.setLayout(new BoxLayout(boxPanel,BoxLayout.Y_AXIS));
			
			//Table
			BodiesTable tabla= new BodiesTable(_ctrl);
			boxPanel.add(tabla,CENTER_ALIGNMENT);
			
			//viewer
			Viewer V= new Viewer(_ctrl);
			V.setMinimumSize(new Dimension(200,100));
			V.setPreferredSize(new Dimension(400,600));
			boxPanel.add(V,CENTER_ALIGNMENT);
			
			mainPanel.add(boxPanel);
			
			//status bar
			StatusBar stBar= new StatusBar(_ctrl);
			mainPanel.add(stBar,BorderLayout.PAGE_END);
			
			this.setResizable(false);
			this.setSize(700, 800);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		// other private/protected methods
		// ...
	}
