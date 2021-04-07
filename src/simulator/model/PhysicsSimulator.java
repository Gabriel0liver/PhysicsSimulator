package simulator.model;

import java.lang.Exception;
import java.util.List;
import org.json.*;
import simulator.misc.Vector2D;


public class PhysicsSimulator {
		private double dt; 	//Tiempo real por paso
		private ForceLaws leyesFuerza;
		List<Body> cuerpos;
		
		
		PhysicsSimulator( ForceLaws l,List<Body> b) throws IllegalArgumentException{
			this.dt= 0.0;
			if(l== null) {
				throw new IllegalArgumentException("La ley de Fuerza es nulo ");
			}
			this.leyesFuerza= l;
			this.cuerpos= b;
		}
		
		
		
		
		public void Advance() {
			Body body;
			
			resetForce();
			this.leyesFuerza.apply(this.cuerpos);
			
			for(int i=0; i< this.cuerpos.size();i++) {
				body= this.cuerpos.get(i);
				body.move(dt);
			}
			
			this.dt++;
		}
		private void resetForce() {
			Vector2D clear= new Vector2D();
			Body body;
			
			for(int i=0; i< this.cuerpos.size();i++) {// Ponemos las fuerzas de todos los cuerpos a 0 para simplificar el calculo de sus fuerzas
				body= this.cuerpos.get(i);
				body.getForce().dot(clear);
			}
			
		}
		
		
		public void addBody(Body b) throws IllegalArgumentException{
			Body bs;
			for(int i=0; i< this.cuerpos.size();i++) {
				bs=this.cuerpos.get(i);
				
				if(bs.getId()== b.getId())
					throw new IllegalArgumentException("El Id está repetido ");
			}
			this.cuerpos.add(b);
		}
		
		
		public JSONObject getState() {
			JSONObject state= new JSONObject();
			JSONArray ja= new JSONArray();
			Body bs;
			
			state.put("time", this.dt);
			
			for(int i=0; i< this.cuerpos.size();i++) {
				bs=this.cuerpos.get(i);
				ja.put(bs.getState());
			}
			state.put("bodies", ja);
			
			return state;			
		}
		
		public String toString(){
			JSONObject json= getState();
			
			return  json.toString();			
		}
		
}
