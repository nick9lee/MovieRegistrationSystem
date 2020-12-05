package Domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Reservation {
	private Ticket ticket;
	private Receipt receipt;
	private String userID;
	private String reservationID;
//	private int seatRow;
//	private int seatColumn;
	
	Reservation(int seatRow, int seatColumn, Register register, double cost, String firstName, String middleName, String lastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin, String userID) {
		this(seatRow, seatColumn, register, cost, firstName, middleName, lastName, billingAddress, cardNumber, expYear, expMonth, pin, userID, UUID.randomUUID().toString());
	}
	
	Reservation(int seatRow, int seatColumn, Register register, double cost, String firstName, String middleName, String lastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin, String userID, String reservationID) {
		this.ticket = new Ticket(seatRow, seatColumn, register);
		this.receipt = new Receipt(cost, firstName, middleName, lastName, billingAddress, cardNumber, expYear, expMonth, pin);
		this.userID = userID;
		this.reservationID = reservationID;
//		this.seatRow = seatRow;
//		this.seatColumn = seatColumn;
	}
	
	public Reservation(int seatRow, int seatColumn, Register register, double price, String userID, Payment paymentInfo) {
		this.ticket = new Ticket(seatRow, seatColumn, register);
		this.receipt = new Receipt(price, paymentInfo);
		this.userID = userID;
		this.reservationID = UUID.randomUUID().toString();
//		this.seatRow = seatRow;
//		this.seatColumn = seatColumn;
	}
	
	/**
	 * return true if today is more than or equal to 3 days from watch
	 * @return
	 */
	public boolean canCancel() {
		LocalDate today = LocalDate.now();
		
		long daysDifference = ChronoUnit.DAYS.between(today, ticket.getRegister().getShowDate());
		
		System.out.println(daysDifference);
		if (daysDifference >= 3) {
			return true;
		} else {
			return false;
		}
		
	}

	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
//	public int getSeatRow() {
//		return seatRow;
//	}
//	public void setSeatRow(int seatRow) {
//		this.seatRow = seatRow;
//	}
//	public int getSeatColumn() {
//		return seatColumn;
//	}
//	public void setSeatColumn(int seatColumn) {
//		this.seatColumn = seatColumn;
//	}
}
