package Brownian;

/**
 * GUI class
 * 
 * @author WP
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntroMenu extends JFrame{
	private	AnimationGUI animationGUI;
	private Description description;
	private JButton button1, button2, button3;
	public static JFrame frame;
    
	//Constructor to create the GUI components
	public IntroMenu () {
		
		// Create the frame
		frame = new JFrame();
				
		// Define the size of the frame
		frame.setSize(400, 400);
				
		// Define position based on a component
		frame.setLocationRelativeTo(null);
				
		// Define how the frame exits (Click the Close Button)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize an Image object as what was returned by requestImage() method
		final Image myImage = requestImage("./img/physics.jpg");

		// Allocate  a new panel and make fill it with myImage
		JPanel panel1 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(myImage, 0, 0, null);
	            }
		};
		
		// Make sure that panel1 fills the whole frame
		panel1.setPreferredSize(new Dimension(400, 400));
		
		// Create panel to hold menu buttons
		JPanel panel2 = new JPanel();
		
		// BoxLayout allows components to be placed on top of each other
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		
		// Create start button and add it to the panel
		button1 = new JButton("Start");
		panel2.add(button1);
		
		// Add a listener to the button
	    button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				animationGUI = new AnimationGUI();
				frame.setVisible(false);
			}
	    });
	    
	    // Create exit button and add it to the panel
	 	button3 = new JButton("Info");	         
	 	panel2.add(button3);
	 		
	 	// Add a listener to the button
	 	button3.addActionListener(new ActionListener() {
	 		@Override
	 		public void actionPerformed(ActionEvent arg0) {
	 			description = new Description();
				frame.setVisible(false);			
	 		}
	 	});
	    
		// Create exit button and add it to; the panel
		button2 = new JButton("Exit");	         
		panel2.add(button2);
		
		// Add a listener to the button
	    button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);			
			}
	    });
	    
	    //add panel holding buttons to the panel with background image
	    panel1.add(panel2);
	    
	    // Set panel1 as ContentPane of the frame
		frame.setContentPane(panel1);
	    
	    // Define the title for the frame
		frame.setTitle("Brownian Motion");
		
		// Show the frame
		frame.setVisible(true);
	}
	
	//Method used to read an image from a file and return an image object
	 public Image requestImage(String text) {
		Image image = null;
		 
		try {
			image = ImageIO.read(new File(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return image;
	 }
	
}
