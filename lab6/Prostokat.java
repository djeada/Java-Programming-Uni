package lab6;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Prostokat{

	Random r = new Random();

    private int xPos = 50;
	private int yPos = 50;
    private int width = 20;
    private int height = 20;
    private int vx = r.nextInt(9)+1;
    private int vy = r.nextInt(9)+1;
    private Color color = Color.BLACK;
    
    public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }


	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void paint(Graphics g){
        g.setColor(getColor());
        g.fillRect(xPos,yPos,getWidth(),getHeight());
    }
	
	public int getVx() {
		return vx;
	}
	
	public int getVy() {
		return vy;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	  public void processMove() {
	    	xPos += vx;
	    	yPos += vy;
	  }
		
	  public void wallTest(PanelRysowania panel) {
		  if((this.getX() + this.getWidth() >= panel.getWidth() && this.getVx() > 0) || (this.getX() <= 0 && this.getVx() < 0))
			  this.setVx(-this.getVx());
		  if((this.getY() + this.getHeight() >= panel.getHeight() - 40 && this.getVy() > 0) || (this.getY() <= 0 && this.getVy() < 0))
			  this.setVy(-this.getVy());
	  }
}

