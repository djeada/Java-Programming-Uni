package Brownian;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Menu with options
 * 
 * @author WP
 *
 */

public class Menu extends JMenuBar implements ActionListener {
	
	JMenu option1, option2, Help;    
	JMenuItem snapShot, info, exit, option21, option22, option23;
	JTextField help;

	JFileChooser choose;
	
	//AnimationPanel animationPanel;
	Board board;

	static BufferedImage image;
	static File imageFile = new File("icon.png");
	static File infoFile = new File("info.txt");
	
	public Menu(){   
		
		option1 = new JMenu("Options");    
		option2 = new JMenu("Data");    
		Help = new JMenu("Help");
		
		snapShot = new JMenuItem("Take a snap shot");    
		info = new JMenuItem("Info");    
		exit = new JMenuItem("Exit");    
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
		
		option1.add(snapShot);
		option1.add(info);
		option1.add(exit);
		
		option2.add(option21);
		option2.add(option22);
		option2.add(option23);
		
		snapShot.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		
				
		this.add(option1);
		this.add(option2);
		this.add(Help); 
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		/*
		//snap shot 
		if (ob == snapShot) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(null, "Save");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				BufferedImage image = new BufferedImage(animationPanel.getWidth(), animationPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = image.createGraphics();
				animationPanel.paintAll(g2d); //trzeba cos poprawic, zeby faktycznie w g2d byl nasz animationPanel
				try {
					ImageIO.write(image, "png", fc.getSelectedFile());
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}

			}
		}
		*/
	
		//brief description of phenomenon
		if (ob == info) {
			try {
				FileInputStream isr = new FileInputStream(infoFile);
				String description = getFileContent(isr);
				JOptionPane pane = new JOptionPane();
				image = ImageIO.read(imageFile);
				Icon icon = new ImageIcon(image);
				pane.showMessageDialog(null, description, "Physics behind simulation", JOptionPane.INFORMATION_MESSAGE, icon);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		//exit window
		if (ob == exit) {
			JOptionPane exitPane = new JOptionPane();
			int choice = exitPane.showConfirmDialog(null, "Exit?", "hsjks", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				System.exit(0);
			}
			if(choice != 0) {
				exitPane.setVisible(false);
			}
		}
		
	} 
	
	//reading file method
	public static String getFileContent(FileInputStream fis) throws IOException {
		try( BufferedReader br = new BufferedReader( new InputStreamReader(fis))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while(( line = br.readLine()) != null ) {
				sb.append( line );
				sb.append( '\n' );
			}
			return sb.toString();
		}
	}
	
}
