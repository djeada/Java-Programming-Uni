package Projekt;

import java.awt.BorderLayout;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main extends JFrame{
	private Menu menu;
	private	AnimationPanel gamePanel;
	private BottomPanel bottomPanel;
	public static JFrame frame;

	public Main () {
		frame = new JFrame();

		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		frame.setTitle("Brownian Motion");
		setLayout(new BorderLayout());
		
		gamePanel = new AnimationPanel();

		// Make the drawing area take up the rest of the frame
		
		frame.add(gamePanel, BorderLayout.CENTER);
		
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		bottomPanel = new BottomPanel(gamePanel);		
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		// Used to execute code after a given delay
	    // The attribute is corePoolSize - the number of threads to keep in 
	    // the pool, even if they are idle
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
		// Method to execute, initial delay, subsequent delay, time unit
		
		executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
		
		// Show the frame
		
		frame.setVisible(true);
	}
	
	//Class implements the runnable interface
	//By creating this thread we can continually redraw the screen
	//while other code continues to execute

	class RepaintTheBoard implements Runnable{

		Main theBoard;
		
		public RepaintTheBoard(Main theBoard){
			this.theBoard = theBoard;
		}

		@Override
		public void run() {
			
			// Redraws the game board
			
			theBoard.repaint();
			
		}
		
	}
	
	 public static void main(String[] args) {
	        Main main = new Main();
	 }
}
