package simulator.control;


import java.io.*;
import org.json.*;
import simulator.model.*;
import simulator.factories.*;

public class Controller {
	private PhysicsSimulator simulator;
	private Factory<Body> factoria;
	
	Controller(PhysicsSimulator simulator,Factory<Body> factoria){
		this.simulator= simulator;
		this.factoria=factoria;
	}
	
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray array= jsonInupt.getJSONArray("bodies");
		Builder<Body> b= new Builder<Body>();
		
		for(int i=0;i<array.length();i++) {
			this.simulator.addBody(this.factoria.createInstance(array.getJSONObject(i)));
		}
			
		
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) {// hay que terminar la funcion
		for(int i=0; i<n;i++) {
			this.simulator.Advance();
		}
	}
}
