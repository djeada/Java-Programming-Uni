package Brownian;

import java.awt.Color;
import java.awt.GridLayout;
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
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BottomPanel extends JPanel {
	JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11;
	JCheckBox checkBox1;
	JSlider slider1;
	JPanel panel1, panel2, panel3;
	private AnimationPanel animationPanel;
	private RepaintTheBoard repaintTheBoard;
	
	//Constructor to initialize UI components of the controls
	public BottomPanel(RepaintTheBoard repaintTheBoard, AnimationPanel animationPanel) {
		this.repaintTheBoard = repaintTheBoard;
		this.animationPanel = animationPanel;
		
		//creating a border for settings menu at the bottom of the frame
			Border border = BorderFactory.createTitledBorder("SIMULATION SETTINGS");
			this.setBorder(border);
			this.setLayout(new GridLayout(3, 1));

			//creating an instance of a listener for all the buttons 
			buttonListener bListener = new buttonListener();
			checkBoxListener cListener = new checkBoxListener();
			sliderListener sListener = new sliderListener();


			//a panel holding all the settings for the small particle
			panel1 = new JPanel();
			Border Border1 = BorderFactory.createTitledBorder("Small particles properties");
			panel1.setBorder(Border1);
				
			button2 = new JButton("Set Radius");
			button2.addActionListener(bListener);
			panel1.add(button2);
				
			button3 = new JButton("Set Mass");
			button3.addActionListener(bListener);
			panel1.add(button3);
				
			button4 = new JButton("Set Color");
			button4.addActionListener(bListener);
			panel1.add(button4);
				
			this.add(panel1);

			//a panel holding all the settings for the big particle
			panel2 = new JPanel();
			Border Border2 = BorderFactory.createTitledBorder("Big particle properties");
			panel2.setBorder(Border2);
				
			button5 = new JButton("Set Radius");
			button5.addActionListener(bListener);
			panel2.add(button5);
				
			button6 = new JButton("Set Mass");
			button6.addActionListener(bListener);
			panel2.add(button6);
				
			button7 = new JButton("Set Color");
			button7.addActionListener(bListener);
			panel2.add(button7);
				
			this.add(panel2);

			//a panel holding all the general options
			panel3 = new JPanel();
			
			Border Border3 = BorderFactory.createTitledBorder("General options");
			panel3.setBorder(Border3);
				
				
			// A checkbox to toggle pause/resume movement
			panel3.add(new JLabel("Pause"));
			checkBox1 = new JCheckBox();
			checkBox1.addItemListener(cListener);
			panel3.add(checkBox1);
			
			// A slider for adjusting the speed of all the balls by a factor
			
			int minFactor = 5;    // percent
			int maxFactor = 200;  // percent
			slider1 = new JSlider(JSlider.HORIZONTAL, minFactor, maxFactor, 100);
			slider1.addChangeListener(sListener);
			panel3.add(new JLabel("Speed"));
			panel3.add(slider1);
			
				
			// A button for launching the remaining balls
			button11 = new JButton("Launch New Particle");
			button11.addActionListener(bListener);
			panel3.add(button11);
			
			button9 = new JButton("BG COLOR");
			button9.addActionListener(bListener);
			panel3.add(button9);
						
			button10 = new JButton("RESET");
			button10.addActionListener(bListener);
			panel3.add(button10);
			
			this.add(panel3);
	}
	
	//implenting methods
	
		void reset() {
			if(!repaintTheBoard.getPaused()) {
				repaintTheBoard.setPaused(false);
				try {
					Thread.sleep(10);
					animationPanel.reset();
					repaintTheBoard.setPaused(true);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			else {
				animationPanel.reset();
				repaintTheBoard.setPaused(true);
				checkBox1.setSelected(true);
			}
		}
		
		//implementing listeners
		
		private class checkBoxListener implements ItemListener{
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(checkBox1.isSelected()) {
					repaintTheBoard.setPaused(false); // Toggle pause/resume flag
					//transferFocusUpCycle();  // To handle key events
				}
				else {
		           repaintTheBoard.setPaused(true); // Toggle pause/resume flag
		           transferFocusUpCycle();  // To handle key events
		       }
		    }
		}
		
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
		
		private class buttonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == button2) {
					int oldR = animationPanel.radius;
					int newR = oldR;
					try {
						newR = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
						if(oldR == newR) { return; }
						animationPanel.setSmallRadius(newR);
						for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
							animationPanel.getElement(i).radius = newR;
						}
					}
					catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
					catch(NullPointerException npe) {  }
				}
				
				else if(e.getSource() == button3) {
					int oldM = animationPanel.mass;
					int newM = oldM;
					try {
						newM = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
						if(oldM == newM) { return; }
						for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
							animationPanel.getElement(i).mass = newM;
						}
					}
					catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
					catch(NullPointerException npe) {  }
				}
				
				else if(e.getSource() == button4) {
					Color color1 = JColorChooser.showDialog(null, "Change the color of small particles", Color.WHITE);
					for (int i = 1; i < animationPanel.getCurrentNumParticles(); i++) {
						animationPanel.getElement(i).color = color1;
					}
					animationPanel.setSmallColor(color1);
				}
				
				else if(e.getSource() == button5) {
					int oldR = animationPanel.Radius;
					int newR = oldR;
					try {
						newR = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
						if(oldR == newR) { return; }
						animationPanel.getElement(0).radius = newR;
						reset();
					}
					catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
					catch(NullPointerException npe) {  }
				}
				
				else if(e.getSource() == button6) {
					int oldM = animationPanel.Mass;
					int newM = oldM;
					try {
						newM = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new mass", "Edit mass", JOptionPane.QUESTION_MESSAGE, null, null, oldM).toString());
						if(oldM == newM) { return; }
						animationPanel.getElement(0).mass = newM;

					}
					catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
					catch(NullPointerException npe) {  }
				}
				
				else if(e.getSource() == button7) {
					Color color2 = JColorChooser.showDialog(null, "Change the color of big particle", Color.WHITE);
					animationPanel.getElement(0).color = color2;
				}
				
				
				else if(e.getSource() == button9) {
					Color color3 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
					animationPanel.getBoard().setColor(color3);
					animationPanel.repaint();
				}
				
				else if(e.getSource() == button10) {
					animationPanel.reset();
				}
				
				else if(e.getSource() == button11) {
					if (animationPanel.getCurrentNumParticles() < animationPanel.getMax()) {
						animationPanel.getElement(animationPanel.getCurrentNumParticles()).x = 20;
						animationPanel.getElement(animationPanel.getCurrentNumParticles()).y = animationPanel.getCanvasHeight() - 20;
						animationPanel.setCurrentNumParticles(animationPanel.getCurrentNumParticles()+1);
						if (animationPanel.getCurrentNumParticles() == animationPanel.getMax()) {
							// Disable the button, as there is no more ball
							button11.setEnabled(false);
			              }
			            }
						transferFocusUpCycle();  // To handle key events
			       }
				}
			}
}	
