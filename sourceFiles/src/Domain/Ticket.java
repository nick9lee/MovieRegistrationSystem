package Domain;

import java.time.LocalDate;

public class Ticket {
	private int seatRow;
	private int seatColumn;
	private Register register;
	private LocalDate paymentDate;
	
	
	public Ticket(int seatRow, int seatColumn, Register register) {
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.register = register;
		this.paymentDate = LocalDate.now();
	}
	
	public int getPaymentYear() {
		return paymentDate.getYear();
	}
	
	public int getPaymentMonth() {
		return paymentDate.getMonthValue();
	}
	
	public int getPaymentDay() {
		return paymentDate.getDayOfMonth();
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
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
}
