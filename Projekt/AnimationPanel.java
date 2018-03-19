package Projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JComponent;

public class AnimationPanel extends JComponent { 
		
		int width=this.getWidth();
		int height=this.getHeight();
		static int N = 500;
		static Color bgColor = Color.black;
		static Color ballColor = Color.white;
		static int Radius = 15;
		Graphics2D graphicSettings;
		
		// Holds every particle we create
		
		public ArrayList<Particle> particles = new ArrayList<Particle>();
		
		// Creates n Particle objects and stores them in the ArrayList
		
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
			
			// Cycle through all of the Particles objects
			
			for(Particle p : particles){								
				// Stroke the particle on the screen
				graphicSettings.setColor(ballColor);
				graphicSettings.fillOval(p.x-p.radius,p.y-p.radius, Radius, Radius);  
			} 
			
		} 
		
		public void populate () {
			for(int i = 0; i < N; i++){
				Particle p = new Particle();
				particles.add(p);
			}
		}
		
		public void reset() {
			particles.clear();
			bgColor = Color.black;
			ballColor = Color.white;  
			
			populate ();
		}
		
		public void setBgColor(Color bgColor) {
			AnimationPanel.bgColor = bgColor;
		}
		
		public void setBallColor(Color ballColor) {
			AnimationPanel.ballColor = ballColor;
		}
		
		public void setN(int n) {
			AnimationPanel.N = n;
		}
		
		public void setRadius(int r) {
			AnimationPanel.Radius = r;
		}
		
}
