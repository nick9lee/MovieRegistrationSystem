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

public class SearchReservationsGUI {

	private JFrame frame;
	private JPanel masterPanel = new JPanel();
	
	private JButton searchReservationButton;
	private JButton doneButton;
	
	private JTextField reservationID;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SearchReservationListener listener;
	
	public class SearchReservationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchReservationButton) {
//				MenuController.SearchReservationButtonPressed(reservationID.getText());
				theText.setText(MenuController.SearchReservationButtonPressed(reservationID.getText()));
			} else if(e.getSource() == doneButton) {
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				MenuController.backToMenu();
				frame.dispose();
			}
			
		}
		
	}
	
	public SearchReservationsGUI(){
		listener = new SearchReservationListener();
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("Search reservation");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(547,527);
		
		
		theText = new JTextArea();
		theText.setEditable(false);
		scroll = new JScrollPane(theText);
		scroll.setBounds(42, 58, 448, 349);
		masterPanel.setLayout(null);
		masterPanel.add(scroll);
		
		frame.getContentPane().add(masterPanel);
		JLabel label1 = new JLabel("Enter Reservation ID:");
		label1.setBounds(54, 21, 143, 14);
		masterPanel.add(label1);
		reservationID = new JTextField(30);
		reservationID.setBounds(192, 18, 246, 20);
		masterPanel.add(reservationID);
		searchReservationButton = new JButton("Search Reservation");
		searchReservationButton.setBounds(85, 430, 164, 46);
		masterPanel.add(searchReservationButton);
		doneButton = new JButton("Done");
		doneButton.setBounds(305, 430, 121, 46);
		masterPanel.add(doneButton);
		doneButton.addActionListener(listener);
		
		searchReservationButton.addActionListener(listener);
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
}
