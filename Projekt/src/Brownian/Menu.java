package Brownian;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu extends JMenuBar {
	
	JMenu option1, option2, Help;    
	JMenuItem option11, option12, option13, option21, option22, option23;
	JTextField help;
	

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
		
		help = new JTextField(20);
		help.setText("What's bothering you?");
		Help.add(help);
		//JTextField mTextField = new JTextField();
	    help.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	String url = help.getText();
	            	url = url.replace(" ", "+");
	            	url = "http://lmgtfy.com/?q=" + url;
	            	System.out.println(url);
	            	URL adres;
	            	try
	            	{
	            		adres = new URL(url);
	            		Desktop d = Desktop.getDesktop();
	            		d.browse(new URI(url));
	            	}
	            	catch (MalformedURLException ee) {
	            		ee.printStackTrace();
	            	} catch (IOException eee) {
	            		eee.printStackTrace();
	            	} catch (URISyntaxException e1) {
	            		e1.printStackTrace();
				}
	            }
	        }
	    });
		
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
