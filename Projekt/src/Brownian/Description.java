package Brownian;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class displays general information about the experiment. 
 * 
 * @author WP
 *
 */

public class Description extends JFrame {
	private Menu menu;
	private JPanel panel1, panel2;
	private JTextArea textArea1;
	private JScrollPane scroll1;
	private JLabel label1;
	public static JFrame frame;

	public Description() {
		
		// Create the frame
		frame = new JFrame();
		
		// Define the size of the frame
		frame.setSize(1000, 800);
		
		// Define position based on a component
		frame.setLocationRelativeTo(null);
		
		// Define how the frame exits (Click the Close Button)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// BorderLayout allows to place components against any of the four borders of the container
	    frame.setLayout(new BorderLayout());

	    // Initialize Menu class object and set it as JMenuBar for our frame
		menu = new Menu();
		frame.setJMenuBar(menu);
		
		// Allocate panel1 which will hold text description
		panel1 = new JPanel();		
		panel1.setMinimumSize(new Dimension(600,600));

		// Initialize an object of JTextArea class
		textArea1 = new JTextArea();
		
		// Set the line-wrapping policy of the text area.
	    textArea1.setLineWrap(true);
	    textArea1.setWrapStyleWord(true);
	    
	    // Make textArea1 unEditable
	    textArea1.setEditable(false);
	    
	    // Set displaying text for textArea1
	    textArea1.setText("\nIn 1829, the Scottish botanist Robert Brown noticed tiny pollen grains in water moving around in a completely disordered fashion, "
	    		+"tracing out a path like a 'drunkard's walk;. He was very surprised and thought that here might be the basis of life. "
	    		+"But tiny pieces of mica in water sealed up in rocks for millions of years also behaved similarly – these could hardly be alive, "
	    		+"so the idea was dropped. It took a long time – about 50 years – for scientists to realize the origin of Brownian motion and to be convinced that"
	    		+"they showed the ideas of the kinetic theory and the reality of molecules."
	    		+"\n\nIn 1905 Albert Einstein worked out the theory of Brownian motion and Avogadro's number, which is a measure of the actual number of molecules present"
	    		+"in a gram-molecule of a substance, was determined from Brownian motion."
	    		+ "\n\nBrownian motion occurs in liquids and gases because of the random motion of the molecules. In gases, Brownian motion is best observed by illuminating "
	    		+ "from the side under a microscope a shallow box containing smoke. A dark background is put behind the box. The illuminated smoke particles "
	    		+ "seen as bright spots of light execute a zigzag walk against the dark background. The smoke particles have smaller diameters than the wavelength"
	    		+ " of light but they can easily be seen as they scatter light into a diffraction halo. \n\nThere are two sorts of Brownian motions of the smoke particles. "
	    		+ "The more easily observed movement is that in which the particles are knocked from place to place. There is a second type of motion more difficult "
	    		+ "to observe, in which large particles, which have some mark on them, are found to be turned through different angles by the impact of the molecules. "
	    		+ "This is called rotational Brownian motion.\n\nThe kinetic theory of gases makes the assumption that molecules are hard, perfectly elastic little spheres,"
	    		+ " much like steel ball-bearings – except that these are not perfectly elastic. There are about 26 million trillion such molecules to a cubic centimeter of air. "
	    		+ "They move around rapidly and chaotically, and their energy of motion or kinetic energy is proportional to what a thermometer measures as the temperature "
	    		+ "of the gas. The gas molecules communicate their energy to the molecules of mercury in the thermometer and the higher energy mercury molecules then "
	    		+ "take up more space. Gases are heated up by bringing a bunch of faster moving molecules – (i.e., a gas at a higher temperature) and letting them loose among "
	    		+ "the more sluggish ones. The sluggish molecules are speeded up when they are bombarded by fast moving ones. In doing so the fast moving molecules are slowed "
	    		+ "down a little, and the average kinetic energy of the two gases becomes the same, i.e. they come to be at the same temperature, somewhere between the "
	    		+ "two temperatures. \n\nWhen one of the molecular bullets hits the wall of a container it exerts a force on the wall – exactly as a ball thrown at an open door "
	    		+ "exerts a force and will slightly move it. All the rebounds of the molecules add together and make up the pressure of the gas. If the volume of the vessel containing "
	    		+ "the gas is halved the number of impacts per second will be doubled, so the pressure will also double. This is the explanation of Boyle's law which states that "
	    		+ "pressure × volume = constant.\n\nIf no heat was lost to the outside, the motions of all the molecules would continue because they are perfectly elastic and they "
	    		+ "do not lose any energy by collision. Ball bearings or billiard balls set flying about on a billiard table quickly lose their energy because of friction and also "
	    		+ "because they are not nearly elastic enough to keep going.\n");
	    
	    // Creates a grid layout with the specified number of rows and columns. 
	    panel1.setLayout(new GridLayout(1,1));
	    panel1.add(textArea1);
	    
	    // Provides a scrollable view
	    scroll1 = new JScrollPane(textArea1);
	    scroll1.setBorder(null);
	    panel1.add(scroll1);
	    
	    // Add panel1 to the frame
	    frame.add(panel1,BorderLayout.CENTER);

	    // Initialize a second panel for an image
	    panel2 = new JPanel();
	   
	    // Read image from the file and set it as JLabel
	    ImageIcon brown = new ImageIcon("./img/brown.jpg");
		label1 = new JLabel();
		label1.setIcon(brown);
		label1.setIconTextGap(15);
	    panel2.add(label1);
	    
	    // Add panel2 to the frame
	    frame.add(panel2,BorderLayout.WEST);
	    
		// Define the title for the frame
		frame.setTitle("Brownian Motion");
		
		// Show the frame
		frame.setVisible(true);

	}
	
	

}
