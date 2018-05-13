package Brownian;
import java.awt.*;
import java.util.Formatter;

import collisionphysics.*;

public class Particle {
	
	// Particle's center coordinates x and y and other parameters
	public float x, y;
	public float vx, vy; 
	public float radius;
	public float mass;
	public Color color;  
   
   //Constructor to initialize a particle object
   // For user friendliness, user specifies velocity in speed and moveAngle in usual Cartesian coordinates
   public Particle(float x, float y, float radius, float speed, float angleInDegree, float mass, Color color) {
      this.x = x;
      this.y = y;
      // Convert (speed, angle) to (x, y), with y-axis inverted
      this.vx = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
      this.vy = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
      this.radius = radius;
      this.mass = mass;
      this.color = color;
   }

   // Working copy for computing response in intersect(), to avoid repeatedly allocating objects.
   private CollisionResponse tempResponse = new CollisionResponse(); 

   // Maintain the response of the earliest collision detected by this ball instance. 
   //Only the first collision matters!
   CollisionResponse earliestCollisionResponse = new CollisionResponse();
   
   //Check if there will be collision with the borders of a Board in the coming time-step.
   public void intersect(Board board, float timeLimit) {
      // Invoke movingPointIntersectsRectangleOuter, 
      // Earliest collision to one of the 4 borders is returned.
      CollisionPhysics.pointIntersectsRectangleOuter(x, y, vx, vy, radius,
            board.minX, board.minY, board.maxX, board.maxY, timeLimit, tempResponse);
      if (tempResponse.t < earliestCollisionResponse.t) {
         earliestCollisionResponse.copy(tempResponse);
      }
   }
   
   // Working copy for computing response in intersect(Particle, timeLimit), to avoid repeatedly allocating objects.
   private CollisionResponse thisResponse = new CollisionResponse(); 
   private CollisionResponse anotherResponse = new CollisionResponse(); 

   //Check if this ball collides with the given another ball in the interval  (0, timeLimit].
   public void intersect(Particle another, float timeLimit) {
      // Call movingPointIntersectsMovingPoint() with timeLimit.
      // Use thisResponse and anotherResponse, as the working copies, to store the
      // responses of this ball and another ball, respectively.
      // Check if this collision is the earliest collision, and update the ball's
      // earliestCollisionResponse accordingly.
      CollisionPhysics.pointIntersectsMovingPoint(
            this.x, this.y, this.vx, this.vy, this.radius, this.mass,
            another.x, another.y, another.vx, another.vy, another.radius, another.mass,
            timeLimit, thisResponse, anotherResponse);
      
      if (anotherResponse.t < another.earliestCollisionResponse.t) {
            another.earliestCollisionResponse.copy(anotherResponse);
      }
      if (thisResponse.t < this.earliestCollisionResponse.t) {
            this.earliestCollisionResponse.copy(thisResponse);
      }
   }

   // Update the states of this ball for the given time.
   //If this ball's earliestCollisionResponse.time equals to time, this
   //ball is the one that collided; otherwise, there is a collision elsewhere.
   
   public void update(float time) {
      // Check if this ball is responsible for the first collision?
      if (earliestCollisionResponse.t <= time) {
         // This ball collided, get the new position and speed
         this.x = earliestCollisionResponse.getNewX(this.x, this.vx);
         this.y = earliestCollisionResponse.getNewY(this.y, this.vy);
         this.vx = earliestCollisionResponse.newSpeedX;
         this.vy = earliestCollisionResponse.newSpeedY;
      } else {
         // This ball does not involve in a collision. Move straight.
         this.x += this.vx * time;         
         this.y += this.vy * time;         
      }
      // Clear for the next collision detection
      earliestCollisionResponse.reset();
   }
   
   // Draw itself using the given graphics context.
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius),
            (int)(2 * radius));
   }
   
   // Return the direction of movement in degrees (counter-clockwise).
   public float getMoveAngle() {
      return (float)Math.toDegrees(Math.atan2(-vy, vx));
   }
   
   //Return the kinetic energy (0.5mv^2) */
   public float getKineticEnergy() {
      return 0.5f * mass * (vx * vx + vy * vy);
   }

   //Display info
   public String toString() {
      sb.delete(0, sb.length());
      formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%3.0f,%3.0f) " +
            "\u0398=%4.0f KE=%3.0f", 
            x, y, radius, vx, vy, getMoveAngle(),
            getKineticEnergy());  // \u0398 is theta
      return sb.toString();
   }
   private StringBuilder sb = new StringBuilder();
   private Formatter formatter = new Formatter(sb);

}
