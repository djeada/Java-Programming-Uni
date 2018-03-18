package real;

import java.util.Random;

public class Particle {
	
	public int x,y;
	public double vx=50*(new Random()).nextGaussian();
	public double vy=50*(new Random()).nextGaussian();

	public int r=15;
	private int dist2=r*r*4;
	double dist=2*r;
	double speed;
	

	public Particle(AnimationPanel animationPanel) {
		//start position
		x=(new Random()).nextInt(400)+2*r;
		y=(new Random()).nextInt(400)+2*r;
		speed=Math.sqrt(vx*vx+vy*vy);
		

	}
	public Particle(int X, int Y) {
		//start position
		x=X;
		y=Y;

	}
	
	public void borderTest(AnimationPanel Panel){
		if(x<r){vx*=-1;x=r;}
		if(x>Panel.getWidth()-r){vx*=-1;x=Panel.getWidth()-r;}
		if(y<r){vy*=-1;y=r;}
		if(y>Panel.getHeight()-r){vy*=-1;y=Panel.getHeight()-r;}
	}
	public void ballTest(Particle b,double dt){
		double d2=(b.x-x)*(b.x-x)+(b.y-y)*(b.y-y);
		if(d2<dist2&&d2>0){
			speed=Math.sqrt(vx*vx+vy*vy);
			double nx=(b.x-x)/Math.sqrt(d2);
			double ny=(b.y-y)/Math.sqrt(d2);
			double p=1*(vx*nx+vy*ny-b.vx*nx-b.vy*ny);
			vx-=p*nx;
			vy-=p*ny;
			b.vx+=p*nx;
			b.vy+=p*ny;
			y+=vy*2*dt;
			x+=vx*2*dt;
			b.y+=b.vy*2*dt;
			b.x+=b.vx*2*dt;
			
		}
	}
	
	public void nextPos(double dt){
		y+=vy*dt;
		x+=vx*dt;
	}
}
