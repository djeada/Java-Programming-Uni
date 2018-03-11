package lab3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MedianFrame extends JFrame{
	
	JButton button1, button2;
	JTextField area1, area2;
	JLabel label1, label2;
	ArrayList<Double> liczby = new ArrayList<Double>();
	
	public static void main(String[] args) {
		new MedianFrame();
	}
	
	//konstruktor
	public MedianFrame(){
		//tworze okienko o wymiarach 400x400
		this.setSize(400, 400);
								
		//ustawiam okienko na srodku ekranu
		this.setLocationRelativeTo(null);
								
		//okienko zamknie sie po nacisinieciu x
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		//tworze nowy obiekt klasy JPanel - panel
		JPanel thePanel = new JPanel();
		
		//tworze obiekt klasy JTextField - pole tekstowe o szerokosci 8
		area1 = new JTextField();
		area1.setColumns(8);
		thePanel.add(area1);
		
		//tworze dwa obiekty klasy JButton - przycisk "dodaj" i przycisk "mediana"
		button1 = new JButton("Dodaj");
		button1Listener lbutt1 = new button1Listener();
		button1.addActionListener(lbutt1);
		thePanel.add(button1);
		
		button2 = new JButton("Mediana");
		button2Listener lbutt2 = new button2Listener();
		button2.addActionListener(lbutt2);
		thePanel.add(button2);
		
		//tworze dwa obiekty klasy JLabel - etykiety do wyswietlenia zbioru liczb i mediany
		label1 = new JLabel("=");
		thePanel.add(label1);
				
		label2 = new JLabel("Zbior liczb");
		thePanel.add(label2);
		
		this.add(thePanel);

		//sprawiam ze pojawia sie okno
		this.setVisible(true);
	}
	
	//Implementuje metody
	
	//metoda znajdowania mediany
	private double median(){
		double result = 0;
		int middle = 0;
		
		Collections.sort(liczby);
		
		//dla nieparzystej
		
		if (liczby.size()%2 != 0){
			middle = liczby.size()/2;
			result = liczby.get(middle);
		}
		
		//dla parzystej
		else {
			middle = liczby.size()/2-1;
			result = liczby.get(middle);
		}
		
		return result;
	}

	//metoda wyswietlajca wszystkie liczby z listy na etykiecie
	private void displayList() {
		String listString = liczby.toString();
		label2.setText("Zbior liczb " + listString);
	}
	
	//metoda wyswietlajaca mediane z liczb na etykiecie
	private void displayMedian(double a) {
		String doubleString = Double.toString(a);
		label1.setText("= " + doubleString);
	}
	
	//Implementuje listenery
	
	//Listener dla pierwszego guzika
	private class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				try{
					//dodaje liczbe z pola tekstowego do mojej listy
					liczby.add(Double.parseDouble(area1.getText()));
					displayList();
				}
				
				//przy errorze zamyka ca³¹ apkê
				catch(NumberFormatException excep){	
					JOptionPane.showMessageDialog(MedianFrame.this, "Wpisales cos bardzo zlego", "Error", JOptionPane.ERROR_MESSAGE);System.exit(0);
				}
			}	
		}
	}
	
	//Listener dla drugiego guzika
	private class button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button2) {
				displayList();
				//obliczam mediane
				double wynik = median();
				displayMedian(wynik);
			}
		}
	}
}


