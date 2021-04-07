package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public class BasicBodyBuilder<Body> extends Builder {
	
	
	
	
	public Body createInstance(JSONObject info)throws IllegalArgumentException {
		boolean encontrado= false;
		int i=0;
		Body obj;
		
		while(!encontrado && i<7 ) {
			if(info.get("type")=="basic")
				encontrado= true;
			i++;
		}
		
		if(!encontrado)
			return null;
		
		obj= fabricateObject(info);
		
		if(obj== null) {
			throw new IllegalArgumentException("No se ha conseguido crear el objeto"+ "basic"+"/n");
		}
		
		
		return obj;
		
	}
	
	private Body fabricateObject(JSONObject info) {
		
		
	} //si hay algún error, se devuelve un null.
		
	

	public List<JSONObject> getInfo(){
		
	}
}
