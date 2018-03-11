package lab2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ThreeButtonFrame extends JFrame {
	
	JLabel label1;
	
	public ThreeButtonFrame(){
		
		//creating the frame and setting its size
		this.setSize(400, 400);
				
		//in the middle of the screen
		this.setLocationRelativeTo(null);
				
		//how the frame is going to exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		this.setTitle("Three Button Frame");
				
		label1 = new JLabel("Mi³e s³owa");
		this.add(label1);
		
		//W konstruktorze nowej klasy zainicjuj trzy przyciski 
		//i dodaj je do ramki ThreeButtonFrame. 
		this.setLayout(new GridLayout(3,1));
		
		
		JButton button1 = new JButton("Zmieñ kolor");
		button1.addActionListener(colorListener);
		this.add(button1);
		
		JButton button2 = new JButton("Zmieñ napis na etykiecie");
		button2.addActionListener(labelListener);
		this.add(button2);
		
		JButton button3 = new JButton("Zakoñcz");
		button3.addActionListener(exitListener);
		this.add(button3);
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		new ThreeButtonFrame();
		//ThreeButtonFrame frame = new ThreeButtonFrame();
		//frame.setVisible(true);
	}
	
	ActionListener colorListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
            if (source instanceof Component) {
                ((Component)source).setBackground(Color.RED);
            }
					
		}	
	};
	
	ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
					
		}	
	};
	
	ActionListener labelListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			label1.setText("Brawo, zmieni³ej napis na etykiecie");		
		}	
	};
	
}