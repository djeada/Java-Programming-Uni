package Projekt;

import java.util.*;

import javax.swing.JPanel;

public class Particle extends JPanel{
	 // instance variables
    public int x, y;
    public double vx, vy;
    public final int radius = 15;
	
    // constructor
    public Particle() {
    	//setting default position
        x=(new Random()).nextInt(400);
		y=(new Random()).nextInt(400);
		
		//setting default velocity
        vx = 5*(new Random()).nextGaussian();
        vy = 5*(new Random()).nextGaussian();
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

	
    // move the ball one step
    public void move(AnimationPanel panel) {
    	if (x<radius)bounceOffVerticalWall(panel,radius);
        if (x>panel.getWidth()) bounceOffVerticalWall(panel,panel.getWidth()-radius);
    	if (y<radius)bounceOffVerticalWall(panel,radius);
        if (y>panel.getHeight()) bounceOffHorizontalWall(panel,panel.getHeight()-radius);
        
        x = x + (int)vx;
        y = y + (int)vy;
    }
    
    
}
