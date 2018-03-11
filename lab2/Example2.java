package lab2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Example2 {
	public static void main(String[] args)
	{
		CloseableFrame frame = new CloseableFrame();
		
		JButton przycisk1 = new JButton("Przycisk 1");
		JButton przycisk2 = new JButton("Przycisk 2");
		
		//BorderLayout wyró¿nia 5 obszarów okna: górny (PAGE_START), dolny (PAGE_END), 
		//lewy (LINE_START), prawy (LINE_END) i centralny (CENTER)
		
		frame.add(przycisk1, BorderLayout.PAGE_START);
		frame.add(przycisk2, BorderLayout.PAGE_END);
		
		JLabel label = new JLabel("To jest etykieta");
		frame.add(label);
		
		JTextField field = new JTextField("A to pole tekstowe");
		frame.add(field);
		
		//GridLayout jest bardzo wygodnym managerem, który dzieli okno na siatkê
		//u ustalonej liczbie kolumn i wierszy. 
		
		frame.setLayout(new GridLayout(2,2));
		
		frame.getContentPane().setBackground(Color.blue);
		
		frame.setVisible(true);
		
	}
}
