package Domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Register {
	private Movie movie;
	private Theatre theatre;
	private double price;
	private SeatList seatList;
	private LocalDate showDate;
	private int showHour;
	private LocalDate announcementDate;
	private String registerID;
	private int seatRow;
	private int seatColumn;
	
	/**
	 * Create a register object and generate seatList
	 * @param movie
	 * @param theatre
	 * @param price
	 * @param row
	 * @param col
	 * @param earlyBooking
	 * @param showYear
	 * @param showMonth
	 * @param showDay
	 * @param showHour
	 * @param announcementYear
	 * @param announcementMonth
	 * @param announcementDay
	 */
	public Register(Movie movie, Theatre theatre, double price, int seatRow, int seatColumn, boolean earlyBooking, int showYear, int showMonth, 
			int showDay, int showHour, int announcementYear, int announcementMonth, int announcementDay) {
		this(movie, theatre, price, seatRow, seatColumn, earlyBooking, showYear, showMonth, showDay, showHour, announcementYear, announcementMonth, announcementDay, UUID.randomUUID().toString());
		
//		LocalDate today = LocalDate.now();
//		LocalDate newDate = LocalDate.of(2932, 3, 23);
//		System.out.println(today);
//		System.out.println(newDate);
//		System.out.println(today.getDayOfMonth());
//		System.out.println(today);
//		today = today.plusMonths(2);
//		System.out.println(today);
	}
	
	
	public boolean isEarly() {
		LocalDate today = LocalDate.now();

		if (ChronoUnit.DAYS.between(today, announcementDate) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Create a register object and generate seatList (with specified registerID)
	 * @param movie
	 * @param theatre
	 * @param price
	 * @param row
	 * @param col
	 * @param earlyBooking
	 * @param showYear
	 * @param showMonth
	 * @param showDay
	 * @param showHour
	 * @param announcementYear
	 * @param announcementMonth
	 * @param announcementDay
	 * @param registerID
	 */
	public Register(Movie movie, Theatre theatre, double price, int seatRow, int seatColumn, boolean earlyBooking, int showYear, int showMonth, 
			int showDay, int showHour, int announcementYear, int announcementMonth, int announcementDay, String registerID) {
		this.setMovie(movie);
		this.setTheatre(theatre);
		this.setPrice(price);
		this.seatList = new SeatList(seatRow, seatColumn, earlyBooking);
		this.setShowDate(LocalDate.of(showYear, showMonth, showDay));
		this.setShowHour(showHour);
		this.setAnnouncementDate(LocalDate.of(announcementYear, announcementMonth, announcementDay));
		this.registerID = registerID;
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
	}
	
	/**
	 * Occupy a seat, return true if it worked
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	public boolean occupySeat(int row, int col, boolean isEarly) {
		return seatList.occupySeat(row, col, isEarly);
	}
	
	/**
	 * Check if the seat Can be occupied
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	public boolean canOccupySeat(int row, int col, boolean isEarly) {
		if (row > seatRow || col > seatColumn) {
			return false;
		}
		return seatList.canOccupySeat(row, col, isEarly);
	}
	
	/**
	 * Un-occupy a seat, return true if it worked
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	public boolean freeSeat(int row, int col) {
		return seatList.freeSeat(row, col);
	}
	
	char[][] getSeatListInChar() {
		return seatList.getSeatListInChar();
	}
	
	public int getShowYear() {
		return showDate.getYear();
	}
	
	public int getShowMonth() {
		return showDate.getMonthValue();
	}
	
	public int getShowDay() {
		return showDate.getDayOfMonth();
	}
	
	public int getAnnouncementYear() {
		return announcementDate.getYear();
	}
	
	public int getAnnouncementMonth() {
		return announcementDate.getMonthValue();
	}
	
	public int getAnnouncementDay() {
		return announcementDate.getDayOfMonth();
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public SeatList getSeatList() {
		return seatList;
	}
	public void setSeatList(SeatList seatList) {
		this.seatList = seatList;
	}
	
	public String getShowHour() {
		if (showHour == 0 || showHour == 24) {
			return "12 am";
		} else if (showHour < 12) {
			return showHour + " " + "am";
		} else if (showHour == 12) {
			return "12 pm";
		} else if (showHour < 24) {
			return (showHour - 12) + " " + "am";
		} else {
			return "No hour specified";
		}
	}
	
//	public int getShowHour() {
//		return showHour;
//	}
	public void setShowHour(int showHour) {
		this.showHour = showHour;
	}
	public String getRegisterID() {
		return registerID;
	}
	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public LocalDate getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(LocalDate announcementDate) {
		this.announcementDate = announcementDate;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
	
	public int getShowHourInInteger() {
		return showHour;
	}
}
