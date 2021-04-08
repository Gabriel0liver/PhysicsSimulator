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
		}
		
		
		public void addBody(Body b) throws IllegalArgumentException{
			Body bs;
			for(int i=0; i< this.cuerpos.size();i++) {
				bs=this.cuerpos.get(i);
				
				if(bs.getId()== b.getId())
					throw new IllegalArgumentException("El Id esta repetido ");
			}
			this.cuerpos.add(b);
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
