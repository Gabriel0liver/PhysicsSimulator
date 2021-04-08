package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	
	public MovingTowardsFixedPointBuilder(String type){
		super(type);
		
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
		
		
		
		
		
	}
}
