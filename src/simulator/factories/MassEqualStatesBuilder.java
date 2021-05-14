package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{
	
	public MassEqualStatesBuilder(){
		this.type = "masseq";
		this.desc = "compares mass";
	}
	
	protected StateComparator createTheInstance(JSONObject info) {
		
		try {
		return new MassEqualStates();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		
		
		objeto.put("type",this.type);
		objeto.put("desc",this.desc);
		objeto.put("data",datos);
		
		return objeto;
		
		
		
		
		
	}


}
