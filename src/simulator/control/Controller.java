package simulator.control;


import java.io.*;
import org.json.*;
import simulator.model.*;
import simulator.factories.*;

public class Controller {
	private PhysicsSimulator simulator;
	private Factory<Body> factoria;
	
	public Controller(PhysicsSimulator simulator,Factory<Body> factoria){
		this.simulator= simulator;
		this.factoria=factoria;
	}
	
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray array= jsonInupt.getJSONArray("bodies");
		
		for(int i=0;i<array.length();i++) {
			this.simulator.addBody(this.factoria.createInstance(array.getJSONObject(i)));
		}
			
		
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws Exception  {
		PrintStream p = new PrintStream(out);
		
		JSONObject jsonEO;
		JSONArray expectedStates = null;
		if(expOut != null) {
			jsonEO = new JSONObject(new JSONTokener(expOut));
			expectedStates= jsonEO.getJSONArray("states");
		}
		
		p.println("{");
		p.println("\"states\": [");
		p.println(simulator.getState());
		for (int i = 0; i < n; i++) {
			if(expOut != null) {
				if(!cmp.equal(simulator.getState(), expectedStates.getJSONObject(i))) {
					throw new Exception("Diference in step: " + i);
				}
			}
			simulator.Advance();
			p.println(","+simulator.getState());
		}
		
		p.println("]");
		p.println("}");

	}
}
