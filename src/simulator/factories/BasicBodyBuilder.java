package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class BasicBodyBuilder extends Builder<Body> {
	
	
	public BasicBodyBuilder() {
		type = "basic";
	}
	
	

	protected Body createTheInstance(JSONObject info) {
		
		try {
		Vector2D v;
		Vector2D p;
		JSONObject datos= info.getJSONObject("data");
		JSONArray vectores = new JSONArray();
		
		vectores= datos.getJSONArray("v");
		v= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
		vectores= datos.getJSONArray("p");
		p= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
		
		Body objeto= new Body(datos.getString("id"),p,v,datos.getDouble("m"));
		return objeto;
		}
		catch(Exception e) {
			return null;
		}
		
		
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		JSONArray vectores = new JSONArray();
		
		
		datos.put("id","b1");
		vectores.put(0.0);
		vectores.put(0.0);
		datos.put("p",vectores);
		datos.put("v",vectores);
		datos.put("m", 5.97e24);
		
		objeto.put("type","basic");
		objeto.put("data",datos);
		
		return objeto;
		
	}
	

	
}
