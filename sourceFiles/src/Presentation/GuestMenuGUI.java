package Presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.MenuController;

public class GuestMenuGUI {
	private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
	
	private JButton signUpButton = new JButton("Sign Up");
	private JButton loginButton = new JButton("Login");
	private JButton cancelReservationButton = new JButton("Cancel Reservation");
	private JButton bookReservationButton = new JButton("Book Reservation");
	private JButton searchReservationButton = new JButton("Search Reservation");
	private JButton searchVoucherButton = new JButton("Search Voucher");
	private JButton exitButton = new JButton("Exit");
	
	private JTextArea theText;
	private JScrollPane scroll;
	private guestMenuListener listener;
	
	class guestMenuListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == bookReservationButton) {
				MenuController.guestMenuBookReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancelReservationButton) {
				MenuController.guestMenuCancelReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == loginButton) {
				MenuController.guestMenuLoginButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == signUpButton) {
				MenuController.guestMenuSignUpButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == exitButton) {
				MenuController.guestMenuExitButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == searchReservationButton) {
				MenuController.guestMenuSearchReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == searchVoucherButton) {
				MenuController.guestMenuSearchVoucherButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	public GuestMenuGUI(){
		//add listner
		listener = new guestMenuListener();
		bookReservationButton.addActionListener(listener);
		cancelReservationButton.addActionListener(listener);
		loginButton.addActionListener(listener);
		signUpButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		searchReservationButton.addActionListener(listener);
		searchVoucherButton.addActionListener(listener);
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("Guest Menu");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(1000,200);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(searchReservationButton);
		southPanel.add(bookReservationButton);
		southPanel.add(cancelReservationButton);
		southPanel.add(searchVoucherButton);
		southPanel.add(loginButton);
		southPanel.add(signUpButton);
		southPanel.add(exitButton);
		
		theText = new JTextArea();
		scroll = new JScrollPane(theText);
		
		masterPanel.setLayout(new BorderLayout());
		
		masterPanel.add(southPanel, BorderLayout.PAGE_END);
		masterPanel.add(scroll,BorderLayout.CENTER);
		
		frame.add(masterPanel);
		frame.setVisible(true); 		//make this visible
		
		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
		frame.setIconImage(image.getImage()); 	
		
		theText.setEditable(false);
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MenuController.saveAndExit();
            }
        });
	}

	
	//getters and setters
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextArea getTheText() {
		return theText;
	}

	public void setTheText(JTextArea theText) {
		this.theText = theText;
	}
	
	
	
	
}
