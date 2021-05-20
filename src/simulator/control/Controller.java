package simulator.control;


import java.io.*;
import java.util.List;

import org.json.*;
import simulator.model.*;
import simulator.factories.*;

public class Controller {
	private PhysicsSimulator simulator;
	private Factory<Body> factoria;
	Factory<ForceLaws> Fl;
	
	
	public Controller(PhysicsSimulator simulator,Factory<Body> factoria,Factory<ForceLaws> Fl){
		this.simulator= simulator;
		this.factoria=factoria;
		this.Fl=Fl;

	}
	
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray array= jsonInupt.getJSONArray("bodies");
		
		for(int i=0;i<array.length();i++) {
			this.simulator.addBody(this.factoria.createInstance(array.getJSONObject(i)));
		}
			
		
	}
	
	public void run(int n) throws Exception  {// Opcional hacer el Output stream pag 4 practica 2
		for (int i = 0; i < n; i++) {
			simulator.Advance();
		}
	}
	
	public void reset() {
		this.simulator.reset();
	}
	public void erase_simulator() {
		this.simulator.erase_simulator();
	}
	
	public void setDeltaTime(double dt) {
	this.simulator.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		this.simulator.addObserver(o);
	}
	
	public List<JSONObject> getForceLawsInfo(){
		return Fl.getInfo();
	}
	
	public void setForceLaws(JSONObject info) {
		simulator.setForceLawsLaws(Fl.createInstance(info));
	}
	
	
}
