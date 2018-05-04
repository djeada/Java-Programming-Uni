package lab8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyChart {
	static Scanner scanner; 
	static XYSeries series; 
	JMenuBar menu;
	JMenu opcje;
	JMenuItem otworz;
	
	public MyChart() {
		series = new XYSeries("Seria");
		menu = new JMenuBar();
		opcje = new JMenu("opcje");
		otworz = new JMenuItem("otworz");
		
		otworz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
				int result = chooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION)
					plotResult(chooser.getSelectedFile());
			}
		});
		
		opcje.add(otworz);
		menu.add(opcje);
		
		XYDataset xyDataset = new XYSeriesCollection(series); 
		JFreeChart chart = ChartFactory.createXYLineChart 
				("Y = F(X)", "X", "Y",xyDataset, PlotOrientation.VERTICAL, true, true, false); 
		ChartFrame frame1=new ChartFrame("XYLine Chart",chart); 
		frame1.setJMenuBar(menu);
		frame1.setVisible(true); 
		frame1.setSize(600,400); 
	}
			
	
	public static void plotResult(File file) { 
		try {
			int index = 0; 
			scanner = new Scanner(file); 
			while (scanner.hasNextDouble()) { 
				series.add(index++,scanner.nextDouble()); 
			} 
			scanner.close(); 
		} 
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} 
		
	}
	
	public static void main(String[] args) { 
		MyChart chart = new MyChart();
	}
}