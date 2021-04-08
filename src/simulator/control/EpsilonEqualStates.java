package simulator.control;

import org.json.*;
import simulator.misc.*;

public class EpsilonEqualStates implements StateComparator{
	private double eps;
	
	public EpsilonEqualStates(double eps){
		this.eps=eps;
	}

	public boolean equal(JSONObject s1, JSONObject s2) {
		
		JSONArray ar1= s1.getJSONArray("bodies");
		JSONArray ar2= s2.getJSONArray("bodies");
		JSONObject b1,b2;
		
		int i=0;
		boolean keys= true,list=true;
		
		if(s1.getDouble("time")!=s2.getDouble("time"))
			keys=true;
		if(ar1.length()!= ar2.length())
			list=false;
		
		while(list && i<ar1.length() ){
			b1= ar1.getJSONObject(i);
			b2= ar2.getJSONObject(i);
			
			if(b1.getBoolean("id")!=b2.getBoolean("id") || b1.getBoolean("mass")!=b2.getBoolean("mass"))
				list=false;
			
			compararAtributos(b1,b2);
			
			
			i++;
		}
		
		return keys&& list;
	}
	private boolean compararAtributos(JSONObject s1, JSONObject s2) { 
		Vector2D v1,v2;
		String[] s= {"v","f","p"} ;
		double m1= s1.getDouble("m"),m2= s2.getDouble("m");
	
		if(s1.get("id")!=s1.get("id"))
			return false;
		
		if(Math.abs(m1-m2)>this.eps)
			return false;
		
		for(int i=0;i<3;i++){
			//pasar el json object a Vector2D
			v1= (Vector2D) s1.get(s[i]);
			v2= (Vector2D) s2.get(s[i]);
			
			if(v1!=v2) {
				return false;
			}			
		}
		return true;
	}
}
