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
import javax.swing.JTextField;

import Controller.MenuController;

public class SignupGUI {
	private JButton signup;
	private JButton cancel;
	private JPanel masterPanel;
	private JPanel north;
	private JPanel south;
	private JPanel centre;
	private JFrame frame;
	private JTextField username;
	private JTextField password;
	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	private JTextField billingAddress;
	private JTextField cardNumber;
	private JTextField expYear;
	private JTextField expMonth;
	private JTextField pin;
	
	private SignupListener listener;
	
//	if (prog.signUp("Username", "password", new Name(null, null, null), new Payment(null, null, null, null, 0, 0, 0, 0)) == true) {
//		JOptionPane.showMessageDialog(null, "Account successfully created! Try to login with it!", "Success", JOptionPane.INFORMATION_MESSAGE);
//		backToMenu();
//	} else {
//		JOptionPane.showMessageDialog(null, "Username was alread used! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
//		new SignupGUI();
//	}
	
	public class SignupListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == signup) {
				MenuController.SignUPButtonPressed(username.getText(), password.getText(), firstName.getText(), middleName.getText(), lastName.getText()
						, billingAddress.getText(), cardNumber.getText(), expYear.getText(), expMonth.getText(), pin.getText()
						);
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancel) {
				MenuController.SignUpCancelButtonPressed();
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	public SignupGUI() {
		

		listener = new SignupListener();
		
		signup = new JButton("Continue");
		cancel = new JButton("Cancel");

		signup.addActionListener(listener);
		cancel.addActionListener(listener);

		JLabel a = new JLabel("Please fill out fields");
		JLabel b = new JLabel("Username *");
		b.setBounds(13, 14, 105, 14);
		JLabel c = new JLabel("Password *");
		c.setBounds(217, 14, 95, 14);
		JLabel d = new JLabel("First Name*");
		d.setBounds(9, 58, 74, 14);
		JLabel e = new JLabel("Middle Name");
		e.setBounds(7, 83, 79, 14);
		JLabel f = new JLabel("Last Name*");
		f.setBounds(9, 108, 74, 14);
		JLabel g = new JLabel("Billing Address *");
		g.setBounds(7, 153, 105, 14);
		JLabel h = new JLabel("Card Number *");
		h.setBounds(9, 178, 103, 14);
		JLabel i = new JLabel("Exp Year*");
		i.setBounds(260, 58, 74, 14);
		JLabel j = new JLabel("Exp Month*");
		j.setBounds(260, 83, 74, 14);
		JLabel k = new JLabel("Pin*      ");
		k.setBounds(260, 108, 74, 14);

		username = new JTextField(30);
		username.setBounds(86, 11, 118, 20);
		password = new JTextField(30);
		password.setBounds(287, 11, 118, 20);
		firstName = new JTextField(30);
		firstName.setBounds(88, 52, 118, 20);
		middleName = new JTextField(30);
		middleName.setBounds(89, 77, 118, 20);
		lastName = new JTextField(30);
		lastName.setBounds(88, 105, 118, 20);
		billingAddress = new JTextField(30);
		billingAddress.setBounds(107, 147, 246, 20);
		cardNumber = new JTextField(30);
		cardNumber.setBounds(107, 175, 246, 20);
		expYear = new JTextField(30);
		expYear.setBounds(331, 55, 74, 20);
		expMonth = new JTextField(30);
		expMonth.setBounds(331, 80, 74, 20);
		pin = new JTextField(30);
		pin.setBounds(331, 105, 74, 20);
		

		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setSize(436,305);
		frame.setTitle("signup");
		
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());

		north = new JPanel();
		north.setLayout(new FlowLayout());

		centre = new JPanel();

		south = new JPanel();
		south.setLayout(new FlowLayout());

		north.add(a);
		centre.setLayout(null);

		centre.add(b);
		centre.add(username);

		centre.add(c);
		centre.add(password);
		
		centre.add(d);
		centre.add(firstName);
		
		centre.add(e);
		centre.add(middleName);
		
		centre.add(f);
		centre.add(lastName);
		
		centre.add(g);
		centre.add(billingAddress);
		
		centre.add(h);
		centre.add(cardNumber);
		
		centre.add(i);
		centre.add(expYear);
		
		centre.add(j);
		centre.add(expMonth);
		
		centre.add(k);
		centre.add(pin);

		south.add(signup);
		south.add(cancel);


		masterPanel.add(north, BorderLayout.PAGE_START);
		masterPanel.add(centre, BorderLayout.CENTER);
		masterPanel.add(south, BorderLayout.PAGE_END);

		frame.setContentPane(masterPanel);
		frame.setVisible(true);
		
		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
		frame.setIconImage(image.getImage()); 						//set GUI logo to image created above
		
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MenuController.backToMenu();
            }
        });
	}
}
