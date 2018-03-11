package lab2;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Example3 {
	
	public static void main(String[] args) {
		CloseableFrame frame = new CloseableFrame();
		//Wykorzystano tutaj wygodny GridLayout, który pionowo podzieli³ ramkê 
		//na dwie równe czêœci. W ka¿dej po³owie okna umieszczono jeden panel, 
		//a w ka¿dym panelu jedn¹ etykietê.
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
