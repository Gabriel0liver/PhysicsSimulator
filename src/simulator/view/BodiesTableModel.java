package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	// ...
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames= {"Id","Mass","Position","Velocity","Force"};
	private List<Body> _bodies;
	
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	@Override
	public int getRowCount() {
	// TODO complete
		return _bodies.size();
	}
	@Override
	public int getColumnCount() {
		// TODO complete
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
		
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Body b= _bodies.get(columnIndex);
		
		if(columnIndex==0) {
			return b.getId();
		}
		else if(columnIndex==1) {
			return b.getMass();
		}
		else if(columnIndex==2) {
			return b.getPosition();
		}
		else if(columnIndex==3) {
			return b.getVelocity();
		}
		else if(columnIndex==4) {
			return b.getForce();
		}
		else {
			assert(false);
			return null;
		}
		
	}
	
	// SimulatorObserver methods
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies=bodies;
		 fireTableStructureChanged();
		
	}
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies=bodies;
		 fireTableStructureChanged();
	}
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies=bodies;
		 fireTableStructureChanged();
	}
	public void onAdvance(List<Body> bodies, double time) {
		_bodies=bodies;
		 fireTableStructureChanged();
	}
	public void onDeltaTimeChanged(double dt) {
		
	}
	public void onForceLawsChanged(String fLawsDesc) {
		
	}
}

