package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {
	
	private final double G;
	
	public NewtonUniversalGravitation(){
		this.G= 6.67E-11;
	}
	public void apply(List<Body> bs) {
		Body body;
		Body aux;
		Vector2D f;
		
		for(int i=0; i< bs.size();i++) {//introducimos las fuerzas
			
			body= bs.get(i);
			
			for(int j=i+1; j< bs.size();j++) {
				
				aux= bs.get(j);
				f=calcular_fuerza(body,aux);
				body.getForce().plus(f);		//reutilizamos el resultado de los dos cuerpos para no tener que recalcularlo
				aux.getForce().plus(f);
			}
			
		}
		
		//La aceleracion se calcula directamente en la clase body en el metodo move(double t) 
	}
	
	private Vector2D calcular_fuerza(Body b1,Body b2) {
		Vector2D force;
		double masa=b1.getMass();
		
		if(masa==0) {	//si la masa = 0 entonces la fuerza sera nula
			force = new Vector2D();
			return force;
		}
		
		Vector2D distancia = new Vector2D(b1.getPosition());
		double x,y;	//creo x e y para poder calcular la division del vector que hace de denominador
		
		distancia.dot(distancia);	//obtengo las variables necesarias para calcular la fuerza
		x= distancia.getX();
		y=distancia.getY();
		masa= masa *b2.getMass();
		
		x=G*masa/x;	//calculo la fuerza
		y=G*masa/y;
		force = new Vector2D(x,y);
		
		return force;
	}

	

}
