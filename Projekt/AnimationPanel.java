package Projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class AnimationPanel extends JComponent { 
		
		int width = this.getWidth();
		int height = this.getHeight();
		static int N = 100;
		Color bgColor = Color.white;
		Color bigColor = Color.red;
		Color smallColor = Color.green;
		static int Radius = 50, radius = 5;
		static int Mass = 100, mass = 10;
		int x = 200, y = 100;
		double vx = 1.0, vy = 1.0;
		double min = 0.5, max = 2;
		Random r = new Random();
		Graphics2D graphicSettings;
			
		//holds every particle we create
		public ArrayList<Particle> particles = new ArrayList<Particle>();
		
		//creates n Particle objects and stores them in the ArrayList
		public AnimationPanel() { 
			populate ();
		} 
		
		//holds all graphics settings
		public void paint(Graphics g) { 
			
			// Allows me to make many settings changes in regards to graphics
			graphicSettings = (Graphics2D)g; 
			
			// Draw a black background that is as big as the game board
			graphicSettings.setColor(bgColor);
			graphicSettings.fillRect(0, 0, getWidth(), getHeight());
			
			// makes rendering more beautiful
			graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			
			graphicSettings.setColor(bigColor);
			
			//drawing the big particle
			Particle big = particles.get(0);
			graphicSettings.fillOval(big.x,big.y, 2*big.radius, 2*big.radius); 

			// Cycle through all of the Particles objects
			for(Particle p : particles.subList(1, particles.size())){								
				// Stroke the particle on the screen
				graphicSettings.setColor(smallColor);
				graphicSettings.fillOval(p.x,p.y, 2*radius, 2*radius);  
			} 
		} 

		public void populate () {
			
			//creating one big particle
			Particle big = new Particle(400, 200, 0, 0, Radius, Mass);
			particles.add(big);
			
			//creating n number of smaller particles
			for(int i = 0; i < N; i++){
				//setting default position
				x=(new Random()).nextInt(950)+20;
				y=(new Random()).nextInt(500)+10;
				
				//setting default velocity
				vx = min + (max - min) * r.nextDouble();
				vy = min + (max - min) * r.nextDouble();
		        
				Particle p = new Particle(x, y, vx, vy, radius, mass);
				particles.add(p);
			}
		}
		
		public void reset() {
			particles.clear();
			populate ();
		}
		
		public void basicSettings() {
			bgColor = Color.white;
			bigColor = Color.red;  
			smallColor = Color.green;
			Radius = 50;
			radius = 5;
			Mass = 100;
			mass = 10;
			N = 100;
			vx = min + (max - min) * r.nextDouble();
			vy = min + (max - min) * r.nextDouble();
		}
		
		//all the setters
		public void setBgColor(Color bgColor) {
			this.bgColor = bgColor;
		}
		
		public void setBigColor(Color ballColor) {
			this.bigColor = ballColor;
		}
		
		public void setSmallColor(Color ballColor) {
			this.smallColor = ballColor;
		}
		
		public void setN(int n) {
			AnimationPanel.N = n;
		}
		
		public void setBigRadius(int r) {
			AnimationPanel.Radius = r;
		}
		
		public void setSmallRadius(int r) {
			AnimationPanel.radius = r;
		}
		
		public void setBigMass(int m) {
			AnimationPanel.Mass = m;
		}
		
		public void setSmallMass(int m) {
			AnimationPanel.mass = m;
		}
		
		
}
