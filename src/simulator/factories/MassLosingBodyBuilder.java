package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class MassLosingBodyBuilder extends Builder<MassLosingBody>{

	MassLosingBodyBuilder(String type){
		super(type);
	}
	
protected MassLosingBody createTheInstance(JSONObject info) {
		
	try {
		Vector2D v;
		Vector2D p;
		JSONObject datos= info.getJSONObject("data");
		JSONArray vectores = new JSONArray();
		
		vectores= datos.getJSONArray("v");
		v= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
		vectores= datos.getJSONArray("p");
		p= new Vector2D(vectores.getDouble(0),vectores.getDouble(1));
		
		MassLosingBody objeto= new MassLosingBody(datos.getString("id"),v,p,datos.getDouble("m"),datos.getDouble("freq"),datos.getDouble("factor"));
		
		return objeto;
	}
	catch(Exception e) {
		return null;
	}
	
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		
		
		
		
		
	}
	
}
