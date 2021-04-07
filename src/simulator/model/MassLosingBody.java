package simulator.model;


import simulator.misc.*;
import java.lang.Double;

public class MassLosingBody  extends Body{
	
	private Double lossFactor;
	private Double lossFrequency;
	private Double c;
	
	
	public MassLosingBody(Vector2D v,Vector2D f,Vector2D p,Double m,Double fac,Double fre) {
		super( v, f, p,m);
		lossFactor= fac;
		lossFrequency= fre;
		c= 0.0;
		
	}
	
	public void move(double t) {
		Double masa = 0.0;
		
		super.move(t);
		
		c+=t;
		
		if(c>=this.lossFrequency) {
			masa=this.getMass()*(1- this.lossFactor);
			this.setMass(masa); 
			
			c= 0.0;
		}
		
		
	}

}
