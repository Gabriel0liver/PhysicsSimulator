package simulator.model;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.*;
import simulator.misc.Vector2D;


public class PhysicsSimulator {
		private double dt; 	//Tiempo real por paso
		private double tiempoActual;
		private ForceLaws leyesFuerza;
		List<Body> cuerpos;
		List<SimulatorObserver> o;
		
		
		public PhysicsSimulator( ForceLaws l, double dt) throws IllegalArgumentException{
			if(dt < 1.0) {
				throw new IllegalArgumentException("Delta time invalid ");
			}
			this.dt= dt;
			if(l== null) {
				throw new IllegalArgumentException("La ley de Fuerza es nulo ");
			}
			this.leyesFuerza= l;
			this.tiempoActual = 0.0;
			this.cuerpos = new ArrayList<>();
			this.o = new ArrayList<>();
		}
		
		
		public void Advance() {
			Body body;
			
			for(int i=0; i< this.cuerpos.size();i++) {// Ponemos las fuerzas de todos los cuerpos a 0 para simplificar el calculo de sus fuerzas
				body= this.cuerpos.get(i);
				body.resetForce();
			}
			
			this.leyesFuerza.apply(this.cuerpos);
			
			for(int i=0; i< this.cuerpos.size();i++) {
				body= this.cuerpos.get(i);
				body.move(dt);
			}
			
			this.tiempoActual += this.dt;
			
			for(SimulatorObserver i: this.o) {
				
				i.onAdvance(cuerpos, tiempoActual);
			}
		}
		
		
		public void addBody(Body b) throws IllegalArgumentException{
			Body bs;		// este objeto compara todos los cuerpos del simulador con la clase b para que no hayan problemas
			for(int i=0; i< this.cuerpos.size();i++) {
				bs=this.cuerpos.get(i);
				
				if(bs.getId()== b.getId())
					throw new IllegalArgumentException("El Id esta repetido ");
			}
			this.cuerpos.add(b);
			
			for(SimulatorObserver i: this.o) {
				i.onBodyAdded(cuerpos, b);
			}
		}
		
		public void addObserver(SimulatorObserver o) {
			if(!this.o.contains(o)) {
				o.onRegister(this.cuerpos, this.tiempoActual, dt, this.leyesFuerza.toString());
				this.o.add(o);
				
			}
				
			
		}
		
		public void reset() {
			this.tiempoActual=0;
			
			for(SimulatorObserver i: this.o) {
				i.onReset(cuerpos, tiempoActual, dt, this.leyesFuerza.toString());
			}
		}
		
		//getters y setters
		
		public void setDeltaTime(double dt) {
			this.dt=dt;
			
			for(SimulatorObserver i: this.o) {
				i.onDeltaTimeChanged(this.dt);
			}
		}
		
		public void setForceLawsLaws(ForceLaws forceLaws)throws IllegalArgumentException {
			if(forceLaws == null) {
				throw new IllegalArgumentException("La ley de Fuerza es nulo ");
			}
			this.leyesFuerza=forceLaws;
			
			for(SimulatorObserver i: this.o) {
				i.onForceLawsChanged(this.leyesFuerza.toString());
			}
		}
		
		
		public JSONObject getState() {
			JSONObject state= new JSONObject();
			JSONArray bodies= new JSONArray();
			Body b;
			
			state.put("time", this.tiempoActual);
			
			for(int i=0; i< this.cuerpos.size();i++) {
				b=this.cuerpos.get(i);
				bodies.put(b.getState());
			}
			state.put("bodies", bodies);
			
			return state;			
		}
		
		public String toString(){
			JSONObject json= getState();
			return  json.toString();			
		}
		
}
