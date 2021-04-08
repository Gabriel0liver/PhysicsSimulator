package simulator.factories;

import java.util.List;
import org.json.JSONObject;

public abstract class Builder<T> 
{
	private String type= null;
	
	Builder(String type){
		this.type=type;
	}
	
	public T createInstance(JSONObject info)throws IllegalArgumentException {
		
		T obj=null;
	
		
		if(info.get("type")== this.type) {
			obj= createTheInstance(info);
		}
		else 
			return null;
		
		if(obj== null) {
			throw new IllegalArgumentException("No se ha conseguido crear el objeto"+ info.getDouble("type")+"/n");
		}
		return obj;
	}
	
	protected abstract T createTheInstance(JSONObject info); 
	
		
	public  JSONObject getBuilderInfo() {//supongo que el objetivo es crear un ejemplo de como tiene que ser la construcción de un objeto;
		JSONObject info= new JSONObject();;
		info= createData();
		
		
		return info;
		
	}
	
	protected abstract JSONObject createData();

	
	

}












