package simulator.model;

import org.json.JSONObject;
import simulator.misc.*;
import java.lang.Double;


public class Body {//supongo que en la futura practica pasaremos al Vector3D y habra una clase madre llamada Vector
	protected String id;
	protected Vector2D v;	//velocidad
	protected Vector2D f;	//Fuerza
	protected Vector2D p;	//posicion
	protected Double m;		//Masa
	

	public Body(String id,Vector2D p,Vector2D v,Double m) {
		this.id=id;
		this.v= v; //velocidad
		this.p=p; //posicion
		this.m= m; //masa
		this.f = new Vector2D(0,0);
	}
	
	public void addForce(Vector2D f) {
		this.f = this.f.plus(f);
	}
	
	public void resetForce() {
		this.f = this.f.scale(0);
	}
	
	public void move(double t) {//t = tiempo
		
		Vector2D a; //aceleracion
		if(m!=0) {
			 a= new Vector2D((f.getX()/m),(f.getY()/m));
		}
		else {
			 a = new Vector2D();
		}
		
		this.p = p.plus(calcularPosicion(a,t));
		
		this.v = v.plus(a.scale(t));
		
	}
	private Vector2D calcularPosicion(Vector2D a,double t) {
		
		Vector2D v1= new Vector2D(this.v);
		Vector2D a1= new Vector2D(a);
		v1 = v1.scale(t);
		a1 = a1.scale((t*t)/2);
		
		return v1.plus(a1);
	}
	
	
	public JSONObject getState() {
		JSONObject b= new JSONObject();
		
		b.put("id",this.id);
		b.put("m",this.m);
		b.put("p",this.p);
		b.put("v",this.v);
		b.put("f",this.f);
		
		return b;
	}
	
	public String toString() {
		JSONObject json= getState();
		
		return  json.toString();
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
