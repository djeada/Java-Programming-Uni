package real;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BottomPanel extends JPanel{
	
	JButton button1;
	AnimationPanel animationPanel;
	
	public BottomPanel (AnimationPanel animation) {
		this.animationPanel = animation;
		
		button1 = new JButton("ON");
		this.add(button1,BorderLayout.SOUTH);
		
		buttonListener bListener = new buttonListener();
		
		button1.addActionListener(bListener);

	}
	
	public Timer timer = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        		for(Particle b: animationPanel.list){
        			b.borderTest(animationPanel);
        			for(Particle bn: animationPanel.list)b.ballTest(bn, 0.07);
        			b.nextPos(0.07);
        			
        		}
            	animationPanel.repaint();
        }
	});
	
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(button1.getText()=="ON"){
	    		button1.setText("OFF");
	    		timer.start();
	    	}
	    	else {
	    		timer.stop();
	    		button1.setText("ON");
	    	}
	    }
	}
}
