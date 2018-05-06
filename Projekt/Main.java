package Projekt;

import java.awt.BorderLayout;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main extends JFrame{
	private Menu menu;
	private	AnimationPanel animationPanel;
	private BottomPanel bottomPanel;
	private RepaintTheBoard repaintTheBoard;

	public static JFrame frame;

	public Main () {
		frame = new JFrame();

		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		frame.setTitle("Brownian Motion");
		setLayout(new BorderLayout());
		
		animationPanel = new AnimationPanel();

		// Make the drawing area take up the rest of the frame
		frame.add(animationPanel, BorderLayout.CENTER);
		
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		repaintTheBoard = new RepaintTheBoard(animationPanel);

		bottomPanel = new BottomPanel(animationPanel, repaintTheBoard);		
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		// Used to execute code after a given delay
		// The attribute is corePoolSize - the number of threads to keep in the pool
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
		// Method to execute, initial delay, subsequent delay, time unit
	     
		executor.scheduleAtFixedRate(repaintTheBoard, 0L, 500L, TimeUnit.MILLISECONDS);
		
		// Show the frame
		frame.setVisible(true);
	}
	
	 public static void main(String[] args) {
			Main main = new Main();
	 }

}


