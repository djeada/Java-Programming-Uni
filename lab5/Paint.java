package lab5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Paint extends JFrame{
	   
	public static int ksztalt, grubosc;
	JButton colorButton, clearButton;
	JSpinner spinner;
	PadDraw drawPad;
	Menu menu;
	JComboBox<String> figure;
	static Color color = Color.WHITE;
	
	public Paint (PadDraw drawPad) {
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Paint");
		setLayout(new BorderLayout());
		
		this.drawPad = drawPad;
		this.add(drawPad, BorderLayout.CENTER);

		menu = new Menu(this);
		this.setJMenuBar(menu);

		
		JPanel panel = new JPanel(new GridLayout(2, 2));

		//ComboBox Figure
		figure = new JComboBox<String>();
		figure.setEditable(true);
		figure.addItem("Olowek");
		figure.addItem("Linia");
		figure.addItem("Prostokat");
		figure.addItem("Owal");
		figure.addItem("Gumka");
		ListenForComboBox lCombo = new ListenForComboBox();
		figure.addActionListener(lCombo);
		panel.add(figure);

		//Spinner
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 0, 100, 1);  
		spinner = new JSpinner(model1);
		ListenForSpinner lForSpinner = new ListenForSpinner();
		spinner.addChangeListener(lForSpinner);
		panel.add(spinner);

		//Clear Button
		clearButton = new JButton("Czysc");
		ListenForButton lForButton = new ListenForButton();
		clearButton.addActionListener(lForButton);
		panel.add(clearButton);

		//Color Button
		colorButton = new JButton("Wybierz kolor");
		colorButton.addActionListener(lForButton);
		panel.add(colorButton);

		this.add(panel, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	
	private class ListenForButton implements ActionListener {				
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == clearButton) {
				drawPad.clear();
			} else if (e.getSource() == colorButton) {
				color = JColorChooser.showDialog(null, "Wybierz kolor",color);
				drawPad.choosencolor(color);
			}
		}
	}
	
	private class ListenForComboBox implements ActionListener {				
		public void actionPerformed(ActionEvent e) {
			if (figure.getSelectedIndex() == 0) {
				ksztalt=0;
			}
			if (figure.getSelectedIndex() == 1) {
				ksztalt=1;
			}
			if (figure.getSelectedIndex() == 2) {
				ksztalt=2;
			}
			if (figure.getSelectedIndex() == 3) {
				ksztalt=3;
			}
			if (figure.getSelectedIndex() == 4) {
				ksztalt=4;
			}
		}
	}
	
	private class ListenForSpinner implements ChangeListener {				
		@Override
		public void stateChanged(ChangeEvent e) {
			drawPad.sizespinner((Integer) spinner.getValue());
		}
	}
		
		
	public static void main(String [] args) {
			PadDraw drawPad = new PadDraw();
			new Paint(drawPad);
       }
}

