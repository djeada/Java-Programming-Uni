package lab8b;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

public class ChartPDF extends JFrame {
	List<Double> List1 = new ArrayList<>();
	List<Double> List2 = new ArrayList<>();
	List<String> List3 = new ArrayList<>();
	List<String> List4 = new ArrayList<>();

	public ChartPDF() throws IOException {
		try {
			JFreeChart chart = createChart(createDataset());
			
			PDFDocument pdfDoc = new PDFDocument();
			pdfDoc.setTitle("ChartPDF3D");
			pdfDoc.setAuthor("Adam Djellouli");
			
			Page page1 = pdfDoc.createPage(new Rectangle(800, 450));
			PDFGraphics2D g1 = page1.getGraphics2D();
			chart.draw(g1, new Rectangle(0, 0, 800, 450));
			
			Page page2 = pdfDoc.createPage(new Rectangle(0, 0, 800, 450));
			PDFGraphics2D g2 = page2.getGraphics2D();
			drawTable(g2);
			
			Page page3 = pdfDoc.createPage(new Rectangle(0, 0, 800, 450));
			PDFGraphics2D g3 = page3.getGraphics2D();
			g3.setPaint(Color.GREEN);
			g3.drawOval(350, 400, 50, 50);
	        g3.fillOval(500, 200, 400, 100);
	        g3.fillRect(0, 0, 80, 320);
			
			BufferedImage img = ImageIO.read(new File("pies.jpg"));
			g3.drawImage(img, 300, 150, 200, 200,null);		
			
			pdfDoc.writeToFile(new File("ChartPDF3D.pdf"));
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Methods
	
	private void drawTable(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawString("X", 60, 30);
		g.drawString("||", 100, 30);
		g.drawString("Y", 120, 30);
		g.drawString("||", 160, 30);
		g.drawString("row", 180, 30);
		g.drawString("||", 220, 30);
		g.drawString("col", 240, 30);
		for(int i = 0; i < List1.size(); ++i) {
			g.drawString(List1.get(i)+"", 60, 45 + i*15);
			g.drawString("||", 100, 32 + i*15);
			g.drawString(List2.get(i)+"", 120, 45 + i*15);
			g.drawString("||", 160, 32 + i*15);
			g.drawString(List3.get(i), 180, 45 + i*15);
			g.drawString("||", 220, 32 + i*15);
			g.drawString(List4.get(i), 240, 45 + i*15);
		}
	}
	
	private CategoryDataset createDataset() {
		readInFile();
		DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
		for (int i = 0; i < List1.size(); i++) {
			dataset.add((double)List1.get(i), (double)List2.get(i), List3.get(i), List4.get(i));
		}
		return dataset;
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart3D("Statistical Bar Chart", "Type", "Value", dataset);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		 
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(false);
		 
		// customise the renderer...
		StatisticalBarRenderer renderer = new StatisticalBarRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setErrorIndicatorPaint(Color.black);
		renderer.setIncludeBaseInRange(false);
		plot.setRenderer(renderer);
		 
		// ensure the current theme is applied to the renderer just added
		ChartUtilities.applyCurrentTheme(chart);
		 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Color.yellow);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition());
		 
		// set up gradient paints for series...
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		 
		return chart;
	}


	private void readInFile() {
		try {
			List1.clear();
			List2.clear();
			List3.clear();
			List4.clear();
			
			File file = new File("data.txt"); 
			BufferedReader fin = new BufferedReader(new FileReader(file));
			String line=null;
			while((line=fin.readLine()) !=null){
		       String [] tab = line.split("\\s+");
		       List1.add(Double.parseDouble(tab[0]));
		       List2.add(Double.parseDouble(tab[1]));
		       List3.add(tab[2]);
		       List4.add(tab[3]);
			}
			fin.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(this,"Editor can't find the file called data.txt");
		}
	}
	
	public static void main(String[] args) throws IOException {
		ChartPDF chart = new ChartPDF();
	}

}

