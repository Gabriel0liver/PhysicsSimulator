package simulator.factories;

import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;
import simulator.model.*;

public class MassLosingBodyBuilder extends Builder<Body>{


	public MassLosingBodyBuilder(){
		this.type = "mlb";
		this.desc = "body that looses mass";

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
		System.out.println(e.getMessage());
		return null;
	}
	
	}
	
	protected  JSONObject createData() {
		JSONObject objeto= new JSONObject();
		JSONObject datos= new JSONObject();
		JSONArray vectores = new JSONArray();
		
		
		datos.put("id","b1");
		vectores.put(-3.5e10);
		vectores.put(0.0);
		datos.put("p",vectores);
		vectores.remove(0);
		vectores.remove(1);
		vectores.put(0.0);
		vectores.put(1.4e03);
		datos.put("v",vectores);
		datos.put("m", 5.97e24);
		datos.put("freq", 1e3);
		datos.put("factor", 1e-3);
		
		objeto.put("type",this.type);
		objeto.put("desc",this.desc);
		objeto.put("data",datos);
		
		return objeto;
	}
	
}
