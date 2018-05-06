package Projekt;

import java.awt.Rectangle;
import java.util.*;

import javax.swing.JPanel;

public class Particle extends JPanel{
	
	// instance variables
    public int x, y;
    public double vx, vy;
    public int radius;
    public double mass;
    
    // constructor
    public Particle(int x, int y, double vx, double vy, int r, double m) {
        this.vx = vx;
        this.vy = vy;
        this.x = x;
        this.y = y;
        this.radius = r;
        this.mass = m;
    }
	
    // bounce of vertical wall by reflecting x-velocity
    private void bounceOffVerticalWall(AnimationPanel panel, int distance) {
        vx = -vx;
        x=distance;
    }

    // bounce of horizontal wall by reflecting y-velocity
    private void bounceOffHorizontalWall(AnimationPanel panel, int distance) {
        vy = -vy;
        y=distance;
    }
	
    // reverse the direction of the particle whenever it reaches one of the four walls
    public void wallTest(AnimationPanel panel) {
    	if (x<radius)bounceOffVerticalWall(panel,radius);
        if (x>panel.getWidth()-4*radius) bounceOffVerticalWall(panel,panel.getWidth()-4*radius);
    	if (y<radius)bounceOffHorizontalWall(panel,radius);
        if (y>panel.getHeight()-4*radius) bounceOffHorizontalWall(panel,panel.getHeight()-4*radius);
    }

    // creates a bounding rectangle for collision checking
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 2*this.radius,  2*this.radius);
    }
    
 	public void collisionTest(AnimationPanel panel) {
 		
 	// this rectangle surrounds the particle that will be checked against
 	// all of the other particles below
 		
    		Rectangle particleToCheck = this.getBounds();
    	
    		// cycle through all the other particles and check if they
    		// cross over the rectangle created above
    		for(Particle p: panel.particles){
    			
    			// creates a bounding rectangle that is used temporarily
    			// for each other rock on the board
    			
    			Rectangle otherParticle = p.getBounds();
    			
    			// check to make sure I'm not comparing one particle to itself
    			// check if one particle crosses over another particle
    			
    			if(p != this && otherParticle.intersects(particleToCheck)){

    				//switch the direction the particle are moving on impact
    				//equations for post-collision velocity for two particles in one dimension, 
        			//based on masses and initial velocities:
    				double dvx = (vx*(mass-p.mass)+2*p.mass*p.vx)/(mass+p.mass);
    				double dvy = (vy*(mass-p.mass)+2*p.mass*p.vy)/(mass+p.mass);
    				double pdvx = (p.vx*(p.mass-mass)+2*mass*vx)/(mass+p.mass);
    				double pdvy = (p.vy*(p.mass-mass)+2*mass*vy)/(mass+p.mass);
    				vx = dvx;
    				vy = dvy;
    				p.vx = pdvx;
    				p.vy = pdvy;
    				
    			}
    	}
    }
    
    // move the particle one step
    public void move() {
    	 x = x + (int)vx;
    	 y = y + (int)vy;
    }
    
    
}
