package lab4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RightPanel extends JPanel{
	JLabel label1, label2;
	JPanel panel1, panel2;
	List<JTextField> list1, list2;
	LeftPanel leftPanel;

	public RightPanel(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
		
		this.setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		
		label1 = new JLabel("X pos");
		this.add(label1);
		label2 = new JLabel("Y pos");
		this.add(label2);
		
		panel1.add(label1);
		panel1.add(label2);
		
		this.add(panel1, BorderLayout.PAGE_START);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(UpperPanel.getVertices(), 2));
		list1 = new ArrayList<>(UpperPanel.getVertices());
		list2 = new ArrayList<>(UpperPanel.getVertices());
		
		this.add(panel2, BorderLayout.CENTER);
		
		calc();
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		
		label1 = new JLabel("X pos");
		this.add(label1);
		label2 = new JLabel("Y pos");
		this.add(label2);
		
		panel1.add(label1);
		panel1.add(label2);
		
		this.add(panel1, BorderLayout.PAGE_START);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(UpperPanel.getVertices(), 2));
		
		list1 = new ArrayList<>(UpperPanel.getVertices());
		list2 = new ArrayList<>(UpperPanel.getVertices());
		
		this.add(panel2, BorderLayout.CENTER);
	}
	
	public void calc() {
		if (leftPanel.radio1.isSelected()) {
			for (int i = 0; i < UpperPanel.getVertices(); i++) {
				list1.add(i, new JTextField(
						(int) (200 * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / UpperPanel.getVertices()) + 320) + ""));
				panel2.add(list1.get(i));

				
				list2.add(i, new JTextField(
						(int) (200 * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / UpperPanel.getVertices()) + 220) + ""));
				panel2.add(list2.get(i));
			}
			
		} else if(leftPanel.radio2.isSelected()) {
			for (int i = 0; i < UpperPanel.getVertices(); i++) {
				Random rand = new Random();
				
				list1.add(i, new JTextField((int) (200*(rand.nextDouble()-0.5)+320) + ""));
				panel2.add(list1.get(i));
				
				list2.add(i, new JTextField((int) (200*(rand.nextDouble()-0.5)+220) + ""));
				panel2.add(list2.get(i));
			}
		}
	}
	
	void update () {
		this.removeAll();
		list1.clear();
		list2.clear();
		init();
		this.revalidate();
		this.repaint();
	}

	public List<JTextField> getList1() {
		return list1;
	}

	public List<JTextField> getList2() {
		return list2;
	}
	
}
