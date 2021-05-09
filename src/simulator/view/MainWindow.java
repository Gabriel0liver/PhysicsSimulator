package simulator.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
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
			
			
			
			
			StatusBar stBar= StatusBar(_ctrl);
			mainPanel.add(stBar,BorderLayout.PAGE_END);
		}
		// other private/protected methods
		// ...
	}
