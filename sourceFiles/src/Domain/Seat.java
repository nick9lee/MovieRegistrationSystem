package Domain;

public class Seat {
	private int rowNumber;
	private int columnNumber;
	private boolean isEarlyRegistration;
	private boolean isTaken;
	
	/**
	 * Constructs a seat object
	 * @param rowNumber
	 * @param columnNumber
	 */
	public Seat(int rowNumber, int columnNumber) {
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		
		isEarlyRegistration = false;
		isTaken = false;
	}
	
	/**
	 * Occupy a seat
	 * @param isEarly
	 * @return 
	 */
	boolean occupySeat(boolean isEarly) {
		if (isTaken == false) {
			if (isEarly == true) {
				isEarlyRegistration = true;
			}
			isTaken = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Un-occupy a seat
	 * @return 
	 */
	boolean unoccupySeat() {
		if (isTaken == true) {
			isEarlyRegistration = false;
			isTaken = false;
			return true;
		} else {
			return false;
		}
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
//	public void setRowNumber(int rowNumber) {
//		this.rowNumber = rowNumber;
//	}
	public int getColumnNumber() {
		return columnNumber;
	}
//	public void setColumnNumber(int columnNumber) {
//		this.columnNumber = columnNumber;
//	}
	public boolean isEarlyRegistration() {
		return isEarlyRegistration;
	}
//	public void setEarlyRegistration(boolean isEarlyRegistration) {
//		this.isEarlyRegistration = isEarlyRegistration;
//	}
	public boolean isTaken() {
		return isTaken;
	}
//	public void setTaken(boolean isTaken) {
//		this.isTaken = isTaken;
//	}
}
