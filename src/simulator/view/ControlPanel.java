package simulator.view;

import simulator.control.Controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.swing.*;

import org.apache.commons.cli.ParseException;

import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{//page 7
	// ...

	private Controller _ctrl;
	private boolean _stopped;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		JToolBar toolBar= new JToolBar();
		
		addLoad(toolBar);
		toolBar.addSeparator();
		addFclaws(toolBar);
		toolBar.addSeparator();
		addRun(toolBar);
		toolBar.addSeparator();
		addStop(toolBar);
		toolBar.addSeparator();
		addExit(toolBar);
		toolBar.addSeparator();
		
		JSpinner steps = new JSpinner();
		JTextField  delta_time = new JTextField();
		
		
		this.add(toolBar);
	}
	
	
	//Buttons
	private void addLoad(JToolBar toolBar) {
		JButton load= new JButton();
		load.setToolTipText("Load a file");
		load.setActionCommand("load");
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fc= new JFileChooser();
				
				int x= fc.showOpenDialog(null);
				
				if (x == JFileChooser.APPROVE_OPTION) {
					File f =fc.getSelectedFile();
					_ctrl.reset();
					try  {
						_ctrl.loadBodies(new FileInputStream(f));
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
						//throw exception
					}
					
				}
				else {
					//trow exception.
				}
				
			}
			
		};
		
		load.addActionListener(al);		//evento
		load.setIcon(new ImageIcon("icons/open.png"));
		toolBar.add(load);
		
		
	}
	
	private void addFclaws(JToolBar toolBar) {//hay que hacer el evento.
		JButton Fclaws= new JButton();
		Fclaws.setToolTipText("Add force laws");
		Fclaws.setActionCommand("Fclaws");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		
		Fclaws.addActionListener(al);		//evento
		Fclaws.setIcon(new ImageIcon("icons/physics.png"));
		toolBar.add(Fclaws);
		
		
	}
	
	private void addRun(JToolBar toolBar) {
		JButton run = new JButton();
		run.setToolTipText("Run");
		run.setActionCommand("run");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i =0;i<toolBar.getComponentCount();i++) {//desactiva todos los botones. No estoy seguro si funciona.
					Component j = toolBar.getComponent(i);
					if(i != 3) {								//no desactiva el boton stop.
						j.setEnabled(false);
					}
				}
				
				_stopped= false;
				
				run_sim()
				
				
				
			}
			
		};
		run.addActionListener(al);		//evento
		run.setIcon(new ImageIcon("icons/run.png"));
		toolBar.add(run);
		
		
	}
	
	private void addStop(JToolBar toolBar) {
		JButton stop= new JButton();
		stop.setToolTipText("Stop");
		stop.setActionCommand("stop");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				for(int i =0;i<toolBar.getComponentCount();i++) {//activa todos los botones
					Component j = toolBar.getComponent(i);
					j.setEnabled(true);
					
				}
				
				
			}
			
		};
		stop.addActionListener(al);		//evento
		stop.setIcon(new ImageIcon("icons/stop.png"));
		toolBar.add(stop);
		
		
	}
	
	private void addExit(JToolBar toolBar) {
		JButton exit= new JButton();
		exit.setToolTipText("Exit");
		exit.setActionCommand("exit");
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
		exit.addActionListener(al);		//evento
		exit.setIcon(new ImageIcon("icons/exit.png"));
		toolBar.add(exit);
		
		
	}
	
	
private void Actions(ActionEvent e) {
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	};
}
	// other private/protected methods
	// ...
	
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
	} catch (Exception e) {
		// TODO show the error in a dialog box
		// TODO enable all buttons
		_stopped = true;
		return;
	}
			SwingUtilities.invokeLater( new Runnable() {
				@Override
	public void run() {
					run_sim(n-1);
				}
			});
		} else {
			_stopped = true;
			// TODO enable all buttons
		}
		}
	// SimulatorObserver methods
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		
	}
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		
	}
	public void onBodyAdded(List<Body> bodies, Body b) {
		
	}
	public void onAdvance(List<Body> bodies, double time) {
		
	}
	public void onDeltaTimeChanged(double dt) {
		
	}
	public void onForceLawsChanged(String fLawsDesc) {
		
	}

}
