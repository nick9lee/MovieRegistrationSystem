package Controller;

import Domain.*;
import Presentation.*;

public class MenuController {
	static TheatreProgram prog = TheatreProgram.getInstance();
	
	//searchVoucherGUIFunctions------------------------------------------------------------------
	public static String searchVoucherGUIsearchVoucherButtonPressed(String voucherID) {
		return ManageUserController.searchVoucherGUIsearchVoucherButtonPressed(voucherID);
	}
	
	//getPayment functions----------------------------------------------------------------------------------------------------------------
	public static void addPaymentButtonPressed(String firstname, String middleName, String lastName, String billingAddress, String cardnumber, String expYear, String expMonth, String pin, String registerID, String seatRow, String seatCol) {
		PaymentController.addPaymentButtonPressed(firstname, middleName, lastName, billingAddress, cardnumber, expYear, expMonth, pin, registerID, seatRow, seatCol);
	}
	
	public static void addPaymentCancelButton() {
		backToMenu();
	}
	
	//RegisteredUserShowReservationGUI function--------------------------------------------------------
	public static void RegisteredUserShowReservationsButtonPressed() {
		backToMenu();
	}
	
	//SearchReservationGUI function--------------------------------------------------------
	public static String SearchReservationButtonPressed(String reservationID) {
		return ManageReservationController.SearchReservationButtonPressed(reservationID);
	}	
	
	//RegisteredUserMenuGUI functions-----------------------------------------------------
	public static void RegisteredUserSearchVouchersButtonPressed() {
		new SearchVoucherGUI();
	}
	
	public static void RegisteredUserSearchReservationButtonPressed() {
		new SearchReservationsGUI();
	}
	
	public static void RegisteredUserViewAccountButtonPressed() {
		new ViewAccountGUI(ManageUserController.parseAccountInfo((RegisteredUser) prog.getCurrentUser()));
	}
	
	public static void RegisteredUserViewVoucherListButtonPressed() {
		new ViewVouchersGUI(PaymentController.parseVoucherList(prog.getVoucherOfUser()));
	}
	
	public static void RegisteredUserViewReservationsButtonPressed() {
		ManageUserController.RegisteredUserViewReservationsButtonPressed();
	}
	
	public static void RegisteredUserBookReservationButtonPressed() {
		guestMenuBookReservationButtonPressed();
	}
	
	public static void RegisteredUserCancelReservationButtonPressed() {
		new CancelReservationGUI();
	}
	
	public static void ViewAccountPaySubscriptionButtonPressed() {
		ManageUserController.ViewAccountPaySubscriptionButtonPressed();
	}

	public static void RegisteredUserViewNewsButtonPressed() {
		ManageUserController.RegisteredUserViewNewsButtonPressed();
	}
	
	public static void RegisteredUserLogoutButtonPressed() {
		ManageUserController.RegisteredUserLogoutButtonPressed();
	}
	
	//signUpGUI functions------------------------------------------------------------
	public static void SignUPButtonPressed(String username, String password, String firstname, String middleName, String lastName, String billingAddress, String cardnumber, String expYear, String expMonth, String pin) {
		ManageUserController.SignUPButtonPressed(username, password, firstname, middleName, lastName, billingAddress, cardnumber, expYear, expMonth, pin);
	}
	
	public static void SignUpCancelButtonPressed() {
		backToMenu();
	}
	
	//CancelReservationGUI Functions------------------------------------------------
	public static void CancelReservationGUIcancelReservationPressed(String reservationID) {
		ManageReservationController.CancelReservationGUIcancelReservationPressed(reservationID);
	}
	
	public static void CancelReservationGUICancelButtonPressed() {
		backToMenu();
	}
	
	//SelectSeatGUI functions--------------------------------------------------------
	public static void selectSeatGUISelectSeatButtonPressed(String registerID, String seatRow, String seatCol) {
		ManageReservationController.selectSeatGUISelectSeatButtonPressed(registerID, seatRow, seatCol);
	}
	
	public static void selectSeatGUICancelButtonPressed() {
		backToMenu();
	}
	
	//SelectTheatreGUI functions--------------------------------------------------------
	public static void selectTheatreGUISelectTheatreButtonPressed(String registerID, String movieID) {
		ManageReservationController.selectTheatreGUISelectTheatreButtonPressed(registerID, movieID);
	}
	
	public static void selectTheatreGUICancelButtonPressed() {
		backToMenu();
	}
	
	//SelectSessionsGUI functions--------------------------------------------------------
	public static void selectSessionsGUISelectButtonPressed(String sessionID) {
		ManageReservationController.selectSessionsGUISelectButtonPressed(sessionID);
	}
	
	//SelectMovieGUI functions--------------------------------------------------------
	public static void selectMovieGUISelectMovieButtonPressed(String movieID) {
		ManageReservationController.selectMovieGUISelectMovieButtonPressed(movieID);
	}
	
	public static void selectMovieGUICancelButtonPressed() {
		backToMenu();
	}
	
	//guestMenuGUI functions--------------------------------------------------------
	public static void guestMenuSearchVoucherButtonPressed() {
		new SearchVoucherGUI();
	}
	
	public static void guestMenuSearchReservationButtonPressed() {
		new SearchReservationsGUI();
	}
	
	public static void guestMenuBookReservationButtonPressed() {
		ManageReservationController.guestMenuBookReservationButtonPressed();
	}
	
	public static void guestMenuCancelReservationButtonPressed() {
		new CancelReservationGUI();
	}
	
	public static void guestMenuLoginButtonPressed() {
		new LoginMenuGUI();
	}
	
	public static void guestMenuSignUpButtonPressed() {
		new SignupGUI();
	}
	
	public static void guestMenuExitButtonPressed() {
		prog.exitProgram();
	}
	
	public static void saveAndExit() {
		prog.exitProgram();
	}
	
	//LoginMenuGUI functions--------------------------------------------------------
	public static void loginMenuLoginButtonPressed(String username, String password) {
		ManageUserController.loginMenuLoginButtonPressed(username, password);
		
	}
	
	public static void loginMenuCancelButtonPressed() {
		System.out.println("Cancel pressed");
		new GuestMenuGUI();
	}
	
	public static void backToMenu() {
		if (prog.getLoginStatus() == 0) {
			new GuestMenuGUI();
		} else if (prog.getLoginStatus() == 1) {
			new RegisteredUserMenuGUI();
		} 
	}

	public static void logout() {
		prog.logout();
	}
	

}
