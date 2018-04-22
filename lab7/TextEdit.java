package lab7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TextEdit extends JFrame implements Runnable{
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	private boolean changed = false;
	private JTextPane txt=new JTextPane();
	
	private String currtype="Serif";
	private int currstyle=0;
	private int currsize= 20;
	
	@Override
	public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	
	
	public TextEdit() throws HeadlessException {
		super();
		setLayout(new BorderLayout());
		setSize(1024, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(480,120));
		this.setTitle("Title");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (changed&&JOptionPane.showConfirmDialog(new JFrame(), 
		                "Changes not saved.\nWant to save?", "Are you sure to close this window?",
		                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        		saveFileAs();
		                System.exit(0);
		            }
		    }
		});

		
		
		JMenuBar menuBar = new JMenuBar();
		//Build the first menu.
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		//a group of JMenuItems
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		       saveFileAs();
		    }
		});
		
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
					readInFile(dialog.getSelectedFile().getAbsolutePath());
		    }
		});
		menu.add(save);
		menu.add(open);
		this.setJMenuBar(menuBar);
		
		
		//Edit
		JPanel pN= new JPanel();
		pN.setLayout(new FlowLayout());
		
		//fonts
		txt.setFont(new Font(currtype,currstyle,currsize));
		final String[] fontTypes={"Serif","Arial","Times New Roman"};
		final JComboBox<String> font=new JComboBox<>(fontTypes);
		font.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currtype=fontTypes[font.getSelectedIndex()];
				txt.setFont(new Font(currtype,currstyle,currsize));
		    }
		});
	
		final Integer[] sizeArr={20,8,10,12,14,16,18,0,100};
		final JComboBox<Integer> size=new JComboBox<>(sizeArr);
		size.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				currsize=sizeArr[size.getSelectedIndex()];
				txt.setFont(new Font(currtype,currstyle,currsize));
		    }
		});
		
		
		final String[] styleArr={"Plain","Bold","Cursive","Bold + Italic"};
		final JComboBox<String> style=new JComboBox<>(styleArr);
		style.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				currstyle=(int)style.getSelectedIndex();
				txt.setFont(new Font(currtype,currstyle,currsize));
			
		    }
		});
		
		final String[] fontColorArr={"Black","Red","Green","Blue"};
		final JComboBox<String> fontColor=new JComboBox<>(fontColorArr);
		fontColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				Color col=new Color(0,0,0);
				if(fontColor.getSelectedIndex()==0)col=new Color(0,0,0);
				if(fontColor.getSelectedIndex()==1)col=new Color(255,0,0);
				if(fontColor.getSelectedIndex()==2)col=new Color(0,255,0);
				if(fontColor.getSelectedIndex()==3)col=new Color(0,0,255);
				txt.setForeground(col);
			
		    }
		});
		final String[] paneColorArr={"White","Red","Green","Blue"};
		final JComboBox<String> paneColor=new JComboBox<>(paneColorArr);
		paneColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {
				Color col=new Color(255,255,255);
				if(paneColor.getSelectedIndex()==0)col=new Color(255,255,255);
				if(paneColor.getSelectedIndex()==1)col=new Color(128,0,0);
				if(paneColor.getSelectedIndex()==2)col=new Color(0,128,0);
				if(paneColor.getSelectedIndex()==3)col=new Color(0,0,128);
				txt.setBackground(col);
			
		    }
		});
		
		//Spell check
		JButton Button = new JButton("Spell Check");
		Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
					spellCheck(dialog.getSelectedFile().getAbsolutePath());
		    }
		});
		
		
		pN.add(Button);
		pN.add(font);
		pN.add(size);
		pN.add(style);
		pN.add(fontColor);
		pN.add(paneColor);
		this.add(pN,BorderLayout.NORTH);
		
		//TextArea
		//txt.setLineWrap(true);
		txt.addKeyListener(k1);
		JScrollPane scroll = new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll,BorderLayout.CENTER);

	}
	
	
	
	//Methods
	private void saveFile(String fileName) {
		try {
			Writer writer = new OutputStreamWriter( new FileOutputStream(fileName), "UTF-8");
			BufferedWriter fout = new BufferedWriter(writer);
			txt.write(fout);
			fout.close();
			changed = false;
		}
		catch(IOException e) {
		}
	}
	 
	private void readInFile(String fileName) {
		try {
			Scanner scanner = new Scanner( new File(fileName), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close();
			String tmp = text.replaceAll("�", "?").replaceAll("ó", "?").replaceAll("ć", "?").replaceAll("ę", "?").replaceAll("ł", "?").replaceAll("ń", "?").replaceAll("ś", "?").replaceAll("ź", "?").replaceAll("ż", "?");
			txt.setText(tmp);
			
			setTitle(fileName);
			changed = false;
			
			System.out.print( "File opening was executed correctly" );
		}
		catch(IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this,"Editor can't find the file called "+fileName);
		}
		
	}
	
	private void saveFileAs() {
		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath());
	}
	
	final KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			changed = true;
		}
	};
	
	private void spellCheck(String fileName) {
		try{ 	
			
			Reader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
			BufferedReader fin = new BufferedReader(reader);
			StringBuilder tmp1 = new StringBuilder();
			String line;
			
			while ((line = fin.readLine()) != null) {
				tmp1.append(line);
			}
			
			String tmp2 = txt.getText();
		
			System.out.print("\n");
			System.out.print(tmp1);
			System.out.print("\n");
			System.out.print(tmp2);

		
			if(tmp2.equals(tmp1)){
				JOptionPane.showMessageDialog(this, "Well done. Mission accomplished.");
			} 
		
			else {
				JOptionPane.showMessageDialog(this, "Try harder.");
			}
		}
		
		catch(IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this,"Editor can't find the file called "+fileName);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TextEdit frame = new TextEdit();
				frame.setVisible(true);
			}
		});
	}
		

}

