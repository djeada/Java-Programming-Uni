package real;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	JMenuBar menuBar;    
	JMenu line,edit,help;    
	JMenuItem line1px, line2px, line5px, cut,copy,paste,selectAll;    
	
	public Menu(){   
		
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
			
			}

		
		}	
}
