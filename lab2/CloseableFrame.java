package lab2;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class CloseableFrame extends JFrame {
    public CloseableFrame() throws HeadlessException {
    	//Metoda super() widoczna wewnatrz konstruktorow sluzy do wywolania konstruktora 
    	//klasy nadrzednej (w tym wypadku JFrame). Znajduje sie ono zawsze na poczatku konstruktora, 
    	//dzieki czemu mozna po nim dodaæ dalszy kod, który sprawi, ze klasa CloseableFrame 
    	//bedzie rozszerzeniem klasy JFrame.
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
