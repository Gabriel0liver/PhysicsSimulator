package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	

	public MovingTowardsFixedPointBuilder(){
		this.type = "mtfp";
		this.desc = "body moves toward fixed point";
	}
	
	protected ForceLaws createTheInstance(JSONObject info) {
		
		try {
			Vector2D v = new Vector2D(0,0);
			JSONArray vectores = new JSONArray();
			JSONObject datos= info.getJSONObject("data");
			double g = 9.81;
			
			if(datos.has("c")) {
				vectores= datos.getJSONArray("c");
				v= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
			}
			if(datos.has("g")) {
				g = datos.getDouble("g");
			}
			
			return new MovingTowardsFixedPoint(g,v);
		}
			
			catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		
		datos.put("c","the point towards which bodies move (a json list of 2 numbers, e.g., [100.0,50.0])");
		datos.put("g", "the length of the acceleration vector (a number)");
		
		objeto.put("type","mtfp");
		objeto.put("desc","Moving towards a fixed point");
		objeto.put("data",datos);
		
		return objeto;
		
		
		
		
	}
}
