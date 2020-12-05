package Presentation;

import java.awt.BorderLayout;
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

public class SelectMovieGUI {
	private JFrame frame;
	private JPanel masterPanel = new JPanel();
	
	private JButton selectMovie;
	private JButton cancel;
	
	private JTextField movieID;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SelectMovieListener listener;
	
	public class SelectMovieListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == selectMovie) {
				MenuController.selectMovieGUISelectMovieButtonPressed(movieID.getText());
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancel) {
				MenuController.selectMovieGUICancelButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			}
			
		}
		
	}
	public SelectMovieGUI(){
		//add listner
				listener = new SelectMovieListener();
				selectMovie = new JButton("Select Movie");
				cancel = new JButton("Cancel");
				movieID = new JTextField(30);
				new JLabel("Movie ID:");
				
				selectMovie.addActionListener(listener);
				cancel.addActionListener(listener);
				
				//create GUI
				frame = new JFrame();
//				frame.setUndecorated(true);
				frame.setTitle("select movie");
				
				frame.setResizable(false); 		//user can't resize GUI on their own
				frame.setSize(800,800);			//set size of this
				
				
				theText = new JTextArea();
				scroll = new JScrollPane(theText);
				theText.setEditable(false);
				
				masterPanel.setLayout(new BorderLayout());
				

				masterPanel.add(scroll,BorderLayout.CENTER);
				
				frame.getContentPane().add(masterPanel);
				frame.setVisible(true); 		//make this visible
				
				
				
				ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
				frame.setIconImage(image.getImage()); 	
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
	public SelectMovieGUI(String movieList){
		//add listner
				listener = new SelectMovieListener();
				
				//create GUI
				frame = new JFrame();
//				frame.setUndecorated(true);
				frame.setTitle("Select movie");
				
				frame.setResizable(false); 		//user can't resize GUI on their own
				frame.setSize(707,566);
				
				
				theText = new JTextArea(movieList);
//				theText.setText(movieList);
				scroll = new JScrollPane(theText);
				scroll.setBounds(10, 72, 681, 368);
				
				theText.setEditable(false);
				masterPanel.setLayout(null);
				masterPanel.add(scroll);
				
				frame.getContentPane().add(masterPanel);
				JLabel label1 = new JLabel("Movie ID:");
				label1.setBounds(177, 26, 82, 14);
				masterPanel.add(label1);
				movieID = new JTextField(30);
				movieID.setBounds(269, 23, 171, 20);
				masterPanel.add(movieID);
				selectMovie = new JButton("Select Movie");
				selectMovie.setBounds(177, 451, 141, 60);
				masterPanel.add(selectMovie);
				cancel = new JButton("Cancel");
				cancel.setBounds(358, 451, 154, 60);
				masterPanel.add(cancel);
				cancel.addActionListener(listener);
				
				selectMovie.addActionListener(listener);
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	
	
	
}
