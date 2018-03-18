package lab4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;

public class CentralPanel extends JPanel{
	Graphics2D graphics2d;
	RightPanel rightPanel;
	Polygon polygon;

	public CentralPanel(RightPanel right) {
		this.rightPanel = right;
		this.setPreferredSize(new Dimension(600, 450));
		this.setBackground(BottomPanel.getBgColor());
		setPolygon(rightPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke(Menu.getLineWidth()));
		graphics2d.setColor(BottomPanel.getLnColor());
		graphics2d.drawPolygon(polygon);
	}

	public void setPolygon(RightPanel rightPanel) {
		int length = rightPanel.getList1().size();
		int[] xArr = new int[length];
		int[] yArr = new int[length];

		for (int i = 0; i < length; i++) {
			xArr[i] = Integer.parseInt(rightPanel.getList1().get(i).getText());
			yArr[i] = Integer.parseInt(rightPanel.getList2().get(i).getText());
		}
		polygon = new Polygon(xArr, yArr, length);
	}
	
	void setPolygon(int[] xArr, int[] yArr, int len) {
		polygon = new Polygon(xArr, yArr, len);
	}
}

