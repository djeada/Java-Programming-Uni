package lab5;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JMenuBar {

	PadDraw drawPad;
	JMenu file;
	JMenuItem open, save;

	public Menu(PadDraw drawPad) {
		this.drawPad = drawPad;
		file = new JMenu("File");

		ListenForMenu lForMenu = new ListenForMenu();

		open = new JMenuItem("Open");
		open.addActionListener(lForMenu);
		file.add(open);
		
		save = new JMenuItem("Save");
		save.addActionListener(lForMenu);
		file.add(save);

		this.add(file);
	}

	
	private class ListenForMenu implements ActionListener {				
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == open) {
				saveToFile(drawPad);
			}
			else if(e.getSource() == save) {
				openFile(drawPad);
			}

			else {
				System.exit(0);
			}
		}
	}
	
	public void saveToFile(PadDraw drawPad)  {
	            JFileChooser chooseDirec = new JFileChooser();
	            chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            chooseDirec.showSaveDialog(this);
	            File file = chooseDirec.getSelectedFile();
	            file = new File(file+".png");
	             
	            BufferedImage image=new BufferedImage(
	                   drawPad.getWidth(), drawPad.getHeight(),
	                   BufferedImage.TYPE_INT_RGB);
	            Graphics2D g2=(Graphics2D)image.getGraphics();
	           drawPad.paint(g2);
	        try
	        {
	            ImageIO.write(image, "png", new File("/tmp/image.png"));
	        }
	        catch (Exception e)
	             
	        {
	        }
	    	
	}

	public void openFile(PadDraw drawPad) {
        JFileChooser chooseDirec = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphic files", "jpg", "gif", "png", "bmp");
	    chooseDirec.setFileFilter(filter);
	    int returnVal = chooseDirec.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	BufferedImage image = null;
            try {
            	image = ImageIO.read(chooseDirec.getSelectedFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
            drawPad.setBackgroundImage(image);
	    }
	}
}