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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import Controller.MenuController;

public class SelectSeatGUI {
	private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private char[][] theSeatArray;
	
	private JButton selectSeatButton;
	private JButton cancelButton;
	private JToggleButton[][] seat;
	
	private JTextField seatRow;
	private JTextField seatCol;
	
	private JTextArea theText;
	private JScrollPane scroll;
	private SelectSeatListener listener;
	
	private String seatPos = "";
	
	private String registerID;
	
	private int rows;
	private int cols;
	
	private boolean seatSelected = false; 
	
	public class SelectSeatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == selectSeatButton && seatSelected == true) {
				
				checkWhichButtonPressed();
				String row = String.valueOf(seatPos.charAt(0));
				String col = String.valueOf(seatPos.charAt(1));
					
				System.out.println(row + " " + col);
				MenuController.selectSeatGUISelectSeatButtonPressed(registerID, row, col);
				frame.dispose();
				
				
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			} else if(e.getSource() == cancelButton) {
				MenuController.selectSeatGUICancelButtonPressed();
				frame.dispose();
//				getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
			}
			
			System.out.println("fdsif");
			if(seatSelected == false) {
				System.out.println("fbdjsk");
				seatSelected = true;
				selectSeatButton.setEnabled(true);
				for(int i = 0; i < cols; i++) {
		    		for(int j = 0; j < rows; j++) {
		    			if(e.getSource() == seat[i][j])
		    				continue;
		    			
		    			seat[i][j].setEnabled(false);
		    		}
		        }
			} else {
				seatSelected = false;
				selectSeatButton.setEnabled(false);
				for(int i = 0; i < cols; i++) {
		    		for(int j = 0; j < rows; j++) {
		    			if(e.getSource() == seat[i][j])
		    				continue;
		    			
		    			seat[i][j].setEnabled(true);
		    		}
		        }

				for(int i = 0; i< cols; i++) {
					for( int j = 0; j < rows; j++) {
					
						if(theSeatArray[j][i] == 'e' || theSeatArray[j][i] == 'r') {
							seat[i][j].setEnabled(false);
						}
						
					}
				}
			}
			
			
			
		}
		
	}
	
	public SelectSeatGUI(){
		listener = new SelectSeatListener();
		selectSeatButton = new JButton("Select Seat");
		cancelButton = new JButton("Cancel");
		seatRow = new JTextField(5);
		seatCol = new JTextField(5);
		JLabel label1 = new JLabel("seat row:");
		JLabel label2 = new JLabel("seat column:");
		
		selectSeatButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("select seat");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(800,800);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(selectSeatButton);
		southPanel.add(cancelButton);
		
		northPanel.setLayout(new FlowLayout());
		
		northPanel.add(label1);
		northPanel.add(seatRow);
		
		northPanel.add(label2);
		northPanel.add(seatCol);
		
		
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
	
	public SelectSeatGUI(char[][] seatArray, String registerID){
		theSeatArray = seatArray;
		
		rows = seatArray.length;
		cols = seatArray[0].length;
		
		System.out.println(rows);
		System.out.println(cols);
		
		this.registerID = registerID;
		listener = new SelectSeatListener();
		selectSeatButton = new JButton("Select Seat");
		cancelButton = new JButton("Cancel");
		
		selectSeatButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		
		//create GUI
		frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setTitle("select seat");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(800,800);			//set size of this
		southPanel.setLayout(new FlowLayout());
		southPanel.add(selectSeatButton);
		southPanel.add(cancelButton);
		
		northPanel.setSize(800, 700);
		
		createSeats(seatArray);
		
		masterPanel.setLayout(new BorderLayout());
		
		masterPanel.add(southPanel, BorderLayout.PAGE_END);
		masterPanel.add(northPanel, BorderLayout.CENTER);
//		masterPanel.add(scroll,BorderLayout.CENTER);
		

		
		frame.add(masterPanel);
		frame.setVisible(true); 		//make this visible
		
		ImageIcon image = new ImageIcon("src/MoviesJavaApp.png");		//create an image icon
		frame.setIconImage(image.getImage()); 
		
		frame.setLocationRelativeTo(null);

		selectSeatButton.setEnabled(false);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MenuController.backToMenu();
            }
        });
	}
	
	private void createSeats(char[][] arrayOfSeats) {		
		seat = new JToggleButton[cols][rows];
		for(int i = 0; i< cols; i++) {
			for( int j = 0; j < rows; j++) {
				StringBuilder seatPos = new StringBuilder();
				seatPos.append(j);
				seatPos.append(i);
				
				seat[i][j] = new JToggleButton(seatPos.toString());
				seat[i][j].setBounds(70*i+60, 70*j+40, 65, 65);
				seat[i][j].setName(seatPos.toString());
				if(arrayOfSeats[j][i] == 'e' || arrayOfSeats[j][i] == 'r') {
					seat[i][j].setEnabled(false);
				}
				seat[i][j].addActionListener(listener);
				frame.add(seat[i][j]);
			}
		}
	}
	
	
	private void checkWhichButtonPressed() {
		seatPos = "";
		for(int i = 0; i < cols; i++) {
    		for(int j = 0; j < rows; j++) {
    			if(seat[i][j].isSelected())
    				seatPos += seat[i][j].getName() + "\n";	// how its attached
    		}
        }
		
	}
	

	


	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


}
