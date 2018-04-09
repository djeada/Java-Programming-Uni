package lab5;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JMenuBar {

	Paint parent;
	JMenu file;
	JMenuItem open, save;

	public Menu(Paint parent) {
		this.parent = parent;
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
				openFile(parent);

			}
			else if(e.getSource() == save) {
				saveToFile(parent);

			}

			else {
				System.exit(0);
			}
		}
	}
	
	public void saveToFile(Paint parent)  {
		BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();
		parent.drawPad.paintComponent(g);

		JFileChooser chooseDirec = new JFileChooser();
		int returnVal = chooseDirec.showDialog(null, "wybierz");
		chooseDirec.setDialogTitle("wybierz");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				ImageIO.write(img, "png",chooseDirec.getSelectedFile());
				System.out.println("Plik zapisano poprawnie");
			} catch (IOException e1) {
				System.out.print("PLIK NIE ZOSTA£ ZAPISANY");
			}

		}
	    	
	}

	public void openFile(Paint parent) {
        JFileChooser chooseDirec = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Graphic files", "jpg", "gif", "png", "bmp");
	    chooseDirec.addChoosableFileFilter(filter);
		int returnVal = chooseDirec.showDialog(null, "Open");
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	BufferedImage image = null;
			try {
				image = ImageIO.read(chooseDirec.getSelectedFile());
				System.out.println("Plik otworzono poprawnie");
			} catch (IOException ex) {
				System.out.print("PLIK NIE ZOSTA£ POPRAWNIE OTWORZONY");
			}
			parent.drawPad.setBackgroundImage(image);
	    }
	  
	}
	
}