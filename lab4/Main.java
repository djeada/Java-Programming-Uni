package lab4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
	private Menu menu;
	private UpperPanel upperPanel;
	private LeftPanel leftPanel;
	private CentralPanel centralPanel;
	private RightPanel rightPanel;
	private BottomPanel bottomPanel;

	public Main () {
	
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		this.setTitle("Polygon");
		setLayout(new BorderLayout());
		
		upperPanel = new UpperPanel();
		this.add(upperPanel, BorderLayout.NORTH);
		
		leftPanel = new LeftPanel();
		this.add(leftPanel, BorderLayout.WEST);		
		
		rightPanel = new RightPanel(leftPanel);
		this.add(rightPanel, BorderLayout.EAST);
		
		centralPanel = new CentralPanel(rightPanel);
		this.add(centralPanel, BorderLayout.CENTER);	
		
		bottomPanel = new BottomPanel(centralPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);

		menu = new Menu(centralPanel);
		this.setJMenuBar(menu);
		
		this.setVisible(true);
	}
	
	
	void changeListner() {
		upperPanel.getSlider().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				UpperPanel.setVertices(upperPanel.getSlider().getValue());
				rightPanel.update();
				rightPanel.calc();
				rightPanel.revalidate();
				rightPanel.repaint();
				centralPanel.setPolygon(rightPanel);
				centralPanel.repaint();
			}
		});
		
		upperPanel.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(leftPanel.radio1.isSelected()) {
					int length = rightPanel.getList1().size();
					int[] xArr = new int[length];
					int[] yArr = new int[length];
					for(int i = 0; i < length; i++) {
						xArr[i] = Integer.parseInt(rightPanel.getList1().get(i).getText());
						yArr[i] = Integer.parseInt(rightPanel.getList2().get(i).getText());
					}
					centralPanel.setPolygon(xArr, yArr, length);
					
				}
				else {
					rightPanel.update();
					rightPanel.calc();
					rightPanel.revalidate();
					rightPanel.repaint();
					centralPanel.setPolygon(rightPanel);
				}
				
				centralPanel.repaint();
			}
		});
	}
	
	 public static void main(String[] args) {
	        Main main = new Main();
	        main.changeListner();
	 }

}
