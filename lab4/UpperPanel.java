package lab4;

import javax.swing.*;

public class UpperPanel extends JPanel{
	JButton button1;
	JLabel label1;
	JSlider howManyTimes;
	public static int vertices = 3;
	
	public UpperPanel(){
		
		label1 = new JLabel("No. of vertices");
		this.add(label1);
	
		//Parameters are min value, max value, initial value
		howManyTimes = new JSlider(3,33,3);
		howManyTimes.setMinorTickSpacing(1);
		howManyTimes.setMajorTickSpacing(10);
		//if we want them to show up on the screen
		howManyTimes.setPaintTicks(true);
		//labels that explain what happens
		howManyTimes.setPaintLabels(true);
		this.add(howManyTimes);
				
		button1 = new JButton("Draw");
		this.add(button1);
		
		vertices = howManyTimes.getValue();
	}
	
	//Getters
	public JSlider getSlider() {
		return howManyTimes;
	}

	public JButton getButton() {
		return button1;
	}
	
	public static int getVertices() {
		return vertices;
	}
	
	public static void setVertices(int vert) {
		UpperPanel.vertices = vert;
	}
}
