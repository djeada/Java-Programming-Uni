package lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.*;

import javax.swing.JPanel;

class PanelRysowania extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	List<Prostokat> prostakaty = new ArrayList<Prostokat>();


	public PanelRysowania() {
		int rate = 15;
		final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this, rate, rate, MILLISECONDS);
	}
	
	@Override
	public void run() {
		for(Prostokat p : prostakaty) {
			p.wallTest(this);
			p.processMove();

		}
		repaint();
	}
	public void dodajLosowyProstokat(){
		Random r = new Random();
		
		Prostokat p = new Prostokat();
		p.setX(r.nextInt(550));
		p.setY(r.nextInt(550));
		p.setWidth(r.nextInt(80));
		p.setHeight(r.nextInt(80));
		p.setColor(new Color(r.nextInt(255), r.nextInt(255),
				r.nextInt(255), r.nextInt(255)));

		prostakaty.add(p);		
	}
	
	public void dodajProstokat(int x, int y, int width, int height, Color c){
		Prostokat p = new Prostokat();
		p.setX(x);
		p.setY(y);
		p.setWidth(width);
		p.setHeight(height);
		p.setColor(c);

		prostakaty.add(p);		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Prostokat pr : prostakaty) {
			pr.paint(g);
		}

	}
	
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
}

