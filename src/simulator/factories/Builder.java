package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public class Builder<T> implements Factory<T> {
	
	public Builder (){
		
	}
	
	public T createInstance(JSONObject info)throws IllegalArgumentException {
		String[] s= { "basic","mlb", "nlug","mtcp", "nf","masseq", "epseq"} ;
		boolean encontrado= false;
		int i=0;
		T obj;
		
		while(!encontrado && i<7 ) {
			if(info.get("type")==s[i])
				encontrado= true;
			i++;
		}
		
		if(!encontrado)
			return null;
		
		obj= fabricateObject(info);
		
		if(obj== null) {
			throw new IllegalArgumentException("No se ha conseguido crear el objeto"+ s[i]+"/n");
		}
		
		
		return obj;
		
	}
	
	private T fabricateObject(JSONObject info) {
		
	} //si hay algún error, se devuelve un null.
		
	

	public List<JSONObject> getInfo(){
		
	}
	

}
