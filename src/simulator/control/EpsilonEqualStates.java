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
			keys=false;
		if(ar1.length()!= ar2.length())
			list=false;
		
		while(list && i<ar1.length() ){
			b1= ar1.getJSONObject(i);
			b2= ar2.getJSONObject(i);

			list = compararAtributos(b1,b2);
			i++;
		}
		
		return keys&& list;
	}
	private boolean compararAtributos(JSONObject s1, JSONObject s2) { 
		String[] s= {"v","f","p"} ;
		double x,y;
		double m1= s1.getDouble("m"),m2= s2.getDouble("m");
		JSONArray a1= new JSONArray();
		JSONArray a2= new JSONArray();
	
		if(!s1.get("id").equals(s1.get("id")))
			return false;
		
		if(Math.abs(m1-m2)>this.eps)
			return false;
		
		for(int i=0;i<3;i++){
			a1 = s1.getJSONArray(s[i]);
			a2 = s2.getJSONArray(s[i]);
			
			Vector2D v1 = new Vector2D(a1.getDouble(0),a1.getDouble(1));
			Vector2D v2 = new Vector2D(a2.getDouble(0),a2.getDouble(1));
			
			if(v1.distanceTo(v2) > this.eps) {
				System.out.println("Diference in "+s[i]);
				System.out.println("Vectors: " + v1 + "  " + v2);
				System.out.println("Distance:  " +v1.distanceTo(v2));
				return false;
			}			
		}
		return true;
	}
}