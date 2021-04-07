package simulator.model;

import org.json.JSONObject;
import simulator.misc.*;
import java.lang.Double;


public class Body {//supongo que en la futura practica pasaremos al Vector3D y habra una clase madre llamada Vector
	protected String id;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected Double m;
	
	public Body(Vector2D v,Vector2D f,Vector2D p,Double m) {
		this.v= v; //velocidad
		this.f=f; //fuerza
		this.p=p; //posicion
		this.m= m; //masa
		
	}
	
	public void addForce(Vector2D f) {
		this.f.plus(f);
	}
	
	public void resetForce() {
		this.f.scale(0);
	}
	
	public void move(double t) {//t = tiempo
		
		//Si, se que se puede modular mas pero eso lo hare cuando haya acabado.
		
		
		
		
		Vector2D a; //aceleracion
		if(m!=0) {
			 a= new Vector2D((f.getX()/m),(f.getY()/m));
		}
		else {
			 a = new Vector2D();
		}
		
		p.plus(calcularPosicion(a,t));
		
		v.plus(a.scale(t));
		
	}
	private Vector2D calcularPosicion(Vector2D a,double t) {
		
		Vector2D v1= new Vector2D(this.v);
		Vector2D a1= new Vector2D(a);
		v1.scale(t);
		a1.scale((t*t)/2);
		
		return v1.plus(a1);
	}
	
	
	public JSONObject getState() {
		
	
		
		
		return new JSONObject();
	}
	
	public String toString() {
		
		return this.getState.toString();
	}
	
	//geters
	
	
	public String getId() {
		return id;
	}
	
	public Vector2D getVelocity() {
		
		return v;
	}
	
	public Vector2D getForce() {
		return f;
	}
	
	public Vector2D getPosition() {
		return p;
	}
	
	public double getMass() {
		return m;
	}
	
	//setters
	
	public void setMass(Double m) {
		this.m = m;
		
	}
	
	
	
	
}
