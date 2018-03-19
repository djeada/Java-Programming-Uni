package Projekt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	JMenu option1, option2, Help;    
	JMenuItem option11, option12, option13, option21, option22, option23;
	JLabel label1;
	private JFrame frame;

	public Menu(){   
		
		option1 = new JMenu("option1");    
		option2 = new JMenu("option2");    
		Help = new JMenu("Help");
		
		option11 = new JMenuItem("save");    
		option12 = new JMenuItem("option12");    
		option13 = new JMenuItem("option13");    
		option21 = new JMenuItem("option21");
		option22 = new JMenuItem("option22");
		option23 = new JMenuItem("option23");
		
		option1.add(option11);
		option1.add(option12);
		option1.add(option13);
		
		option2.add(option21);
		option2.add(option22);
		option2.add(option23);
		
		option1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
				
		this.add(option1);
		this.add(option2);
		this.add(Help); 
		
	}    
}
