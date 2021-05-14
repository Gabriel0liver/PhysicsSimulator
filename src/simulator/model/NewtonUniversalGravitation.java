package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {
	
	private final double G;
	
	public NewtonUniversalGravitation(double g){
		this.G= g;
	}
	
	public void apply(List<Body> bs) {
		Body body;
		Body aux;
		Vector2D f;
		
		for(int i=0; i< bs.size();i++) {//introducimos las fuerzas
			
			body= bs.get(i);
			
			for(int j=0; j< bs.size();j++) {
				if(j != i) {
					aux= bs.get(j);
					f=calcular_fuerza(body,aux);
					body.addForce(f);
				}
			}
			
		}
		
		//La aceleracion se calcula directamente en la clase body en el metodo move(double t) 
	}
	
	private Vector2D calcular_fuerza(Body b1,Body b2) {
		Vector2D force;
		Vector2D direccion;
		double fuerza;
		double masa=b1.getMass();
		
		if(masa==0) {	//si la masa = 0 entonces la fuerza sera nula
			force = new Vector2D();
			return force;
		}
		
		Vector2D p1 = b1.getPosition();
		Vector2D p2 = b2.getPosition();
		
		double distancia = p1.distanceTo(p2);
		if(distancia == 0.0) {
			fuerza = 0.0;
		}else {
			masa = masa * b2.getMass();
			fuerza = G*(masa/(distancia*distancia));
		}
		

		
		direccion = p2.minus(p1).direction();
		
		return direccion.scale(fuerza);
	}
	
	public String toString() {
		return "Newton�s Universal Gravitation with G=- G ";
	}
	

}
