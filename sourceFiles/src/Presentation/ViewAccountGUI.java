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

public class ViewAccountGUI {
	private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	
	private JButton doneButton;
	private JButton paySubscriptionButton;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private ViewNewsListener listener;
	
	public class ViewNewsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == doneButton) {
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
				MenuController.backToMenu();
				frame.dispose();
			} if(e.getSource() == paySubscriptionButton) {
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
				MenuController.ViewAccountPaySubscriptionButtonPressed();
				frame.dispose();
			}
		}
	}
	
	public ViewAccountGUI(){
		listener = new ViewNewsListener();
		doneButton = new JButton("Done");
		paySubscriptionButton = new JButton("Pay Subscription");
		
		doneButton.addActionListener(listener);
		paySubscriptionButton.addActionListener(listener);
		
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("view account");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(800,800);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(paySubscriptionButton);
		southPanel.add(doneButton);
		
		northPanel.setLayout(new FlowLayout());
		
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
	
	public ViewAccountGUI(String subscriptionInfo){
		listener = new ViewNewsListener();
		doneButton = new JButton("Done");
		paySubscriptionButton = new JButton("Pay Subscription");
		
		doneButton.addActionListener(listener);
		paySubscriptionButton.addActionListener(listener);
		
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("view account");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(800,800);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(paySubscriptionButton);
		southPanel.add(doneButton);
		
		northPanel.setLayout(new FlowLayout());
		
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
		
		theText.setText(subscriptionInfo);
		
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
