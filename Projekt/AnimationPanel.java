package real;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel{
	int width=this.getWidth();
	int height=this.getHeight();
	public int n=50;
	public double maxV=Double.MIN_VALUE;
	public double minV=Double.MAX_VALUE;
	
	public ArrayList<Particle> list=new ArrayList<Particle>();
	public AnimationPanel() {
		this.setBackground(Color.darkGray);
		for(int i=0;i<n;++i){
			Particle b = new Particle (this);
			if(b.speed>maxV)maxV=b.speed;
			if(b.speed<minV)minV=b.speed;
			list.add(b);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		//czastki
		maxV=Double.MIN_VALUE;
		minV=Double.MAX_VALUE;
		for(Particle b: list){
			if(b.speed>maxV)maxV=b.speed;
			if(b.speed<minV)minV=b.speed;
		}
		
		//pojedyncza kulka
        Graphics2D graph = (Graphics2D)g;
        graph.clearRect(0, 0, getWidth(), getHeight());            
        
        for(Particle b: list){
        graph.setColor(Color.getHSBColor((float)(2*3.1416*minV/maxV), 1f, (float)(b.speed/maxV)));
        graph.fillOval(b.x-b.r, b.y-b.r, 2*b.r,2*b.r);   
        }
        

	}
}
