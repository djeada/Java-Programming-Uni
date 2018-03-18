package lab4;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	JButton button1, button2;
	CentralPanel centralPanel;
	static Color lnColor = Color.black;
	static Color bgColor = Color.white;

	public BottomPanel(CentralPanel central) {
		this.centralPanel = central;
		
		button1 = new JButton("BG COLOR");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		this.add(button1);
		
		button2 = new JButton("LN COLOR");
		button2.addActionListener(lForButton);
		this.add(button2);
	}
	
	private class ListenForButton implements ActionListener {				
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				Color color = JColorChooser.showDialog(null, "Change background color", Color.WHITE);
				setBgColor(color);
				centralPanel.setBackground(color);
				centralPanel.repaint();
			} else if (e.getSource() == button2) {
				Color color = JColorChooser.showDialog(null, "Change line color", Color.BLACK);
				setLnColor(color);
				centralPanel.repaint();
				}
			}
	}
	
	public static void setLnColor(Color lnColor) {
		BottomPanel.lnColor = lnColor;
	}
	
	public static void setBgColor(Color bgColor) {
		BottomPanel.bgColor = bgColor;
	}
	
	public static Color getLnColor() {
		return lnColor;
	}
	
	public static Color getBgColor() {
		return bgColor;
	}
	
}

