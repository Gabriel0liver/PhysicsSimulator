package simulator.factories;

import java.util.*;
import org.json.JSONObject;

public class BuilderBasedFactory <T> implements Factory<T>{
	
	private List<Builder<T>> builders;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders= builders;
	}
	
	public T createInstance(JSONObject info)throws IllegalArgumentException {
		
		T object=null;
		
			
		for(Builder<T> i : this.builders ) {
			object= i.createInstance(info);
			if(object!= null)
				return object;
		}
		throw new IllegalArgumentException("No se ha conseguido crear el objeto " + info.getString("type"));
		
	}
	
	public List<JSONObject> getInfo(){
		List<JSONObject> objetos= new ArrayList<JSONObject>();
		for(Builder<T> i : this.builders ) {
			objetos.add(i.getBuilderInfo());
		}
		
		return objetos;
	}

}
