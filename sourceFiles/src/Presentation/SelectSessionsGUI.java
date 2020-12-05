package Presentation;

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

public class SelectSessionsGUI {
	
	private JFrame frame;
	private JPanel masterPanel = new JPanel();
	
	private JButton selectTheatreButton;
	private JButton cancelButton;
	
	private JTextField sessionID;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SelectSessionsListener listener;

	
	public class SelectSessionsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == selectTheatreButton) {
				MenuController.selectSessionsGUISelectButtonPressed(sessionID.getText());
				frame.dispose();
			} else if(e.getSource() == cancelButton) {
				MenuController.backToMenu();
				frame.dispose();
			}
			
		}
		
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public SelectSessionsGUI(String sessionList){
		listener = new SelectSessionsListener();
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("Select Sessions");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(707,566);
		
		
		theText = new JTextArea(sessionList);
//		theText.setText(theatreList);
		scroll = new JScrollPane(theText);
		scroll.setBounds(10, 72, 681, 368);
		
		theText.setEditable(false);
		masterPanel.setLayout(null);
		masterPanel.add(scroll);
		
		frame.getContentPane().add(masterPanel);
		JLabel label1 = new JLabel("Selection ID:");
		label1.setBounds(177, 26, 82, 14);
		masterPanel.add(label1);
		sessionID = new JTextField(30);
		sessionID.setBounds(269, 23, 171, 20);
		masterPanel.add(sessionID);
		selectTheatreButton = new JButton("Select session");
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
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
