package Presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.MenuController;

public class SelectTheatreGUI {
	
	private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	
	private JButton selectTheatreButton;
	private JButton cancelButton;
	
	private JTextField theatreID;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SelectTheatreListener listener;

	private String movieID;
	
	public class SelectTheatreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == selectTheatreButton) {
				MenuController.selectTheatreGUISelectTheatreButtonPressed(theatreID.getText(), movieID);
				frame.dispose();
			} else if(e.getSource() == cancelButton) {
				MenuController.selectTheatreGUICancelButtonPressed();
				frame.dispose();
			}
			
		}
		
	}
	
	public SelectTheatreGUI(){
		listener = new SelectTheatreListener();
		selectTheatreButton = new JButton("Select Theatre");
		cancelButton = new JButton("Cancel");
		theatreID = new JTextField(30);
		JLabel label1 = new JLabel("Theatre ID:");
		
		selectTheatreButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		
		//create GUI
		frame = new JFrame();
		frame.setTitle("select theatre");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(800,800);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(selectTheatreButton);
		southPanel.add(cancelButton);
		
		northPanel.setLayout(new FlowLayout());
		
		northPanel.add(label1);
		northPanel.add(theatreID);
		
		
		theText = new JTextArea();
		scroll = new JScrollPane(theText);
		theText.setEditable(false);
		
		masterPanel.setLayout(new BorderLayout());
		
		masterPanel.add(southPanel, BorderLayout.PAGE_END);
		masterPanel.add(northPanel, BorderLayout.NORTH);
		masterPanel.add(scroll,BorderLayout.CENTER);
		
		frame.add(masterPanel);
		frame.setVisible(true); 		//make this visible
		
		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
		frame.setIconImage(image.getImage()); 
		
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MenuController.backToMenu();
            }
        });
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public SelectTheatreGUI(String theatreList, String movieID){
		this.movieID = movieID;
		listener = new SelectTheatreListener();
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("Select Theatre");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(707,566);
		
		
		theText = new JTextArea(theatreList);
//		theText.setText(theatreList);
		scroll = new JScrollPane(theText);
		scroll.setBounds(10, 72, 681, 368);
		
		theText.setEditable(false);
		masterPanel.setLayout(null);
		masterPanel.add(scroll);
		
		frame.getContentPane().add(masterPanel);
		JLabel label1 = new JLabel("Theatre ID:");
		label1.setBounds(177, 26, 82, 14);
		masterPanel.add(label1);
		theatreID = new JTextField(30);
		theatreID.setBounds(269, 23, 171, 20);
		masterPanel.add(theatreID);
		selectTheatreButton = new JButton("Select theatre");
		selectTheatreButton.setBounds(177, 451, 141, 60);
		masterPanel.add(selectTheatreButton);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(358, 451, 154, 60);
		masterPanel.add(cancelButton);
		cancelButton.addActionListener(listener);
		
		selectTheatreButton.addActionListener(listener);
		frame.setVisible(true); 		//make this visible
		
		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
		frame.setIconImage(image.getImage()); 	
		
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MenuController.backToMenu();
            }
        });
		
//		listener = new SelectTheatreListener();
//		selectTheatreButton = new JButton("Select Theatre");
//		cancelButton = new JButton("Cancel");
//		theatreID = new JTextField(30);
//		JLabel label1 = new JLabel("Theatre ID:");
//		
//		selectTheatreButton.addActionListener(listener);
//		cancelButton.addActionListener(listener);
//		
//		//create GUI
//		frame = new JFrame();
////		frame.setUndecorated(true);
//		frame.setTitle("select theatre");
//		
//		frame.setResizable(false); 		//user can't resize GUI on their own
//		frame.setSize(800,800);			//set size of this
//		southPanel.setLayout(new FlowLayout());
//		southPanel.add(selectTheatreButton);
//		southPanel.add(cancelButton);
//		
//		northPanel.setLayout(new FlowLayout());
//		
//		northPanel.add(label1);
//		northPanel.add(theatreID);
//		
//		
//		theText = new JTextArea();
//		theText.setEditable(false);
//		scroll = new JScrollPane(theText);
//		
//		
//		masterPanel.setLayout(new BorderLayout());
//		
//		masterPanel.add(southPanel, BorderLayout.PAGE_END);
//		masterPanel.add(northPanel, BorderLayout.NORTH);
//		masterPanel.add(scroll,BorderLayout.CENTER);
//		
//		frame.add(masterPanel);
//		frame.setVisible(true); 		//make this visible
//		
//		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
//		frame.setIconImage(image.getImage()); 
//		
//		theText.setEditable(false);
//		theText.setText(theatreList);
//		frame.setLocationRelativeTo(null);
//		
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                MenuController.backToMenu();
//            }
//        });
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	
	
}
