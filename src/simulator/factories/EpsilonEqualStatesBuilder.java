package simulator.factories;

import org.json.JSONObject;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator> {

	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		
		datos.put("eps", 0.1);
		objeto.put("type","epseq");
		objeto.put("data",datos);
		
		return objeto;
		
		
		
		
		
	}
}
