package lab2;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Example3 {
	
	public static void main(String[] args) {
		CloseableFrame frame = new CloseableFrame();
		//Wykorzystano tutaj wygodny GridLayout, ktory pionowo podzielil ramke 
		//na dwie rowne czesci. W kazdej polowie okna umieszczono jeden panel, 
		//a w kazdym panelu jedna etykiete.
		frame.setLayout(new GridLayout(1,2));
			
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		frame.add(panel1);
			
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.black);
		frame.add(panel2);
			
		JLabel leftLabel = new JLabel("Lewy panel");
		panel1.add(leftLabel);
		JLabel rightLabel = new JLabel("Prawy panel");
		rightLabel.setForeground(Color.white);
		panel2.add(rightLabel);

		frame.setVisible(true);
	}
}
