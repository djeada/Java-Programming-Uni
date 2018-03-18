package real;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame{
	private Menu menu;
	private AnimationPanel animationPanel;
	private BottomPanel bottomPanel;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public Main () {
		
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		this.setTitle("Brownian Motion");
		setLayout(new BorderLayout());
		
		menu = new Menu();
		this.setJMenuBar(menu);
		
		animationPanel = new AnimationPanel();
		this.add(animationPanel, BorderLayout.CENTER);
		
		bottomPanel = new BottomPanel(animationPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	
	
	 public static void main(String[] args) {
	        Main main = new Main();
	 }
}
