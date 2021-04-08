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
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) {
		JSONObject output = new JSONObject();
		JSONArray states = new JSONArray();
		
		JSONObject jsonEO;
		JSONArray expectedStates = null;
		if(expOut != null) {
			jsonEO = new JSONObject(new JSONTokener(expOut));
			expectedStates= jsonEO.getJSONArray("states");
		}
		
		
		states.put(simulator.getState());
		for (int i = 0; i < n; i++) {
			if(expOut != null) {
				cmp.equal(simulator.getState(), expectedStates.getJSONObject(i));
				//FALTA LANZAR EXCEPCION
			}
			simulator.Advance();
			states.put(simulator.getState());
		}

		output.put("states", states);

		try {
			out.write(output.toString().getBytes());
		} catch (IOException a) {
			System.err.println("Error en escritura de salida");
		}
	}
}
