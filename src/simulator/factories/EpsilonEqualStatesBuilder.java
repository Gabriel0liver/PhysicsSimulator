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
		this.desc = "epsilon compare";
	}
	
	protected StateComparator createTheInstance(JSONObject info) {
		
		try {
		JSONObject datos= info.getJSONObject("data");
		double eps = 0.0;
		if(datos.has("eps")) {
			eps = datos.getDouble("eps");
		}
		EpsilonEqualStates objeto= new EpsilonEqualStates(eps);
		return objeto;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		
		datos.put("eps", 0.1);
		objeto.put("type",this.type);
		objeto.put("desc",this.desc);
		objeto.put("data",datos);
		
		return objeto;
	}
}
