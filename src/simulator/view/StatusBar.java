package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.model.Body;
import simulator.model.SimulatorObserver;
import simulator.control.Controller;

public class StatusBar extends JPanel implements SimulatorObserver {//hay que acabar.
// ...
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	private int n_bodies;
	StatusBar(Controller ctrl) {
		
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		
		_currTime = new JLabel();
		_currLaws = new JLabel();
		_numOfBodies = new JLabel();
		
		this.add(_currTime);
		this.add(_numOfBodies);
		this.add(_currLaws);
		
		
		// TODO complete the code to build the tool bar
	}
	// other private/protected methods
	// ...
	
	
	
	
	// SimulatorObserver methods
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_currTime.setText("Time:  "+time);
		_numOfBodies.setText("Bodies:  "+bodies.size());
		_currLaws.setText("Laws "+fLawsDesc);
	}
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		
		_currTime.setText("Time:  "+time);
		n_bodies=0;
		_numOfBodies.setText("Bodies:  "+n_bodies);
		_currLaws.setText("Laws "+fLawsDesc);
	}
	public void onBodyAdded(List<Body> bodies, Body b) {
		n_bodies++;
		_numOfBodies.setText("Bodies:  "+n_bodies);
	}
	public void onAdvance(List<Body> bodies, double time) {
		_currTime.setText("Time:  "+time);
		if(bodies.size() != n_bodies) {
			n_bodies = bodies.size();
		}
	}
	public void onDeltaTimeChanged(double dt) {
		
	}
	public void onForceLawsChanged(String fLawsDesc) {
		_currLaws.setText("Laws "+fLawsDesc);
	}
}
