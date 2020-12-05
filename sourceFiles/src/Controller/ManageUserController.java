package Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Domain.Name;
import Domain.News;
import Domain.Payment;
import Domain.RegisteredUser;
import Domain.Reservation;
import Domain.Voucher;
import Presentation.GuestMenuGUI;
import Presentation.RegisteredUserMenuGUI;
import Presentation.SignupGUI;
import Presentation.ViewNewsGUI;
import Presentation.ViewReservationListGUI;

public class ManageUserController {
	private static TheatreProgram prog = TheatreProgram.getInstance();
	
	public static String parseAccountInfo(RegisteredUser user) {
		StringBuilder str  = new StringBuilder();  
		
		if (user == null) {
			str.append("Account not found!");
		} else {
			str.append("UserID: " + user.getUserID() + "\n");
			str.append("Full Name: " + user.getName() + "\n");
			str.append("Date of next Payment: " + user.getNextPaymentDate() + "\n");
			str.append("\nPayment Info: " + "\n");
			str.append("Account holder name: " + user.getPaymentInfo().getCardHolderName() + "\n");
			str.append("Billing address: " + user.getPaymentInfo().getBillingAddress() + "\n");
			str.append("Card Information hidden \n");
		}
		
		return new String(str);
	}
	
	//searchVoucherGUIFunctions------------------------------------------------------------------
	public static String searchVoucherGUIsearchVoucherButtonPressed(String voucherID) {
		Voucher voucher = prog.searchVoucher(voucherID);
		StringBuilder str  = new StringBuilder(); 
		
		if (voucher == null) {
			str.append("Voucher Not found!");
		} else {
			str.append("Voucher ID: " + voucher.getVoucherID() + "\n");
			str.append("Voucher credit: " + voucher.getCredit() + "$" + "\n");
			str.append("Voucher expiration date: " + voucher.getExpirationDate() + "\n");
			if (voucher.getUserID() == null) {
				str.append("Anyone can use this voucher! "+ "\n");
			} else {
				str.append("User ID of the owner of the voucher (only they can use it): " + voucher.getUserID() + "\n");
			}
			
		}

		return new String(str);
	}
	
	//RegisteredUserMenuGUI functions-----------------------------------------------------
	public static void RegisteredUserViewReservationsButtonPressed() {
		RegisteredUser user = (RegisteredUser) prog.getCurrentUser();
		ArrayList<Reservation> reservationList = user.getReservationList();
		if (reservationList == null) {
			JOptionPane.showMessageDialog(null, "Error retriving reservation List!", "Error", JOptionPane.ERROR_MESSAGE);
			MenuController.backToMenu();
		} else {
			new ViewReservationListGUI(parseRegistrationList(reservationList));
		}
	}
	
	private static String parseRegistrationList(ArrayList<Reservation> reservationList) {
		StringBuilder str  = new StringBuilder(); 
		
		if (reservationList.size() == 0) {
			str.append("You have no reservations " + "\n");
		}
		
		for (int i = 0; i < reservationList.size(); i++) {
			Reservation reservation =  reservationList.get(i);
			str.append("ReservationID: " + reservation.getReservationID() + "\n");
			str.append("Movie Name: " + reservation.getTicket().getRegister().getMovie().getMovieName() + "\n");
			str.append("Theatre Name: " + reservation.getTicket().getRegister().getTheatre().getTheatreName() + "\n");
			str.append("Theatre Address: " + reservation.getTicket().getRegister().getTheatre().getTheatreAddress() + "\n");
			str.append("Show Date and time: " + reservation.getTicket().getRegister().getShowDate() + ", at " +reservation.getTicket().getRegister().getShowHour() + "\n");
			str.append("Seat row: " + reservation.getTicket().getSeatRow() + "\n");
			str.append("Seat column: " + reservation.getTicket().getSeatColumn() + "\n");
			str.append("Amount paid (price - voucher credit): " + reservation.getReceipt().getCost() + "$" + "\n");
			str.append("____________________________________________\n\n");
		}
		return new String(str);
	}
	
	public static void ViewAccountPaySubscriptionButtonPressed() {
		if (prog.payUserFee(null) == true) {
			JOptionPane.showMessageDialog(null, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Failed to pay User fee! You can only pay less than 12 month in advance", "Error", JOptionPane.ERROR_MESSAGE);
		}
		MenuController.backToMenu();
	}
	
	public static void RegisteredUserViewNewsButtonPressed() {
		new ViewNewsGUI(parseNewsList(prog.getNewsList()));
	}
	
	private static String parseNewsList(ArrayList<News> newsList) {
		StringBuilder str  = new StringBuilder(); 
		
		if (newsList.size() == 0) {
			str.append("You have news " + "\n");
		}
		
		for (int i = 0; i < newsList.size(); i++) {
			News news =  newsList.get(i);
			
			str.append("***Early registration available***"+ "\n");
			str.append("Movie Name: " + news.getRegister().getMovie().getMovieName() + "\n");
			str.append("Theatre Name: " + news.getRegister().getTheatre().getTheatreName() + "\n");
			str.append("Show Date and time: " + news.getRegister().getShowDate() + ", at " + news.getRegister().getShowHour() + "\n");
			str.append("Movie Synopsis: \n" + news.getInformation() + "\n");
			str.append("____________________________________________\n\n");
		}
		return new String(str);
	}
	
	public static void RegisteredUserLogoutButtonPressed() {
		prog.logout();
		new GuestMenuGUI();
	}
	
	//signUpGUI functions------------------------------------------------------------
	public static void SignUPButtonPressed(String username, String password, String firstname, String middleName, String lastName, String billingAddress, String cardnumber, String expYear, String expMonth, String pin) {
		System.out.println("signup Pressed");
		
		if (username.length() == 0 || password.length() == 0 || firstname.length() == 0 || lastName.length() == 0 || billingAddress.length() == 0 || cardnumber.length() == 0 || expYear.length() == 0 || expMonth.length() == 0 || pin.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please filled in all the required (*) field(s)!", "Error", JOptionPane.ERROR_MESSAGE);
			new SignupGUI();
		} else {
			try {
				Name name = new Name(firstname, middleName, lastName);
				Payment payemnt = new Payment(firstname, middleName, lastName, billingAddress , Integer.parseInt(cardnumber), Integer.parseInt(expYear), Integer.parseInt(expMonth), Integer.parseInt(pin)); 
				
				if (prog.signUp(username, password, name, payemnt) == true) {
					JOptionPane.showMessageDialog(null, "Account successfully created! Try to login with it!", "Success", JOptionPane.INFORMATION_MESSAGE);
					MenuController.backToMenu();
				} else {
					JOptionPane.showMessageDialog(null, "Username was alread used! Try again!", "Error", JOptionPane.ERROR_MESSAGE);
					new SignupGUI();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter valid date numbers!", "Error", JOptionPane.ERROR_MESSAGE);
				new SignupGUI();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error trying to make new user!", "Error", JOptionPane.ERROR_MESSAGE);
				new SignupGUI();
			}
		}
	}	
	
	//LoginMenuGUI functions--------------------------------------------------------
	public static void loginMenuLoginButtonPressed(String username, String password) {
		System.out.println("login Pressed");
		String newUsername = prog.login(username, password);
		
		if (newUsername == null) {
			JOptionPane.showMessageDialog(null, "Username or Password are incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
			new GuestMenuGUI();
		} else if (prog.getLoginStatus() == 1) {
			// Registered User
			JOptionPane.showMessageDialog(null, "Success fully logged on! Welcome: \""+newUsername+"\"", "Success", JOptionPane.INFORMATION_MESSAGE);
			new RegisteredUserMenuGUI();
		} else if (prog.getLoginStatus() == 2) {
			// Admin
			JOptionPane.showMessageDialog(null, "Success fully logged on to admin", "Success", JOptionPane.INFORMATION_MESSAGE);
//			new AdminMenuGUI();
		}
		
	}	
}
