package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Domain.*;

public class TheatreProgram {
	private Database database;
	private User currentUser;
	private Payment currentPayment;
	
	private static TheatreProgram theatre_program_instance = null; 
	
	/**
	 * static method to create instance of Singleton class 
	 * @return
	 */
    public static TheatreProgram getInstance() 
    { 
        if (theatre_program_instance == null) 
        	theatre_program_instance = new TheatreProgram(Database.getInstance()); 
  
        return theatre_program_instance; 
    }
	
	public TheatreProgram(Database database) {
		this.database = database;
	}
	
	/**
	 * Return the list of movie
	 * 
	 * @return
	 */
	public ArrayList<Movie> getMovieList() {
		return database.getMovieList();
	}
	
	/**
	 * Get the register list of movie
	 * @param movieID
	 * @return
	 */
	public ArrayList<Register> getRegisterListOfMovie(String movieID) {
		Movie movie = database.getMovieByID(movieID);
		if (movie == null) {
			return new ArrayList<Register>();
		} else {
			return movie.getRegisterList();
		}
	}
	
	/**
	 * Get theatre lit of movie
	 * @param movieID
	 * @return
	 */
	public ArrayList<Theatre> getTheatreListOfMovie(String movieID) {
		Movie movie = database.getMovieByID(movieID);
		if (movie == null) {
			return new ArrayList<Theatre>();
		} else {
			return movie.getTheatreList();
		}
	}
	
	/**
	 * Add a movie to the database, return false if a movie of that name already exist
	 * @param movie
	 * @return
	 */
	public boolean addMovie(Movie movie) {
		if (database.addMovie(movie.getMovieName(), movie.getReleaseYear(), movie.getReleaseMonth(), movie.getReleaseDay(), movie.getRating(), movie.getMovieDescription()) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add a theatre to the database, return false if a theatre of that name already exist
	 * @param theatre
	 * @return
	 */
	public boolean addTheatre(Theatre theatre) {
		if (database.addThreater(theatre.getTheatreName(), theatre.getTheatreAddress())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add a register to database, return true if successful, false if fail
	 * @param register
	 * @return
	 */
	public boolean addRegister(Register register) {
		if (database.addRegister(register.getMovie().getMovieID(), register.getTheatre().getTheatreID(), register.getPrice(), register.getSeatRow(), register.getSeatColumn(), register.getShowYear(), register.getShowMonth(), register.getShowDay(), register.getShowHourInInteger(), register.getAnnouncementYear(), register.getAnnouncementMonth(), register.getAnnouncementDay())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Create new user, return true if successful, false if not 
	 * @param user
	 * @return
	 */
	public boolean addUser(RegisteredUser user) {
		User newUser = database.signUp(user.getUsername(), user.getPassword(), user.getName(), user.getPaymentInfo());
		if (newUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Create new user, return true if successful, false if not 
	 * @param user
	 * @return
	 */
	public boolean signUp(String username, String password, Name name, Payment paymentinfo) {
		User newUser = database.signUp(username, password, name, paymentinfo);
		if (newUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Login and replace user with the new specified user, also returns the username
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(String username, String password) {
		currentPayment = null;
		User retrivedUser = database.verifyAccount(username, password);
		if (retrivedUser != null) {
			currentUser = retrivedUser;
			return currentUser.getUsername();
		} else {
			return null;
		}
	}
	
	/**
	 * Logout
	 */
	public void logout() {
		currentUser = null;
		currentPayment = null;
	}
	
	/**
	 * Get login status 0 = not login, 1 = registered user, 2 = admin
	 * @return
	 */
	public int getLoginStatus() {
		if (currentUser == null) {
			return 0;
		} else if (currentUser.isAdmin() == false) {
			return 1;
		} else {
			return 2;
		}
	}
	
	/**
	 * Return the list of news
	 * @return
	 */
	public ArrayList<News> getNewsList() {
		return database.getNewsList();
	}
	
	/**
	 * Return reservation by id
	 * @param reservationID
	 * @return
	 */
	public Reservation getReservation(String reservationID) {
		return database.getReservationByID(reservationID);
	}
	
	/**
	 * Return the seat list if registerID found, return null otherwise
	 * @param RegisterID
	 * @return
	 */
	public char[][] getSeatList(String RegisterID) {
		return database.getSeatListByRegisterID(RegisterID);
	}
	
	/**
	 * Book a reservation and return registrationID if successful
	 * @param registerID
	 * @param seatRow
	 * @param seatColumn
	 * @param voucherID
	 * @param paymentInfo
	 * @return
	 */
	public String bookReservation(String registerID, int seatRow, int seatColumn, String voucherID, Payment paymentInfo) {
		if (paymentInfo == null) {
			paymentInfo = currentPayment;
		}
		if (currentUser != null) {
			return database.addReservation(registerID, seatRow, seatColumn, currentUser.getUserID(), voucherID, paymentInfo);
		} else {
			return database.addReservation(registerID, seatRow, seatColumn, null, voucherID, paymentInfo);
		}
	}
	
	public boolean canCancelReservation(String reservationID) {
		Reservation reservation = database.getReservationByID(reservationID);
		
		//If no reservation found for that ID
		if (reservation == null) {
			return false;
		}
		
		return reservation.canCancel();
	}
	
	/**
	 * Return if the voucher can be used
	 * @param voucherID
	 * @return
	 */
	public boolean voucherCanBeUsed(String voucherID) {
		return database.canVoucherBeUsed(voucherID, currentUser);
	}
	
	
	/**
	 * Cancel reservation, create a voucher and returns the voucher, return null if fails
	 * @param reservationID
	 * @return
	 */
	public Voucher cancelReservation(String reservationID) {
		Reservation reservation = database.getReservationByID(reservationID);
		
		//If no reservation found for that ID
		if (reservation == null) {
			return null;
		}
		
		/**
		 * Can't cancel reservation
		 */
		if (reservation.canCancel() == false) {
			return null;
		}
		
		String userID = reservation.getUserID();
		Register register = reservation.getTicket().getRegister();

		// Check if they have permission to access it
		if (userID != null) {
			System.out.println("Login status: " + getLoginStatus());
			if (getLoginStatus() == 1) {
				RegisteredUser user = (RegisteredUser) currentUser;
				if (user.getUserID().equals(userID)) {
					user.removeReservationById(reservationID);
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		double price = reservation.getTicket().getRegister().getPrice();
		if (database.removeReservationByID(reservationID) == true) {
			if (currentUser == null) {
				price *= 0.85;
			}
			register.freeSeat(reservation.getTicket().getSeatRow(), reservation.getTicket().getSeatColumn());
			return database.addVoucher(price, (RegisteredUser) currentUser);
		} else {
			return null;
		}
	}
	
	/**
	 * Pay account fee
	 * @return
	 */
	public boolean payUserFee(Payment paymentInfo) {
		if (getLoginStatus() == 1) {
			RegisteredUser user = (RegisteredUser) currentUser;
			if (paymentInfo == null) {
				paymentInfo = user.getPaymentInfo();
			}
			return user.payAccountFee(paymentInfo);
		}
		return false;
	}
	
	
	/**
	 * Get database to save data and then exit program
	 */
	public void exitProgram() {
		try {
			database.saveToDatabase();
		} catch (IOException e) {
			System.err.println("Error saving to database");
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 * get the list of vouchers associated with the account
	 * @return
	 */
	public ArrayList<Voucher> getVoucherOfUser() {
		if (getLoginStatus() == 1) {
			RegisteredUser user = (RegisteredUser) currentUser;
			return user.getVoucherList();
		}
		return new ArrayList<Voucher>();
	}
	
	/**
	 * Return voucher, null if cannot be found
	 * @param voucherID
	 * @return
	 */
	public Voucher searchVoucher(String voucherID) {
		return database.getVoucherByID(voucherID);
	}
	
	// Getters and setters
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Payment getCurrentPayment() {
		return currentPayment;
	}

	public void setCurrentPayment(Payment currentPayment) {
		this.currentPayment = currentPayment;
	}

	public ArrayList<Register> getRegisterListOfMovie(String theatreID, String movieID) {
		return database.getRegisterListOfMovie(theatreID, movieID);
	}

	public ArrayList<Theatre> getTheatreList() {
		ArrayList<Theatre> theatreList = database.getTheatreList();
		if (theatreList == null) {
			return new ArrayList<Theatre>(0);
		} else {
			return theatreList;
		}
	}
}
