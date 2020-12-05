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

public class RegisteredUserMenuGUI {
	private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
	
	private JButton viewReservations = new JButton("View Reservations");
	private JButton newsButton = new JButton("View News");
	private JButton logoutButton = new JButton("Logout");
	private JButton cancelReservationButton = new JButton("Cancel Reservation");
	private JButton bookReservationButton = new JButton("Book Reservation");
	private JButton searchReservationButton = new JButton("Search Reservation");
	private JButton searchVouchersButton = new JButton("searchVoucher");
	private JButton viewAccountButton = new JButton("View Account");
	private JButton viewVouchersButton = new JButton("View Voucher List");
	
	private JTextArea theText;
	private JScrollPane scroll;
	private RegisteredUserMenuListener listener;
	
	class RegisteredUserMenuListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == viewReservations) {
				MenuController.RegisteredUserViewReservationsButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == bookReservationButton) {
				MenuController.RegisteredUserBookReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancelReservationButton) {
				MenuController.RegisteredUserCancelReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == viewAccountButton) {
				MenuController.RegisteredUserViewAccountButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == newsButton) {
				MenuController.RegisteredUserViewNewsButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == logoutButton) {
				MenuController.RegisteredUserLogoutButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == viewVouchersButton) {
				MenuController.RegisteredUserViewVoucherListButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == searchVouchersButton) {
				MenuController.RegisteredUserSearchVouchersButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == searchReservationButton) {
				MenuController.RegisteredUserSearchReservationButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} 
		}
		
	}
	
	public RegisteredUserMenuGUI(){
		//add listner
		listener = new RegisteredUserMenuListener();
		
		viewReservations.addActionListener(listener);
		bookReservationButton.addActionListener(listener);
		cancelReservationButton.addActionListener(listener);
		searchReservationButton.addActionListener(listener);
		viewAccountButton.addActionListener(listener);
		viewVouchersButton.addActionListener(listener);
		searchVouchersButton.addActionListener(listener);
		newsButton.addActionListener(listener);
		logoutButton.addActionListener(listener);
		
		
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("Registered User Menu");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(1300,400);			//set size of this
		southPanel.setLayout(new FlowLayout());
		
		southPanel.add(viewReservations);
		southPanel.add(bookReservationButton);
		southPanel.add(cancelReservationButton);
		southPanel.add(searchReservationButton);
		southPanel.add(viewAccountButton);
		southPanel.add(viewVouchersButton);
		southPanel.add(searchVouchersButton);
		southPanel.add(newsButton);
		southPanel.add(logoutButton);
		
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
                MenuController.logout();
                MenuController.backToMenu();
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
