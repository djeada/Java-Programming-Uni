package lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class PadDraw extends JComponent {
		private static final long serialVersionUID = 1L;
		public static Image image;
		Graphics2D graphics2D;
		int currentX, currentY, oldX, oldY;
		int squareX,squareY,squareW,squareH;
		int xstart, ystart, xend, yend;
		int width, height;


		public PadDraw() {
			setDoubleBuffered(false);
			
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					oldX = e.getX();
					oldY = e.getY();
				}
			});
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					currentX = e.getX();
					currentY = e.getY();
					if (graphics2D != null)
						linia();
					
					oldX = currentX;
					oldY = currentY;
				}

			
			});
			
			
				
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						squareX = e.getX();
						squareY = e.getY();

					}

					public void mouseReleased(MouseEvent e) {
						if (e.getX() > squareX)
							squareW = e.getX() - squareX;
						else {
							squareW = squareX - e.getX();
							squareX = e.getX();
						}
						if (e.getY() > squareY)
							squareH = e.getY() - squareY;
						else {
							squareH = squareY - e.getY();
							squareY = e.getY();
						}
						if (graphics2D != null)
							owal();
						if (graphics2D != null)
							prostokat();
						
					}

				});
				
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						oldX = e.getX();
						oldY = e.getY();
					}
				});
				addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent e) {
						currentX = e.getX();
						currentY = e.getY();
						if (graphics2D != null)
							kropeczki();
						
						oldX = currentX;
						oldY = currentY;
					}

				
				});
				
				addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseDragged(MouseEvent e) {
						currentX = e.getX();
						currentY = e.getY();
						if (graphics2D != null)
							gumka();
						
						oldX = currentX;
						oldY = currentY;
					}

				
				});
		
			}

			public void paintComponent(Graphics g) {
			if (image == null) {
				image = createImage(getSize().width, getSize().height);
				graphics2D = (Graphics2D) image.getGraphics();
				graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				clear();

			}
			g.drawImage(image, 0, 0, null);
			
			width = this.getWidth();
			height = this.getHeight();
		}

		public void clear() {
			graphics2D.setPaint(Color.white);
			graphics2D.fillRect(0, 0, getSize().width, getSize().height);
			graphics2D.setPaint(Color.black);
			repaint();
		}


		public void choosencolor(Color color) {
			graphics2D.setPaint(color);
			repaint();
		}

		
		public void sizespinner(int liczba) {
			graphics2D.setStroke(new BasicStroke(liczba));
			repaint();
		}
		
		public void owal() {
			if(Paint.ksztalt==3)
			graphics2D.drawOval(squareX, squareY, squareW, squareH);
			repaint();
		}
		
		public void prostokat() {
			if(Paint.ksztalt==2)
			graphics2D.drawRect(squareX, squareY, squareW, squareH);
			repaint();
		}
		
		public void linia() {
			if(Paint.ksztalt==1)
			graphics2D.drawLine(oldX, oldY, currentX, currentY);
			repaint();
		}
		
		public void kropeczki() {
			if(Paint.ksztalt==0)
			graphics2D.drawLine(oldX, oldY, currentX, currentY);
			repaint();
		}
		
		public void gumka() {
			if(Paint.ksztalt==4)
			graphics2D.setPaint(Color.WHITE);
			graphics2D.drawLine(oldX, oldY, currentX, currentY);
			repaint();
		}
		
		public void setBackgroundImage(BufferedImage image) {
			this.image = image;
			double imageRatio = (double) image.getWidth() / image.getHeight();

			int newWidth = width;
			int newHeight = height;

			if ((double) width / height > imageRatio)
				newWidth = (int) imageRatio * height;
			else
				newHeight = (int) (width / imageRatio);

			Image img = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			BufferedImage bgImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D bGr = bgImage.createGraphics();
			bGr.drawImage(img, 0, 0, null);
			bGr.dispose();
			image = bgImage;
			repaint();

		}
}

