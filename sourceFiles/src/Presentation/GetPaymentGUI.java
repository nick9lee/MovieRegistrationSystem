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

public class GetPaymentGUI {
	private JButton addPayment;
	private JButton cancel;
	private JPanel masterPanel;
	private JPanel north;
	private JPanel south;
	private JPanel centre;
	private JFrame frame;
	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	private JTextField billingAddress;
	private JTextField cardNumber;
	private JTextField expYear;
	private JTextField expMonth;
	private JTextField pin;
	
	private addPaymentListener listener;
	
	// Private stored Variables
	private String registerID;
	private String seatRow;
	private String seatCol;
	
	public class addPaymentListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addPayment) {
				MenuController.addPaymentButtonPressed(firstName.getText(), middleName.getText(), lastName.getText()
						, billingAddress.getText(), cardNumber.getText(), expYear.getText(), expMonth.getText(), pin.getText(), registerID, seatRow, seatCol);
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancel) {
				MenuController.addPaymentCancelButton();
				frame.dispose();
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GetPaymentGUI() {
		listener = new addPaymentListener();
		
		addPayment = new JButton("Continue");
		cancel = new JButton("Cancel");

		addPayment.addActionListener(listener);
		cancel.addActionListener(listener);

		JLabel a = new JLabel("Please fill out fields");
		JLabel d = new JLabel("First Name *");
		d.setBounds(25, 14, 82, 14);
		JLabel e = new JLabel("Middle Name");
		e.setBounds(25, 45, 82, 14);
		JLabel f = new JLabel("Last Name *");
		f.setBounds(25, 76, 82, 14);
		JLabel g = new JLabel("Billing Address*");
		g.setBounds(14, 113, 103, 14);
		JLabel h = new JLabel("Card Number*");
		h.setBounds(14, 144, 103, 14);
		JLabel i = new JLabel("Exp Year*");
		i.setBounds(296, 14, 70, 14);
		JLabel j = new JLabel("Exp Month*");
		j.setBounds(296, 49, 70, 14);
		JLabel k = new JLabel("Pin*      ");
		k.setBounds(306, 82, 51, 14);

		firstName = new JTextField(30);
		firstName.setBounds(105, 11, 128, 20);
		middleName = new JTextField(30);
		middleName.setBounds(105, 42, 128, 20);
		lastName = new JTextField(30);
		lastName.setBounds(105, 73, 128, 20);
		billingAddress = new JTextField(30);
		billingAddress.setBounds(115, 110, 184, 20);
		cardNumber = new JTextField(30);
		cardNumber.setBounds(115, 141, 184, 20);
		expYear = new JTextField(30);
		expYear.setBounds(363, 14, 70, 20);
		expMonth = new JTextField(30);
		expMonth.setBounds(363, 45, 70, 20);
		pin = new JTextField(30);
		pin.setBounds(363, 76, 70, 20);
		

		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setSize(500,277);
		frame.setTitle("Add Payment");
		
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());

		north = new JPanel();
		north.setLayout(new FlowLayout());

		centre = new JPanel();

		south = new JPanel();
		south.setLayout(new FlowLayout());

		north.add(a);
		centre.setLayout(null);
		
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

		south.add(addPayment);
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

	public GetPaymentGUI(String registerID, String seatRow, String seatCol) {
		this();
		this.registerID = registerID;
		this.seatRow = seatRow;
		this.seatCol = seatCol;
	}
}
