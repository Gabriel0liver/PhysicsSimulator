package simulator.control;

import org.json.*;

public class MassEqualStates implements StateComparator{

	
	public boolean equal(JSONObject s1, JSONObject s2) {
		JSONArray a1= s1.getJSONArray("bodies");
		JSONArray a2= s2.getJSONArray("bodies");
		JSONObject b1;
		JSONObject b2;
		int i=0;
		boolean keys= true,list=true;
		
		if(s1.getDouble("time")!=s2.getDouble("time"))
			keys=true;
		if(a1.length()!= a2.length())
			list=false;
		
		while(list && i<a1.length() ){
			b1= a1.getJSONObject(i);
			b2= a2.getJSONObject(i);
			
			if(!b1.get("id").equals(b2.get("id")) || b1.getDouble("m")!= b2.getDouble("m")) {
				System.out.println("Id: " + b1.get("id"));
				System.out.println("Mass " + b1.getDouble("m") + " should be " + b2.getDouble("m"));
				list=false;
			}
			
			
			i++;
		}
		
		return keys&& list;
	}

}
