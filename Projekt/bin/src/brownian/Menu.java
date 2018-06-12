package brownian;

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
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Menu with options
 * 
 * @author WP
 *
 */

public class Menu extends JMenuBar implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu options, data, help;    
	JMenuItem snapShot, info, exit, option21, option22, option23;
	JTextField helpTextField;

	JFileChooser choose;
	
	//AnimationPanel animationPanel;
	Board board;

	static BufferedImage image;
	static File imageFile = new File("img/icon.png");
	static File infoFile = new File("info.txt");
	
	public Menu(){   
		
		// Create JMenuBar components
		options = new JMenu("Options");    
		data = new JMenu("Data");    
		help = new JMenu("Help");
		
		// Create JMenuItems
		snapShot = new JMenuItem("Take a snap shot");    
		info = new JMenuItem("Info");    
		exit = new JMenuItem("Exit");    
		option21 = new JMenuItem("option21");
		option22 = new JMenuItem("option22");
		option23 = new JMenuItem("option23");
		
		// Create Help JMenuItem
		helpTextField = new JTextField(20);
		helpTextField.setText("What's bothering you?");
		help.add(helpTextField);
		
		
		// Help option Listener
	    helpTextField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	String url = helpTextField.getText();
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
		
	    // Add JMenuItems and its listeners to MenuBar
		options.add(snapShot);
		options.add(info);
		options.add(exit);
		
		data.add(option21);
		data.add(option22);
		data.add(option23);
		
		snapShot.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		
				
		this.add(options);
		this.add(data);
		this.add(help); 
		
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
	
		// Open description window
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
		
		// Open exit dialog window
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
	
	// Method used to read a file
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
