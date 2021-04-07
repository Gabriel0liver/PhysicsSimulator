package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint {
	private double g=9.81;
	
	MovingTowardsFixedPoint(){
		
	}
	public void apply(List<Body> bs) {
		Vector2D force;
		Vector2D f;
		
		for(int i=0;i<bs.size();i++) {
		Body b= bs.get(i);
		force= b.getForce();
		f = new Vector2D(b.getPosition());
		
		
		b.getPosition();
		f.scale(-g*b.getMass()); //ya que en el metodo body.move() la aceleracion calcula a= Fuerza/masa y como queremos que a= posicion*(-g) tenemos que anular la masa multiplicando la fuerza por su masa;
		force=
		
		}
	}
	
}
