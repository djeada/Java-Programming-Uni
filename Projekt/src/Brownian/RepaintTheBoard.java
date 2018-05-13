package Brownian;

public class RepaintTheBoard {
	private static final int UPDATE_RATE = 30;    // Frames per second (fps)
	private static final float EPSILON_TIME = 1e-2f;  // Threshold for zero time
	
	AnimationPanel animationPanel;
	private boolean paused = false;
	
	public RepaintTheBoard(AnimationPanel animationPanel){
			this.animationPanel = animationPanel;
	}
	 
	/** Start the ball bouncing. */
	   public void gameStart() {
	      // Run the game logic in its own thread.
	      Thread gameThread = new Thread() {
	         public void run() {
	            while (true) {
	               long beginTimeMillis, timeTakenMillis, timeLeftMillis;
	               beginTimeMillis = System.currentTimeMillis();
	               
	               if (!paused) {
	                  // Execute one game step
	                  gameUpdate();
	                  // Refresh the display
	                  animationPanel.repaint();
	               }
	               
	               // Provide the necessary delay to meet the target rate
	               timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
	               timeLeftMillis = 1000L / UPDATE_RATE - timeTakenMillis;
	               if (timeLeftMillis < 5) timeLeftMillis = 5; // Set a minimum
	               
	               // Delay and give other thread a chance
	               try {
	                  Thread.sleep(timeLeftMillis);
	               } catch (InterruptedException ex) {}
	            }
	         }
	      };
	      gameThread.start();  // Invoke GaemThread.run()
	   
	   }
	   
	   /** 
	    * One game time-step. 
	    * Update the game objects, with proper collision detection and response.
	    */
	   public void gameUpdate() {
	      float timeLeft = 1.0f;  // One time-step to begin with
	      
	      // Repeat until the one time-step is up 
	      do {
	         // Find the earliest collision up to timeLeft among all objects
	         float tMin = timeLeft;
	         
	         // Check collision between two balls
	         for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
	            for (int j = 0; j < animationPanel.getCurrentNumParticles(); j++) {
	               if (i < j) {
	                  animationPanel.getElement(i).intersect( animationPanel.getElement(j), tMin);
	                  if ( animationPanel.getElement(i).earliestCollisionResponse.t < tMin) {
	                     tMin =  animationPanel.getElement(i).earliestCollisionResponse.t;
	                  }
	               }
	            }
	         }
	         // Check collision between the balls and the box
	         for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
	        	 animationPanel.getElement(i).intersect(animationPanel.getBoard(), tMin);
	            if (animationPanel.getElement(i).earliestCollisionResponse.t < tMin) {
	               tMin =  animationPanel.getElement(i).earliestCollisionResponse.t;
	            }
	         }
	   
	         // Update all the balls up to the detected earliest collision time tMin,
	         // or timeLeft if there is no collision.
	         for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
	        	 animationPanel.getElement(i).update(tMin);
	         }
	   
	         timeLeft -= tMin;                // Subtract the time consumed and repeat
	      } while (timeLeft > EPSILON_TIME);  // Ignore remaining time less than threshold
	   }

	   void setPaused(boolean paused){
		   this.paused = paused;
	   }
	
	   boolean getPaused() {
		   return paused;
	   }
}
