package Domain;

public class SeatList {
	private Seat[][] seatList;
	private int seatUsed;
	private int seatMax;
	private int earlySeatMax;
	private int earlySeatUsed;
	
	/**
	 * Constructs object, specify if early booking is available
	 * @param row
	 * @param col
	 * @param earlyBooking
	 */
	public SeatList(int row, int col, boolean earlyBooking) {
		seatList = new Seat[row][col];
		// Populate the seatlist
		for (int i = 0; i < row; i++)  {
			for (int j = 0; j < col; j++) {
				seatList[i][j] = new Seat(i, j);
			}
		}
		
		seatMax = row*col;
		seatUsed = 0;
		earlySeatUsed = 0;
		
		if (earlyBooking == true) {
			earlySeatMax = seatMax / 10;
		} else {
			earlySeatMax = 0;
		}
	}
	
	/**
	 * Return if you can occupy the seat specified
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	boolean canOccupySeat(int row, int col, boolean isEarly) {
		// Check if there is even any available seats
		if (seatUsed >= seatMax) {
			return false;
		}
		
		if (isEarly == true) {
			// Check if there is even slots for early registration
			if (earlySeatUsed >= earlySeatMax) {
				return false;
			}
		}
		
		// Try to occupy seat, if seat is already occupied, return false
		if (seatList[row][col].isTaken() == false) {
			return true;
		} else {
			return false;
		}		
	}
	
	
	/**
	 * Occupy a seat, return true if it worked
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	boolean occupySeat(int row, int col, boolean isEarly) {
		// Check if there is even any available seats
		if (seatUsed >= seatMax) {
			return false;
		}
		
		if (isEarly == true) {
			// Check if there is even slots for early registration
			if (earlySeatUsed >= earlySeatMax) {
				return false;
			}
		}
		
		// Try to occupy seat, if seat is already occupied, return false
		if (seatList[row][col].occupySeat(isEarly) == true) {
			if (isEarly == true) {
				earlySeatUsed++;
			}
			seatUsed++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Un-occupy a seat, return true if it worked
	 * @param row
	 * @param col
	 * @param isEarly
	 * @return
	 */
	boolean freeSeat(int row, int col) {
		// If no seats used, no need to proceed
		if (seatUsed == 0) {
			return false;
		}
		
		boolean isEarly = seatList[row][col].isEarlyRegistration();
		
		if (seatList[row][col].unoccupySeat()) {
			if (isEarly == true) {
				earlySeatUsed--;
			}
			seatUsed--;
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the list of seats in char
	 * @return
	 */
	public char[][] getSeatListInChar() {
		char[][] seats = new char[seatList.length][seatList[0].length];
		
		// Load seatlist into char array
		for (int i = 0; i < seatList.length; i++)  {
			for (int j = 0; j < seatList[i].length; j++) {
				if (seatList[i][j].isTaken() == false) {
					seats[i][j] = 'f';
				} else if (seatList[i][j].isEarlyRegistration() == true) {
					seats[i][j] = 'e'; // Want to not show it anymore (Show for debugging purposes)
//					seats[i][j] = 'r';
				} else {
					seats[i][j] = 'r';
				}
			}
		}		
		
		return seats;
	}
	
	public Seat[][] getSeatList() {
		return seatList;
	}
	public void setSeatList(Seat[][] seatList) {
		this.seatList = seatList;
	}
	public int getSeatUsed() {
		return seatUsed;
	}
	public void setSeatUsed(int seatUsed) {
		this.seatUsed = seatUsed;
	}
	public int getSeatMax() {
		return seatMax;
	}
	public void setSeatMax(int seatMax) {
		this.seatMax = seatMax;
	}
	public int getEarlySeatMax() {
		return earlySeatMax;
	}
	public void setEarlySeatMax(int earlySeatMax) {
		this.earlySeatMax = earlySeatMax;
	}
	public int getEarlySeatUsed() {
		return earlySeatUsed;
	}
	public void setEarlySeatUsed(int earlySeatUsed) {
		this.earlySeatUsed = earlySeatUsed;
	}
}
