package lab2;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class CloseableFrame extends JFrame {
    public CloseableFrame() throws HeadlessException {
    	//Metoda super() widoczna wewn¹trz konstruktorów s³u¿y do wywo³ania konstruktora 
    	//klasy nadrzêdnej (w tym wypadku JFrame). Znajduje siê ono zawsze na pocz¹tku konstruktora, 
    	//dziêki czemu mo¿na po nim dodaæ dalszy kod, który sprawi, ¿e klasa CloseableFrame 
    	//bêdzie rozszerzeniem klasy JFrame.
    	super();
		this.setSize(640,480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(GraphicsConfiguration gc) {
        super(gc);
        this.setSize(640,480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(String title) throws HeadlessException {
        super(title);
		this.setSize(640,480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public CloseableFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.setSize(640,480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
    	CloseableFrame frame = new CloseableFrame();
		frame.setVisible(true);
    }
}
