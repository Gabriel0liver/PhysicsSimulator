package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	

	public MovingTowardsFixedPointBuilder(){
		this.type = "mtcp";

	}
	
	protected ForceLaws createTheInstance(JSONObject info) {
		
		try {
			Vector2D v;
			JSONArray vectores = new JSONArray();
			
			vectores= info.getJSONObject("data").getJSONArray("c");
			v= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
			
			return new MovingTowardsFixedPoint(info.getJSONObject("data").getDouble("g"),v);
		}
			
			catch(Exception e) {
				return null;
			}
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		JSONArray vectores = new JSONArray();
		
		vectores.put(0.0);
		vectores.put(0.0);
		datos.put("c",vectores);
		datos.put("g", 9.81);
		
		objeto.put("type","mtcp");
		objeto.put("data",datos);
		
		return objeto;
		
		
		
		
	}
}
