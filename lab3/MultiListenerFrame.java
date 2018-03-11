package lab3;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class MultiListenerFrame extends JFrame{
	JButton button1;
	JLabel label1;
	JRadioButton radioButton1;
	
	public static void main(String[] args) {
		//wywoluje konstruktor
		new MultiListenerFrame();
	}
	//definiuje konstruktor
	public MultiListenerFrame() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
		this.setTitle("Multi Listener Frame");
			
		JPanel thePanel = new JPanel();
		
		label1 = new JLabel("Hello");
		thePanel.add(label1);

		labelListener lLabel = new labelListener();
		button1 = new JButton("Zmien napis na etykiecie");

		button1.addActionListener(lLabel);
		thePanel.add(button1);
		
		radioButton1 = new JRadioButton("Zakoncz");
		radioButton1.setActionCommand("0");
		radioButton1.addActionListener(exitListener);
		radioButton1.setSelected(true);
		thePanel.add(radioButton1);
		
		
		String[] colors = {"red", "green", "blue"};
		JComboBox<String> colorList = new JComboBox<String>(colors);
		thePanel.add(colorList, BorderLayout.PAGE_START);
		ComboBoxItemListener comboListener = new ComboBoxItemListener(thePanel);
		colorList.addItemListener(comboListener);
		
		this.add(thePanel);

		this.setVisible(true);

	}
	
	ActionListener exitListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
					
		}	
	};

	//Implementuje Listenery

	private class labelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			label1.setText("Brawo, zmieniles napis na etykiecie");		
		}
	}
}
