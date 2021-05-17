package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws {
	private double g;
	private Vector2D c;
	
	public MovingTowardsFixedPoint(double g, Vector2D c){
		this.g=g;
		this.c=c;
	}
	
	public void apply(List<Body> bs) {
		for(int i=0;i<bs.size();i++) {
			Body b= bs.get(i);
			
			Vector2D d = new Vector2D(c.minus(b.getPosition()).direction());
			Vector2D f = new Vector2D(d.scale(b.getMass()*g));
			
			b.addForce(f);
		}
		
	}
	
	
	public String toString() {
		return "Moving towards "+c+" with constant acceleration "+g;
	}
	
	}
	

