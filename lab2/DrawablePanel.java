package lab2;

import java.awt.*;
import javax.swing.*;

public class DrawablePanel extends JPanel {
	
	public DrawablePanel() throws HeadlessException
	{
		super();
	}
	
	public void paintComponent(Graphics g) {
		//Klasa JPanel posiada metodê paintComponent(), 
		//DrawablePanel "nadpisuje" oryginaln¹ definicjê. 
		//konieczne jest wywo³anie oryginalnej metody
		super.paintComponent(g);
		
		//czerwony prostok¹t 
		g.setColor(Color.red);
	    g.fillRect(50, 50, 150, 100);
		
	    //niebieskie ko³o
		g.setColor(Color.blue);
	 	g.fillOval(250, 250, 150, 150);
			
	}
	
	public static void main(String[] args) {
		
		CloseableFrame frame = new CloseableFrame();	
		DrawablePanel panel = new DrawablePanel();
		
		panel.setBackground(Color.white);
		frame.add(panel);
	  
	    frame.setVisible(true);
	}
}
