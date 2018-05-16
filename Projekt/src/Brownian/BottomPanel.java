package Brownian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class for all the Swing elements used to manipulate the simulation
 * 
 * @author WP & AD
 *
 */

public class BottomPanel extends JPanel {
	
	private JButton SRButton, SMButton, SCButton, SRCButton, BRButton, BMButton, BCButton, BGCButton, ResetButton, LauncherButton;
	private JTextField launcherfield;
	private static int launchedparticles;
	private JCheckBox checkBox1, checkBox2;
	private JSlider slider1;
	private JPanel panel1, panel2, panel3;
	private JLabel SPnumber;
	private AnimationPanel animationPanel;
	private RepaintTheBoard repaintTheBoard;
	boolean launchboolean = true;
	
	//Constructor to initialize UI components of the controls
	public BottomPanel(RepaintTheBoard repaintTheBoard, AnimationPanel animationPanel) {
		this.repaintTheBoard = repaintTheBoard;
		this.animationPanel = animationPanel;
		
		// Create a border for settings menu at the bottom of the frame
		Border border = BorderFactory.createTitledBorder("SIMULATION SETTINGS");
		this.setBorder(border);
			
		// Set GridLayout
		this.setLayout(new GridLayout(3, 1));

		// Create instance of a listeners for JComponents
		buttonListener bListener = new buttonListener();
		checkBoxListener cListener = new checkBoxListener();
		sliderListener sListener = new sliderListener();

		// Panel to hold all the settings for the small particle
		panel1 = new JPanel();
		Border Border1 = BorderFactory.createTitledBorder("Small particles properties");
		panel1.setBorder(Border1);
		
		// Button used to change the radius of a particle
		SRButton = new JButton("Set Radius");
		SRButton.addActionListener(bListener);
		panel1.add(SRButton);
				
		// Button used to change the mass of a particle
		SMButton = new JButton("Set Mass");
		SMButton.addActionListener(bListener);
		panel1.add(SMButton);
				
		// Button used to change the color of a particle
		SCButton = new JButton("Set Color");
		SCButton.addActionListener(bListener);
		panel1.add(SCButton);
			
		// Button used to change the color of a particle to random
		SRCButton = new JButton("Random Color");
		SRCButton.addActionListener(bListener);
		panel1.add(SRCButton);
				
		// Slider for adjusting the speed of all the balls by a factor
		int minFactor = 5;    // percent
		int maxFactor = 200;  // percent
		slider1 = new JSlider(JSlider.HORIZONTAL, minFactor, maxFactor, 100);
		slider1.addChangeListener(sListener);
		panel1.add(new JLabel("Speed"));
		panel1.add(slider1);
		
		// Add panel1 to the bottomPanel
		this.add(panel1);

		// Panel to hold all the settings for the big particle
		panel2 = new JPanel();
		Border Border2 = BorderFactory.createTitledBorder("Big particle properties");
		panel2.setBorder(Border2);
				
		// Button used to change the radius of a particle
		BRButton = new JButton("Set Radius");
		BRButton.addActionListener(bListener);
		panel2.add(BRButton);
				
		// Button used to change the mass of a particle
		BMButton = new JButton("Set Mass");
		BMButton.addActionListener(bListener);
		panel2.add(BMButton);
				
		// Button used to change the color of a particle
		BCButton = new JButton("Set Color");
		BCButton.addActionListener(bListener);
		panel2.add(BCButton);

		// Add panel2 to the bottomPanel
		this.add(panel2);

		// Panel holding all the general options
		panel3 = new JPanel();
		Border Border3 = BorderFactory.createTitledBorder("General options");
		panel3.setBorder(Border3);
				
		// Checkbox to toggle pause/resume movement
		panel3.add(new JLabel("Pause"));
		checkBox1 = new JCheckBox();
		checkBox1.addItemListener(cListener);
		panel3.add(checkBox1);
		
		// Checkbox to enable aperiodic boundary conditions
		panel3.add(new JLabel("Aperiodic"));
		checkBox2 = new JCheckBox();
		checkBox2.addItemListener(cListener);
		panel3.add(checkBox2);
				
		// Button for launching the remaining balls
		launcherfield = new JTextField("Enter numer of particles");
		launcherfield.setMinimumSize(new Dimension(200, 5));
		LauncherButton = new JButton("Launch!");
		LauncherButton.addActionListener(bListener);
		panel3.add(launcherfield);
		panel3.add(LauncherButton);
		
		// Button used to change the background color
		BGCButton = new JButton("BG COLOR");
		BGCButton.addActionListener(bListener);
		panel3.add(BGCButton);
						
		// Button used to go back to the default settings
		ResetButton = new JButton("RESET");
		ResetButton.addActionListener(bListener);
		panel3.add(ResetButton);
		
		// Label displaying current number of particles
		SPnumber = new JLabel("No. of particles: " + animationPanel.getCurrentNumParticles());
		panel3.add(SPnumber);
		
		// Add panel3 to the bottomPanel
		this.add(panel3);
	}
	
	// Implement the listeners	
	
	// Listener for checkBox
	private class checkBoxListener implements ItemListener{
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkBox1.isSelected()) {
					repaintTheBoard.setPaused(true); 
					transferFocusUpCycle();  
				}
				else if (!checkBox1.isSelected()) {
		           repaintTheBoard.setPaused(false);
		           transferFocusUpCycle();  
		       }
				if(checkBox2.isSelected()) {
					repaintTheBoard.setPeriodicity(false);
					System.out.print(repaintTheBoard.getPeriodicity());
				}
				else if (!checkBox2.isSelected()) {
					repaintTheBoard.setPeriodicity(true);
					System.out.print(repaintTheBoard.getPeriodicity());
			    }
		    }
		}
	
	// Listener for Slider
	private class sliderListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				final float[] ballSavedSpeedXs = new float[animationPanel.getMax()];
				final float[] ballSavedSpeedYs = new float[animationPanel.getMax()];
				for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
					ballSavedSpeedXs[i] = animationPanel.getElement(i).vx;
					ballSavedSpeedYs[i] = animationPanel.getElement(i).vy;
				}
				int minFactor = 5;    // percent
				int maxFactor = 200;  // percent
				
				int percentage = (int)source.getValue();
				for (int i = 0; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).vx = ballSavedSpeedXs[i] * percentage / 100.0f;
					animationPanel.getElement(i).vy = ballSavedSpeedYs[i] * percentage / 100.0f;
				}
			}
			transferFocusUpCycle();  // To handle key events
		}
	 }
		
	// Listener for Buttons
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Change radius of the small particles
			if(e.getSource() == SRButton) {
				int oldR = animationPanel.radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.setSmallRadius(newR);
					for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
						animationPanel.getElement(i).radius = newR;
					}
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}

			// Change mass of the small particles
			else if(e.getSource() == SMButton) {
				int oldM = animationPanel.mass;
				int newM = oldM;
				try {
					newM = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
					if(oldM == newM) { return; }
					for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
						animationPanel.getElement(i).mass = newM;
					}
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change color of the small particles
			else if(e.getSource() == SCButton) {
				Color color1 = JColorChooser.showDialog(null, "Change the color of small particles", Color.WHITE);
				for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).color = color1;
				}
				animationPanel.setSmallColor(color1);
			}
			
			// Change color of the small particles to random
			else if(e.getSource() == SRCButton) {
				for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
					animationPanel.getElement(i).color = Color.getHSBColor((float)(2*3.1416*animationPanel.getElement(i).vy/animationPanel.getElement(i).vx), 1f, (float)(animationPanel.getElement(i).y/animationPanel.getElement(i).x));
				}
			}			
			
			// Change radius of the big particle
			else if(e.getSource() == BRButton) {
				int oldR = animationPanel.Radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.getElement(0).radius = newR;
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change mass of the big particle
			else if(e.getSource() == BMButton) {
				int oldM = animationPanel.Mass;
				int newM = oldM;
				try {
					newM = Integer.parseInt(JOptionPane.showInputDialog(
							AnimationGUI.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
					if(oldM == newM) { return; }
					animationPanel.getElement(0).mass = newM;

				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			// Change color of the big particle
			else if(e.getSource() == BCButton) {
				Color color2 = JColorChooser.showDialog(null, "Change the color of big particle", Color.WHITE);
				animationPanel.getElement(0).color = color2;
			}
			
			// Change color of the background
			else if(e.getSource() == BGCButton) {
				Color color3 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				animationPanel.getBoard().setColor(color3);
				animationPanel.repaint();
			}
			
			// Call the method reset from AnimationPanel whenever reset Button is pressed
			else if(e.getSource() == ResetButton) {
				if(!repaintTheBoard.getPaused()) {
					repaintTheBoard.setPaused(true);
					try {
						Thread.sleep(10);
						animationPanel.reset();
						repaintTheBoard.setPaused(false);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else {
					animationPanel.reset();
					repaintTheBoard.setPaused(false);
					checkBox1.setSelected(false);
				}
			
			}
			
			// Launcher button listener used to launch new particles
			else if(e.getSource() == LauncherButton) {
				
				// Set the number of launched particles
				try{
					launchedparticles = Integer.parseInt(launcherfield.getText());
				}
				// Make sure input was a number
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(
						AnimationGUI.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); 
				}
					
				// Make a new thread to control the realse ot the particles
					new Thread(() ->{
						int counter = 0;
						while (launchboolean) {
							Toolkit.getDefaultToolkit().sync();
							try {
								Thread.sleep(250);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (animationPanel.getCurrentNumParticles() < animationPanel.getMax()) {
								animationPanel.getElement(animationPanel.getCurrentNumParticles()).x = 20;
								animationPanel.getElement(animationPanel.getCurrentNumParticles()).y = animationPanel.getCanvasHeight() - 20;
								animationPanel.setCurrentNumParticles(animationPanel.getCurrentNumParticles() + 1);
								
								// Check if we haven't reached the maximum number of allowed particles
								if (animationPanel.getCurrentNumParticles() == animationPanel.getMax()) {
									// Disable the button, as there is no more particles
									LauncherButton.setEnabled(false);
					              }
					            }
								transferFocusUpCycle();  // To handle key events
								SPnumber.setText("No. of particles " + animationPanel.getCurrentNumParticles());
								
								// Determine when to stop
								counter++;
								if (counter >= launchedparticles) {
									launchboolean = false;
								}
						}
						launchboolean = true;
					}).start();
				}
			}
	}
}
