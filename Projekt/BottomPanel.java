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
import javax.swing.Timer;
import javax.swing.border.Border;

public class BottomPanel extends JPanel{
	
	JButton button1, button2, button3, button4, button5, button6, button7;
	JPanel panel1, panel2, panel3;
	AnimationPanel animationPanel;

	public BottomPanel (AnimationPanel animation) {
		this.animationPanel = animation;
		
		Border border = BorderFactory.createTitledBorder("SIMULATION SETTINGS");
		this.setBorder(border);
				
		buttonListener bListener = new buttonListener();

		this.setLayout(new GridLayout(3, 1));

		panel1 = new JPanel();
		Border Border1 = BorderFactory.createTitledBorder("Small particles properties");
		panel1.setBorder(Border1);
		button3 = new JButton("Set N");
		button3.addActionListener(bListener);
		panel1.add(button3);
		button7 = new JButton("Set Radius");
		button7.addActionListener(bListener);
		panel1.add(button7);
		this.add(panel1);

		panel2 = new JPanel();
		Border Border2 = BorderFactory.createTitledBorder("Big particle properties");
		panel2.setBorder(Border2);
		button5 = new JButton("Edit");
		button5.addActionListener(bListener);
		panel2.add(button5);
		this.add(panel2);

		panel3 = new JPanel();
		Border Border3 = BorderFactory.createTitledBorder("General options");
		panel3.setBorder(Border3);
		
		button1 = new JButton("ON");
		panel3.add(button1);
		
		button1.addActionListener(bListener);
		
		button2 = new JButton("BG COLOR");
		button2.addActionListener(bListener);
		panel3.add(button2);
		
		button4 = new JButton("BALL COLOR");
		button4.addActionListener(bListener);
		panel3.add(button4);
		
		button5 = new JButton("RESET");
		button5.addActionListener(bListener);
		panel3.add(button5);
		
		this.add(panel3);

	}
	
	
	public Timer timer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        		for(Particle p: animationPanel.particles){
        			p.wallTest(animationPanel);
        			p.collisionTest(animationPanel);
        			p.move();
        		}
            	animationPanel.repaint();
        }
	});
	
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == button2) {
				Color color1 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				animationPanel.setBgColor(color1);
				animationPanel.repaint();
			}
			
			else if(e.getSource() == button4) {
				Color color2 = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				animationPanel.setBallColor(color2);
				animationPanel.repaint();
			}
				
			else if(e.getSource() == button1) {
					if(button1.getText()=="ON"){
						button1.setText("OFF");
						timer.start();
					} else {
	    		timer.stop();
	    		button1.setText("ON");
					}
			}
			
			else if(e.getSource() == button3) {
				int oldN = animationPanel.N;
				int newN = oldN;
				try
				{
					newN = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new number of particles", "Edit number of the particles", JOptionPane.QUESTION_MESSAGE, null, null, oldN).toString());
					if(oldN == newN) { return; }
					animationPanel.setN(newN);
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			else if(e.getSource() == button7) {
				int oldR = animationPanel.Radius;
				int newR = oldR;
				try
				{
					newR = Integer.parseInt(JOptionPane.showInputDialog(Main.frame, "Enter the new radius", "Edit radius size", JOptionPane.QUESTION_MESSAGE, null, null, oldR).toString());
					if(oldR == newR) { return; }
					animationPanel.setRadius(newR);
				}
				catch(NumberFormatException nfe) { JOptionPane.showMessageDialog(Main.frame, "Input was an invalid number", "Invalid Input", JOptionPane.WARNING_MESSAGE, null); }
				catch(NullPointerException npe) {  }
			}
			
			else if(e.getSource() == button5) {
				animationPanel.reset();
			}
			
		}
	}
	
}
