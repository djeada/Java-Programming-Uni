package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	JMenuBar menuBar;    
	JMenu line,edit,help;    
	JMenuItem line1px, line2px, line5px, cut,copy,paste,selectAll;    
	static int lineWidth = 1;
	CentralPanel centralPanel;
	
	public Menu(CentralPanel central){   
		this.centralPanel = central;
		
		cut = new JMenuItem("cut");    
		copy = new JMenuItem("copy");    
		paste = new JMenuItem("paste");    
		selectAll = new JMenuItem("selectAll");
		line1px = new JMenuItem("1px");
		line2px = new JMenuItem("2px");
		line5px = new JMenuItem("5px");

		ListenForMenu lForMenu = new ListenForMenu();
		
		menuBar = new JMenuBar();    
		line = new JMenu("Line Width");    
		edit = new JMenu("Edit");    
		help = new JMenu("Help");  
		
		line.add(line1px);
		line.add(line2px);
		line.add(line5px);

		line1px.addActionListener(lForMenu);
		line2px.addActionListener(lForMenu);
		line5px.addActionListener(lForMenu);

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectAll);
		
		menuBar.add(line);
		menuBar.add(edit);
		menuBar.add(help); 
		
		this.add(menuBar);
	}    
	
	private class ListenForMenu implements ActionListener {				
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == line1px) {
				setLineWidth(1);
				centralPanel.repaint();
			}
			else if(e.getSource() == line2px) {
				setLineWidth(2);
				centralPanel.repaint();
			}

			else if(e.getSource() == line5px) {
				setLineWidth(5);
				centralPanel.repaint();
			}

			else {
				System.exit(0);
			}
		}
	}
	
	public static int getLineWidth() {
		return lineWidth;
	}
	
	public static void setLineWidth(int a) {
		Menu.lineWidth = a;
	}
	
}
