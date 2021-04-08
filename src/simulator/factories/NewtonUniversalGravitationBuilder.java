package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.model.*;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	
	public NewtonUniversalGravitationBuilder(String type){
		type = "nlug";
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
		JSONObject datos= new JSONObject();
		
		datos.put("G",  6.67e10-11);
		objeto.put("type","nlug");
		objeto.put("data",datos);
		
		return objeto;
	}
	
}
