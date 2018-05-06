package Projekt;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BottomPanel extends JPanel{
	
	JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
	JPanel panel1, panel2, panel3;
	private AnimationPanel animationPanel;
	private RepaintTheBoard repaintTheBoard;

	public BottomPanel (AnimationPanel animation, RepaintTheBoard repaintTheBoard) {
		this.animationPanel = animation;
		this.repaintTheBoard = repaintTheBoard;
		
		//creating a border for settings menu at the bottom of the frame
		Border border = BorderFactory.createTitledBorder("SIMULATION SETTINGS");
		this.setBorder(border);
		this.setLayout(new GridLayout(3, 1));

		//creating an instance of a listener for all the buttons 
		buttonListener bListener = new buttonListener();

		//a panel holding all the settings for the small particle
		panel1 = new JPanel();
		Border Border1 = BorderFactory.createTitledBorder("Small particles properties");
		panel1.setBorder(Border1);
		
		button1 = new JButton("Set N");
		button1.addActionListener(bListener);
		panel1.add(button1);
		
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
		
		button8 = new JButton("ON");
		button8.addActionListener(bListener);
		panel3.add(button8);
		
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
		if(!repaintTheBoard.getShutdown()) {
			repaintTheBoard.setShutdown(true);
			try {
				Thread.sleep(10);
				animationPanel.reset();
				repaintTheBoard.setShutdown(false);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		else {
			animationPanel.reset();
			repaintTheBoard.setShutdown(false);
			button8.setText("OFF");
		}
	}
	
	//implementing listeners
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == button1) {
				int oldN = animationPanel.N;
				int newN = oldN;
				try {
					newN = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new number of particles", "Edit number of the particles", JOptionPane.QUESTION_MESSAGE, null, null, oldN).toString());
					if(oldN == newN) { return; }
					animationPanel.setN(newN);
					reset();
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			else if(e.getSource() == button2) {
				int oldR = animationPanel.radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.setSmallRadius(newR);
					reset();
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
					animationPanel.setSmallMass(newM);
					reset();
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			else if(e.getSource() == button4) {
				Color color1 = JColorChooser.showDialog(null, "Change the color of small particles", Color.WHITE);
				animationPanel.setSmallColor(color1);
				animationPanel.repaint();
			}
			
			else if(e.getSource() == button5) {
				int oldR = animationPanel.Radius;
				int newR = oldR;
				try {
					newR = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.setBigRadius(newR);
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
					animationPanel.setBigMass(newM);
					reset();
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			else if(e.getSource() == button7) {
				Color color2 = JColorChooser.showDialog(null, "Change the color of big particle", Color.WHITE);
				animationPanel.setBigColor(color2);
				animationPanel.repaint();
			}
			
			else if(e.getSource() == button8) {
				if(button8.getText()=="ON"){
					button8.setText("OFF");
					repaintTheBoard.setShutdown(false);
				} else {
					button8.setText("ON");
					repaintTheBoard.setShutdown(true);
				}
			}
			
			else if(e.getSource() == button9) {
				Color color3 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				animationPanel.setBgColor(color3);
				animationPanel.repaint();
			}
			
			else if(e.getSource() == button10) {
				animationPanel.basicSettings();
				reset();
			}
		}
	}	
}