package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.model.*;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	
	public NewtonUniversalGravitationBuilder(String type){
		super(type);
	}

	protected NewtonUniversalGravitation createTheInstance(JSONObject info) {
		try {
		return new NewtonUniversalGravitation();
		}
		catch(Exception e) {
			return null;
		}
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		
		
		
		
		
	}
	
}
