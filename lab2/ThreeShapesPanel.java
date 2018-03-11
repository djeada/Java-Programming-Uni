package lab2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ThreeShapesPanel extends JPanel {
	
	// trzy ró¿ne figury geometryczne o ró¿nych, losowych kolorach.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//losowa liczba
		Random losowa = new Random(); 
		float kolory[][] = new float[3][3];
		for(int i=0; i<3; i++) {
			//Generownie RGB:
			for(int j=0; j<3; j++) {
				kolory[j][i]=losowa.nextFloat();
			}
			//Tworzenie nowego losowego koloru:
			Color c = new Color(kolory[i][0],kolory[i][1],kolory[i][2]);
			g.setColor(c);
			int figura = losowa.nextInt() % 3;
			switch(figura)
			{
				case 0:
					//wype³nia prostok¹t kolorem t³a obiektu nadrzêdnego typu Component.
					//int x, int y, int width, int height
					g.fillRect(50, 50, 150, 100);
					break;
				case 1:
					g.fillOval(50, 50, 150, 100);
					break;
				case 2:
					//Wielobok;
					int x[] = {50,150,150,90};
					int y[] = {50,150,50,90};
					g.fillPolygon(x, y, 3);
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		CloseableFrame closeable = new CloseableFrame();
		
		// w oknie CloseableFrame umieszczone by³y dwa panele tej samej wielkoœci,
		closeable.setLayout(new GridLayout(1,2));
		
		//jeden klasy ThreeShapesPanel
		ThreeShapesPanel panel = new ThreeShapesPanel();
		closeable.add(panel);
		
		//a drugi JPanel
		//W panelu klasy JPanel umieœæ dowolne cztery komponenty ustawione jeden nad drugim. 
		JPanel orginalPanel = new JPanel();
		orginalPanel.setLayout(new GridLayout(4,1));
		JButton button1 = new JButton("To jest pierwszy guziczek");
		orginalPanel.add(button1);
		JButton button2 = new JButton("To jest drugi guziczek");
		orginalPanel.add(button2);
		JTextField pole_tekstowe = new JTextField("Mo¿esz tu pisaæ");
		pole_tekstowe.setToolTipText("To jest pole");
		orginalPanel.add(pole_tekstowe);
		JLabel label1  = new JLabel("To nic nie robi");
		orginalPanel.add(label1);
	
		closeable.add(orginalPanel);
		
		closeable.setVisible(true);
	}

}
