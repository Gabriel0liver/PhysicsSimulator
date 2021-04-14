package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.model.*;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	

	public NewtonUniversalGravitationBuilder(){
		this.type = "nlug";
		this.desc = "newtons law of universal gravitation";
	}

	protected NewtonUniversalGravitation createTheInstance(JSONObject info) {
		try {
			double g = 6.67e-11;
			JSONObject datos= info.getJSONObject("data");
			if(datos.has("G")) {
				g = datos.getDouble("G");
			}
			return new NewtonUniversalGravitation(g);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		datos.put("G",  6.67e10-11);
		objeto.put("type",this.type);
		objeto.put("desc",this.desc);
		objeto.put("data",datos);
		
		return objeto;
	}
	
}
