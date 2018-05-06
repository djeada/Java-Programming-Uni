package Projekt;

class RepaintTheBoard implements Runnable{

	AnimationPanel animationPanel;
	private volatile boolean shutdown = true;
	
	public RepaintTheBoard(AnimationPanel animationPanel){
		this.animationPanel = animationPanel;
	}
	
	@Override
	public void run() {
		
		// Redraws the game board
		while (!shutdown) {
			for(Particle p: animationPanel.particles){
				p.wallTest(animationPanel);
				for(int i=0;i<50;i++) {
					p.collisionTest(animationPanel);
				}
				p.move();
				animationPanel.repaint();
			}
		}
	}
	
	void setShutdown (boolean shutdown) {
		this.shutdown = shutdown;
	}
	
	boolean getShutdown() {
		return shutdown;
	}
	
}