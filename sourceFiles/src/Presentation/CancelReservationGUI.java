package Presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.MenuController;

public class CancelReservationGUI {
	private JButton cancelReservationButton;
	private JButton cancelButton;
	private JLabel label1;
	private JPanel masterPanel;
//	private JPanel masterPanel_1;
	private JPanel north;
	private JFrame frame;
	private JTextField reservationID;
	private CancelReservationListener listener;
	
	public class CancelReservationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cancelReservationButton) {
				MenuController.CancelReservationGUIcancelReservationPressed(reservationID.getText());
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancelButton) {
				MenuController.CancelReservationGUICancelButtonPressed();
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
		
	}
	
	public CancelReservationGUI(){
//		masterPanel = new JPanel();
		frame = new JFrame();
		label1 = new JLabel("Enter Reservation ID");
		listener = new CancelReservationListener();

		frame.setSize(352,166);
		frame.setTitle("Cancel Reservation");
		
		masterPanel = new JPanel();

		north = new JPanel();
		north.setBounds(0, 0, 334, 24);
		north.setLayout(new FlowLayout());

		north.add(label1);
		masterPanel.setLayout(null);


		masterPanel.add(north);

		frame.setContentPane(masterPanel);
		//		frame.setUndecorated(true);
				reservationID = new JTextField(30);
				reservationID.setBounds(46, 35, 246, 20);
				masterPanel.add(reservationID);
				cancelReservationButton = new JButton("Cancel Reservation");
				cancelReservationButton.setBounds(24, 76, 149, 40);
				masterPanel.add(cancelReservationButton);
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(183, 76, 127, 40);
				masterPanel.add(cancelButton);
				cancelButton.addActionListener(listener);
				
				cancelReservationButton.addActionListener(listener);
		frame.setVisible(true);
		
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
