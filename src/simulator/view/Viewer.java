package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ...
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	private static final Color azul=Color.BLUE;
	private static final Color rojo= Color.RED;
	private static final Color verde= Color.GREEN;
	private static final int radio=5;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		//  border with title
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),"Viewer",TitledBorder.LEFT, TitledBorder.TOP));
		
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_showVectors = true;
		this.addKeyListener(new KeyListener() {
			// ...
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					repaint();
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
				repaint();
				break;
				case '=':
					autoScale();
				repaint();
				break;
				case 'h':
					_showHelp = !_showHelp;
					repaint();
					break;
				case 'v':
					_showVectors = !_showVectors;
					repaint();
					break;
				default:
				}
	}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addMouseListener(new MouseListener() {
			// ...
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		
		
		
		}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// use gr to draw not g --- it gives nicer results
		
		
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		// TODO draw a cross at center
		gr.setColor(rojo);
		gr.drawLine(_centerX-3, _centerY, _centerX+3, _centerY);
		gr.drawLine(_centerX, _centerY-3, _centerX, _centerY+3);
		
		
		
		// TODO draw bodies (with vectors if _showVectors is true)
		Body b;
		for(int i =0; i<_bodies.size();i++) {
			b= _bodies.get(i);
			drawBodies(gr,b);
			
		}
		
		
		// TODO draw help if _showHelp is true

		if(_showHelp) {
			g.setColor(rojo);
			String s="h: toggle help, v: toggle vectors, +: zoom in; -: zoom out, =: fit /n";
			String scaling_ratio= "Scaling ratio: "+_scale;
			gr.drawString(s,10,27);
			gr.drawString(scaling_ratio,10,45);
		}
		
	}
	
	private void drawBodies(Graphics g,Body b) {
		double x,y;
		x= b.getPosition().getX();
		y= b.getPosition().getY();
		
		if(_showVectors==true) { //if you want to draw force/velocity, I'm not sure with the colors.
			
			int x2 = (int) ( _centerX + (int)(x/_scale) + (b.getForce().direction().scale(20).getX()));
			int y2= (int) ( _centerY - (int) (y/_scale) - (b.getForce().direction().scale(20).getY()));
			drawLineWithArrow(g,_centerX +(int)(x/_scale),_centerY-(int)(y/_scale),x2,y2,3,3,rojo,rojo);	
			
			x2 = (int) ( _centerX + (int)(x/_scale) + (b.getVelocity().direction().scale(20).getX()));
			y2= (int) ( _centerY - (int) (y/_scale) - (b.getVelocity().direction().scale(20).getY()));
			drawLineWithArrow(g,_centerX +(int)(x/_scale),_centerY-(int)(y/_scale),x2,y2,3,3,verde,verde);
			
		}
		
		g.setColor(azul);

		g.fillOval(_centerX + (int)(x/_scale)-5, _centerY - (int) (y/_scale)-5,10,10);

		g.setColor(Color.black);
		g.drawString(b.getId(),_centerX + (int)(x/_scale)-6 , _centerY - (int) (y/_scale)-10);

		
		
		
	}
	
	
	// other private/protected methods
	// ...
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector2D p = b.getPosition();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}
		
		double size = Math.max(1.0, Math.min(getWidth(), getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
		}

	
	
	// This method draws a line from (x1,y1) to (x2,y2) with an arrow.
	// The arrow is of height h and width w.
	// The last two arguments are the colors of the arrow and the line
	
	private void drawLineWithArrow(Graphics g, int x1, int y1,int x2, int y2, int w, int h, Color lineColor, Color arrowColor) {
		
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;
		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };
		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
		}
	
	
	
	
	
		// SimulatorObserver methods
		// ...
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies=bodies;
		autoScale();
		repaint();
		
	}
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies=bodies;
		autoScale();
		repaint();
		
	}
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies=bodies;
		autoScale();
		repaint();
		
	}
	public void onAdvance(List<Body> bodies, double time) {
		_bodies=bodies;
		repaint();
	}
	public void onDeltaTimeChanged(double dt) {
		
	}
	public void onForceLawsChanged(String fLawsDesc) {
		
	}
	
	
}


