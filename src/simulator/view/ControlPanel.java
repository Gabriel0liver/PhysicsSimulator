package simulator.view;

import simulator.control.Controller;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
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
	private FLDialog _dialog;

	private JButton load;
	private JButton fclaws;
	private JButton run;
	private JButton stop;
	private JButton exit;
	private JButton reset;
	private JSpinner steps;
	private JTextField time;
	
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		JToolBar toolBar= new JToolBar();
		
		
		addLoad(toolBar);
		toolBar.addSeparator();
		addFclaws(toolBar);
		toolBar.addSeparator();
		addRun(toolBar);
		toolBar.addSeparator();
		addStop(toolBar);
		toolBar.addSeparator();
		
		
		JLabel stepText = new JLabel("Steps:");
		JLabel timeText = new JLabel("Delta-Time:");
		steps = new JSpinner();
		steps.setValue(10000);
		steps.setToolTipText("Simulation, insert number of steps: 1-10000");
		time = new JTextField("25000");
		toolBar.add(stepText);
		toolBar.addSeparator();
		toolBar.add(steps);
		toolBar.addSeparator();
		toolBar.add(timeText);
		toolBar.addSeparator();
		toolBar.add(time);
		toolBar.addSeparator();
		addExit(toolBar);
		toolBar.addSeparator();
		addReset(toolBar);
		
		this.add(toolBar);
	}
	
	
	//Buttons
	private void addLoad(JToolBar toolBar) {
		load= new JButton();
		load.setToolTipText("Load a file");
		load.setActionCommand("load");
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fc= new JFileChooser();
				
				int x= fc.showOpenDialog(null);
				
				if (x == JFileChooser.APPROVE_OPTION) {
					File f =fc.getSelectedFile();
					_ctrl.erase_simulator();
					try  {
						_ctrl.loadBodies(new FileInputStream(f)); //carga los cuerpos
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
						//throw exception
					}
					
					
				}
				
			}
			
		};
		
		load.addActionListener(al);		//evento
		load.setIcon(new ImageIcon("./resources/icons/open.png"));
		toolBar.add(load);
		
		
	}
	
	private void addFclaws(JToolBar toolBar) {//hay que hacer el evento.
		fclaws= new JButton();
		fclaws.setToolTipText("Add force laws");
		fclaws.setActionCommand("Fclaws");
		JPanel that = this;
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (_dialog == null) {
					_dialog = new FLDialog((Frame) SwingUtilities.getWindowAncestor(that),_ctrl);
				}
				_dialog.open();
				
			}
			
		};
		
		
		fclaws.addActionListener(al);		//evento
		
		fclaws.setIcon(new ImageIcon("./resources/icons/physics.png"));
		toolBar.add(fclaws);
		
	}
	
	private void addRun(JToolBar toolBar) {
		run = new JButton();
		run.setToolTipText("Run");
		run.setActionCommand("run");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				load.setEnabled(false);
				fclaws.setEnabled(false);
				exit.setEnabled(false);
				//reset.setEnabled(false);
				_stopped= false;
				_ctrl.setDeltaTime(Double.parseDouble(time.getText()));
				run_sim((Integer)steps.getValue());
			}
			
		};
		run.addActionListener(al);		//evento
		run.setIcon(new ImageIcon("./resources/icons/run.png"));
		toolBar.add(run);
		
		
	}
	
	private void addStop(JToolBar toolBar) {
		stop= new JButton();
		stop.setToolTipText("Stop");
		stop.setActionCommand("stop");
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = true;
			}
			
		};
		stop.addActionListener(al);		//evento
		stop.setIcon(new ImageIcon("./resources/icons/stop.png"));
		toolBar.add(stop);
		
		
	}
	
	private void addExit(JToolBar toolBar) {
		exit= new JButton();
		exit.setToolTipText("Exit");
		exit.setActionCommand("exit");
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		};
		exit.addActionListener(al);		//evento
		exit.setIcon(new ImageIcon("./resources/icons/exit.png"));
		toolBar.add(exit);
		
		
	}

	private void addReset(JToolBar toolBar){
		reset=new JButton("Reset");
		reset.setActionCommand("Reset");
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.reset();
			}
			
		};
		
		reset.addActionListener(al);
		toolBar.add(reset);
		
	}
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
			}catch (Exception e) {
				// TODO show the error in a dialog box
				// TODO enable all buttons
				load.setEnabled(true);
				fclaws.setEnabled(true);
				exit.setEnabled(true);
				System.out.println(e);
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
			
		}else{
			_stopped = true;
			load.setEnabled(true);
			fclaws.setEnabled(true);
			exit.setEnabled(true);
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
