package lab4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class LeftPanel extends JPanel{
	JRadioButton radio1, radio2;
	static boolean regular = true;
	
	public LeftPanel() {
		radio1 = new JRadioButton("Regular");
		radio2 = new JRadioButton("Random");
				
		ButtonGroup operation = new ButtonGroup();
		operation.add(radio1);
		operation.add(radio2);
		
		//we want them to be surrounded by a border
		Border leftBorder = BorderFactory.createTitledBorder("Polygon");
		
		this.setBorder(leftBorder);
		
		//you can't add the whole group all at once
		//you have to add each one separately
		this.add(radio1);
		this.add(radio2);
		
		//Regular will be selected by default
		radio1.setSelected(true);
		
		this.setLayout(new GridLayout(2, 1));
	}
	
	
}
