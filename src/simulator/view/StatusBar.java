package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.model.Body;
import simulator.model.SimulatorObserver;
import simulator.control.Controller;

public class StatusBar extends JPanel implements SimulatorObserver {
// ...
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	StatusBar(Controller ctrl) {
		
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		
		this.add(_currTime);
		this.add(_currLaws);
		this.add(_numOfBodies);
		
		
		
		
		// TODO complete the code to build the tool bar
	}
	// other private/protected methods
	// ...
	
	
	
	
	// SimulatorObserver methods
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		
	}
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		
		_currTime.setText("Time:  "+time);
		_numOfBodies.setText("Bodies:  ");
		_currLaws.setText("Laws "+fLawsDesc);
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
