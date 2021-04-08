package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{
	public MassEqualStatesBuilder(String type){
		super(type);
		
	}
	
	protected StateComparator createTheInstance(JSONObject info) {
		
		try {
		new MassEqualStates();
		}
		catch(Exception e) {
			return null;
		}
		
		return null;
	}

}
