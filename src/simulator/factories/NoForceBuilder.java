package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class NoForceBuilder extends Builder<ForceLaws>{
	
	public NoForceBuilder(){
		this.type = "nf";
	}
	
	protected NoForce createTheInstance(JSONObject info) {
		return new NoForce();
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		
		
		
		
		
	}
}
