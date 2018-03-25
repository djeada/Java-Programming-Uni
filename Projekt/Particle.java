package Projekt;

import java.util.*;

import javax.swing.JPanel;

public class Particle extends JPanel{
	// instance variables
    public int x, y;
    public double vx, vy;
    public int radius;
    public double mass;
	
    // constructor for smaller particles
    public Particle(int r, double m) {
    	//setting default position
        x=(new Random()).nextInt(950)+20;
		y=(new Random()).nextInt(500)+10;
		
		//setting default velocity
        vx = (new Random()).nextGaussian() + (new Random()).nextInt(5);
        vy = (new Random()).nextGaussian() + (new Random()).nextInt(5);
        
        this.radius = r;
        this.mass = m;
    }
    
    // constructor for the big particle
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

    public void collisionTest(AnimationPanel panel) {
    	
    
    	// Cycle through all the other particles and check if they
    	// cross over the rectangle created above
    	for(Particle p: panel.particles){
    		
    		// Check to make sure we are not comparing one particle to itself
    		// Check if one particle crosses over another particle
    		if(p != this && this.x + 2*this.radius >= p.x && this.x <= p.x + 2*p.radius && this.y + 2*this.radius >= p.y && this.y <= p.y + 2*p.radius){
    			//Equations for post-collision velocity for two particles in one dimension, 
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
    
    // move the ball one step
    public void move() {
    	 x = x + (int)vx;
    	 y = y + (int)vy;
    }
    
    
}
