package simulator.factories;


import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;
import simulator.misc.Vector2D;
import simulator.model.Body;


public class EpsilonEqualStatesBuilder extends Builder<StateComparator> {
	
	public EpsilonEqualStatesBuilder() {
		this.type = "epseq";
	}
	
	protected StateComparator createTheInstance(JSONObject info) {
		
		try {
		JSONObject datos= info.getJSONObject("data");
		EpsilonEqualStates objeto= new EpsilonEqualStates(datos.getDouble("eps"));
		return objeto;
		}
		catch(Exception e) {
			return null;
		}
		
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		
		datos.put("eps", 0.1);
		objeto.put("type","epseq");
		objeto.put("data",datos);
		
		return objeto;
	}
}
