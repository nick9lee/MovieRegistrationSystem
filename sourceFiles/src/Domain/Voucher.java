package Domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Voucher {
	private double credit;
	private String voucherID;
	private String userID;
	private LocalDate expirationDate;
	
	public Voucher(double credit, String userID) {
		this.credit = credit;
		this.userID = userID;
		voucherID = UUID.randomUUID().toString();
		expirationDate = LocalDate.now().plusYears(1);
		
//		if (monthToNextPayment < 12) {
//			// Extend payment by a year from due date OR from today
//			if (monthToNextPayment >= 0) {
//				nextPaymentDate = nextPaymentDate.plusYears(1);
//			} else {
//				nextPaymentDate = today.plusYears(1);
	}
	
	Voucher(double credit, String userID, String voucherID, int expirationYear, int expirationMonth, int expirationDay) {
		this.credit = credit;
		this.userID = userID;
		if (userID != null) {
			if (userID.equals("null")) {
				this.userID = null;
			}
		}
		
		this.voucherID = voucherID;
//		this.expirationDate
		this.setExpirationDate(LocalDate.of(expirationYear, expirationMonth, expirationDay));
	}
	
	/**
	 * See if you can use it
	 * @return
	 */
	public boolean canBeUsed() {
		LocalDate today = LocalDate.now();

		long daysDifference = ChronoUnit.DAYS.between(today, expirationDate);
		if (daysDifference >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(String voucherID) {
		this.voucherID = voucherID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
}
