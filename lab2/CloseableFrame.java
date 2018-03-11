package lab2;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class CloseableFrame extends JFrame {
    public CloseableFrame() throws HeadlessException {
    	//Metoda super() widoczna wewn�trz konstruktor�w s�u�y do wywo�ania konstruktora 
    	//klasy nadrz�dnej (w tym wypadku JFrame). Znajduje si� ono zawsze na pocz�tku konstruktora, 
    	//dzi�ki czemu mo�na po nim doda� dalszy kod, kt�ry sprawi, �e klasa CloseableFrame 
    	//b�dzie rozszerzeniem klasy JFrame.
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
