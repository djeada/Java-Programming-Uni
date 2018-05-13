package Brownian;

import java.awt.BorderLayout;

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
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		// Allocate and add the game panel      
		animationPanel = new AnimationPanel(this.getWidth(), this.getHeight()-100);
		
		// Make the drawing area take up the rest of the frame
		frame.add(animationPanel, BorderLayout.CENTER);
		
		bottomPanel = new BottomPanel(repaintTheBoard, animationPanel);		
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		repaintTheBoard = new RepaintTheBoard(animationPanel);
		repaintTheBoard.gameStart();
		
		// Show the frame
		frame.setVisible(true);
	}
	
	 public static void main(String[] args) {
		// Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	 Main main = new Main();
	        }
	    });
	}
}
