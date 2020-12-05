package Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Domain.Movie;
import Domain.Register;
import Domain.Reservation;
import Domain.Theatre;
import Domain.Voucher;
import Presentation.GetPaymentGUI;
import Presentation.SelectMovieGUI;
import Presentation.SelectSeatGUI;
import Presentation.SelectSessionsGUI;
import Presentation.SelectTheatreGUI;
import Presentation.ViewReservationIDGUI;
import Presentation.ViewVoucherIDGUI;


public class ManageReservationController {
	static TheatreProgram prog = TheatreProgram.getInstance();
	
	public static String SearchReservationButtonPressed(String reservationID) {
		Reservation reservation = prog.getReservation(reservationID);
		
		StringBuilder str  = new StringBuilder();  
		
		if (reservation == null) {
			str.append("No reservation by that ID was found!");
		} else {
			str.append("Reservation ID: " + reservationID + "\n");
			str.append("Movie name: " + reservation.getTicket().getRegister().getMovie().getMovieName() + "\n");
			str.append("Theatre name: " + reservation.getTicket().getRegister().getTheatre().getTheatreName() + "\n");
			str.append("Theatre Address: " + reservation.getTicket().getRegister().getTheatre().getTheatreAddress() + "\n");
			str.append("Show Date and time: " + reservation.getTicket().getRegister().getShowDate() + ", at " + reservation.getTicket().getRegister().getShowHour() + "\n");
			str.append("Seat row: " + reservation.getTicket().getSeatRow() + "\n");
			str.append("Seat column: " + reservation.getTicket().getSeatColumn() + "\n");
			str.append("Amount paid (price - voucher credit): " + reservation.getReceipt().getCost() + "$" + "\n");
			if (reservation.getUserID() == null) {
				str.append("This reservation was made by a guest" + "\n");
			} else {
				str.append("User ID of user who made this reservation: " + reservation.getUserID() + "\n");
			}
		}
		
		return new String(str);
	}	
	//CancelReservationGUI Functions------------------------------------------------
	public static void CancelReservationGUIcancelReservationPressed(String reservationID) {
		if (prog.canCancelReservation(reservationID) == false) {
			JOptionPane.showMessageDialog(null, "Reservation cannot be cancelled! Ticket can only be cancelled up to 72 hours or 3 days prior to the show!", "Error", JOptionPane.ERROR_MESSAGE);
			MenuController.backToMenu();
			return;
		}
		
		System.out.println("cancel reservation pressed");
		Voucher voucher = prog.cancelReservation(reservationID);
		
		if (voucher == null) {
			JOptionPane.showMessageDialog(null, "Reservation cannot be cancelled, ensure it is the correct ID and you have permission to do that! Please try again!", "Error", JOptionPane.ERROR_MESSAGE);
			MenuController.backToMenu();
		} else {
			JOptionPane.showMessageDialog(null, "Reservation cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			
			new ViewVoucherIDGUI("Voucher amount: " + voucher.getCredit() + "$\n"+"Voucher ID: "+voucher.getVoucherID() +"\nMAKE SURE YOU REMEMBER YOUR VOUCHER ID!");
			
		}

	}	
	
	//SelectSeatGUI functions--------------------------------------------------------
	public static void selectSeatGUISelectSeatButtonPressed(String registerID, String seatRow, String seatCol) {
		String reservationId = null;
		if (prog.getCurrentPayment() == null && prog.getLoginStatus() != 1) {
			new GetPaymentGUI(registerID, seatRow, seatCol);
		} else {
			String voucherID = null;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to use a voucher? (You can only use 1 voucher per purchase!)");
			if(dialogResult == JOptionPane.YES_OPTION){
				voucherID = JOptionPane.showInputDialog("Please enter the voucher ID");
				if (prog.voucherCanBeUsed(voucherID)) {
					JOptionPane.showMessageDialog(null, "Voucher will be applied in this purchase!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The specified voucher cannot be used", "Error", JOptionPane.ERROR_MESSAGE);
					selectSeatGUISelectSeatButtonPressed(registerID, seatRow, seatCol);
					return;
				}
			} else if (dialogResult == JOptionPane.CANCEL_OPTION) {
				MenuController.backToMenu();
				return;
			}
			
			try {
				reservationId = prog.bookReservation(registerID, Integer.parseInt(seatRow.trim()), Integer.parseInt(seatCol.trim()), voucherID, null);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter numbers as row and column numbers", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if (reservationId == null) {
				JOptionPane.showMessageDialog(null, "Failed to make the reservation", "Error", JOptionPane.ERROR_MESSAGE);
				MenuController.backToMenu();
			} else {
				JOptionPane.showMessageDialog(null, "Reservation successfully made!", "Success", JOptionPane.INFORMATION_MESSAGE);
				new ViewReservationIDGUI("Your reservation ID is: " + reservationId);
			}
		}
	}	
	
	//SelectTheatreGUI functions--------------------------------------------------------
	public static void selectTheatreGUISelectTheatreButtonPressed(String theatreID, String movieID) {
		ArrayList<Register> registerList = prog.getRegisterListOfMovie(theatreID, movieID);
		
		new SelectSessionsGUI(parseRegisterList(registerList));
	}
	
	private static String parseRegisterList(ArrayList<Register> registerList) {
		StringBuilder str  = new StringBuilder();
		if (registerList.size() == 0) {
			str.append("There are no sessions available!");
		} else {
			Register currentRegister =  registerList.get(0);
			Theatre currentTheatre = currentRegister.getTheatre();
			Movie currentMovie = currentRegister.getMovie();
			
			if (currentTheatre == null || currentMovie == null) {
				System.err.println("ERROR! on parseRegisterList on ManageReservationController where a register does not have movie or theatre object!");
				return "There are no sessions available!";				
			}
			
			boolean isLoggedIn = (prog.getLoginStatus() == 1);
			
			str.append("Movie Name: " + currentMovie.getMovieName() + "\n");
			str.append("Theatre Name: " + currentTheatre.getTheatreName() + "\n");
			str.append("Theatre Address: " + currentTheatre.getTheatreAddress() + "\n");
			str.append("____________________________________________\n\n");
			
			int missed = 0;
			
			for (int i = 0; i < registerList.size(); i++) {
				currentRegister =  registerList.get(i);
				if(currentRegister == null || currentTheatre == null) {
					System.err.println("Warning in parsing register list!");
				}
				
				if (currentRegister.isEarly() == false) {
					str.append("Selection ID: " + currentRegister.getRegisterID() + "\n");
					str.append("Show Date: " + currentRegister.getShowDate() + ", at: " + currentRegister.getShowHour() + "\n");
					str.append("Price: " + currentRegister.getPrice() + "$" + "\n");
					str.append("Seats remaining: " + (currentRegister.getSeatList().getSeatMax() - currentRegister.getSeatList().getSeatUsed())+ "\n");
					
					str.append("____________________________________________\n\n");
				} else {
					if (isLoggedIn == true) {
						str.append("***EARLY RESERVATION!!!***"+ "\n");
						str.append("Selection ID: " + currentRegister.getRegisterID() + "\n");
						str.append("Show Date: " + currentRegister.getShowDate() + ", at: " + currentRegister.getShowHour() + "\n");
						str.append("Announcement Date: " + currentRegister.getShowDate() + ", at: " + currentRegister.getShowHour() + "\n");
						str.append("Price: " + currentRegister.getPrice() + "$" + "\n");
						str.append("Early seats remaining: " + (currentRegister.getSeatList().getEarlySeatMax()- currentRegister.getSeatList().getEarlySeatUsed())+ "\n");
						str.append("____________________________________________\n\n");
					} else {
						missed ++;
					}
				}
			}
			
			if (missed > 0) {
				str.append("You missed a total of "+missed+" offers due to not being logged in!"+ "\n");
			}
			
		}
		return new String (str);
	}
	
	//SelectSessionsGUI functions--------------------------------------------------------
	public static void selectSessionsGUISelectButtonPressed(String registerID) {
		// TODO Auto-generated method stub
		System.out.println("select theatre pressed");
		char [][] seatList = prog.getSeatList(registerID);
		
		if (seatList == null) {
			JOptionPane.showMessageDialog(null, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
			MenuController.backToMenu();
		} else {
			new SelectSeatGUI(seatList, registerID);
		}
	}
	
	
	//SelectMovieGUI functions--------------------------------------------------------
	public static void selectMovieGUISelectMovieButtonPressed(String movieID) {
		System.out.println("select movie pressed");
		ArrayList<Theatre> theatreList = prog.getTheatreList();
		
		if (theatreList == null || theatreList.size() == 0) {
			new SelectTheatreGUI("There are no theatre available!", movieID);
		} else {
			new SelectTheatreGUI(parseTheareList(theatreList), movieID);
		}
	}
	
	private static String parseTheareList(ArrayList<Theatre> theatreList) {
		StringBuilder str  = new StringBuilder(); 
		
		for (int i = 0; i < theatreList.size(); i++) {
			Theatre currentTheatre = theatreList.get(i);
			
			if(currentTheatre == null) {
				System.err.println("Warning in parsing theatre list!");
			}
			str.append("Theatre ID: " + currentTheatre.getTheatreID() + "\n");
			str.append("Theatre Name: " + currentTheatre.getTheatreName() + "\n");
			str.append("Theatre Address: " + currentTheatre.getTheatreAddress() + "\n");
			str.append("____________________________________________\n\n");
		}
		
		return new String(str);
	}
	
	//guestMenuGUI functions--------------------------------------------------------
	public static void guestMenuBookReservationButtonPressed() {
		System.out.println("book reservation pressed");
		
		ArrayList<Movie> movieList = prog.getMovieList();
		
		if (movieList == null || movieList.size() == 0) {
			new SelectMovieGUI("No movie found!");
		} else {
			new SelectMovieGUI(parseMovieList(movieList));
		}
	}
	
	private static String parseMovieList(ArrayList<Movie> movieList) {
		StringBuilder str  = new StringBuilder(); 
		
		for (int i = 0; i < movieList.size(); i++) {
			Movie currentMovie =  movieList.get(i);
			str.append("Movie ID: " + currentMovie.getMovieID() + "\n");
			str.append("Movie Name: " + currentMovie.getMovieName() + "\n");
			str.append("Movie rating: " + currentMovie.getRating() + "\n");
			str.append("Release Date: " + currentMovie.getReleaseDate() + "\n");
			str.append("\nMovie Description:\n" + currentMovie.getMovieDescription() + "\n");
			str.append("____________________________________________\n\n");
		}
		return new String(str);
	}
	
	
}
