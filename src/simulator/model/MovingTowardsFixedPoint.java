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
		Vector2D force;
		Vector2D di= new Vector2D(c);
		
		for(int i=0;i<bs.size();i++) {
		Body b= bs.get(i);
		force= b.getForce();
		di.minus(b.getPosition());
		force= di.scale(b.getMass()/this.g);
	
		}
		
		}
	
	
	public String toString() {
		return "Moving towards -c+\"with constant acceleration -g ";
	}
	
	}
	

