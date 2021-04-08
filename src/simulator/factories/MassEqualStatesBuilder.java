package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{
	
	public MassEqualStatesBuilder(){
		this.type = "masseq";
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
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		
		
		objeto.put("type","masseq");
		objeto.put("data",datos);
		
		return objeto;
		
		
		
		
		
	}


}
