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

public class SearchVoucherGUI {

	private JFrame frame;
//	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
//	private JPanel northPanel = new JPanel();
	
	private JButton searchVoucherButton;
	private JButton doneButton;
	
	private JTextField voucherID;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SearchVoucherListener listener;
	
	public class SearchVoucherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchVoucherButton) {
				theText.setText(MenuController.searchVoucherGUIsearchVoucherButtonPressed(voucherID.getText()));
			} else if(e.getSource() == doneButton) {
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				MenuController.backToMenu();
				frame.dispose();
			}
		}
	}
	
	public SearchVoucherGUI(){
		listener = new SearchVoucherListener();
		//create GUI
		frame = new JFrame();
		frame.setTitle("Search Voucher");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(547,527);
		
		
		theText = new JTextArea();
		theText.setEditable(false);
		scroll = new JScrollPane(theText);
		scroll.setBounds(42, 58, 448, 349);
		masterPanel.setLayout(null);
		masterPanel.add(scroll);
		
		frame.getContentPane().add(masterPanel);
		JLabel label1 = new JLabel("Enter Voucher ID:");
		label1.setBounds(54, 21, 143, 14);
		masterPanel.add(label1);
		voucherID = new JTextField(30);
		voucherID.setBounds(192, 18, 246, 20);
		masterPanel.add(voucherID);
		searchVoucherButton = new JButton("Search Voucher");
		searchVoucherButton.setBounds(85, 430, 143, 46);
		masterPanel.add(searchVoucherButton);
		doneButton = new JButton("Done");
		doneButton.setBounds(305, 430, 95, 46);
		masterPanel.add(doneButton);
		doneButton.addActionListener(listener);
		
		searchVoucherButton.addActionListener(listener);
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
		
		
//		listener = new SearchVoucherListener();
//		searchVoucherButton = new JButton("Search Voucher");
//		doneButton = new JButton("Done");
//		voucherID = new JTextField(30);
//		JLabel label1 = new JLabel("Enter Voucher ID:");
//		
//		searchVoucherButton.addActionListener(listener);
//		doneButton.addActionListener(listener);
//		
//		//create GUI
//		frame = new JFrame();
////		frame.setUndecorated(true);
//		frame.setTitle("search reservation");
//		
//		frame.setResizable(false); 		//user can't resize GUI on their own
//		frame.setSize(800,800);			//set size of this
//		southPanel.setLayout(new FlowLayout());
//		southPanel.add(searchVoucherButton);
//		southPanel.add(doneButton);
//		
//		northPanel.setLayout(new FlowLayout());
//		
//		northPanel.add(label1);
//		northPanel.add(voucherID);
//		
//		
//		theText = new JTextArea();
//		scroll = new JScrollPane(theText);
//		theText.setEditable(false);
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
//		frame.setLocationRelativeTo(null);
//		
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                MenuController.backToMenu();
//            }
//        });
	}
}
