package Domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisteredUser extends User {
	private Name name;
	private Payment paymentInfo;
	private LocalDate joinInDate;
	private ArrayList<Reservation> reservationList;
	private ArrayList<Receipt> paymentHistory;
	private ArrayList<Voucher> voucherList;
	private LocalDate nextPaymentDate;
	
	RegisteredUser(RegisteredUser user) {
		this(user.getUsername(), user.getPassword(), user.getName(), user.getPaymentInfo());
	}
	
	RegisteredUser(String username, String password, String firstName, String middleName, String lastName, String paymentFirstName, String PaymentMiddleName, String PaymentLastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin) {
		super(username, password);
		this.name = new Name(firstName, middleName, lastName);
		this.paymentInfo = new Payment(paymentFirstName, PaymentMiddleName, PaymentLastName, billingAddress, cardNumber, expYear, expMonth, pin);
		this.joinInDate = LocalDate.now();
		this.reservationList = new ArrayList<Reservation>();
		this.paymentHistory = new ArrayList<Receipt>();
		this.voucherList = new ArrayList<Voucher>();
		this.nextPaymentDate = LocalDate.now();
		payAccountFee(paymentInfo);
	}
	
	/**
	 * For database
	 * @param username
	 * @param password
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param paymentFirstName
	 * @param PaymentMiddleName
	 * @param PaymentLastName
	 * @param billingAddress
	 * @param cardNumber
	 * @param expYear
	 * @param expMonth
	 * @param pin
	 * @param joinYear
	 * @param joinMonth
	 * @param joinDay
	 * @param paymentYear
	 * @param paymentMonth
	 * @param paymentDay
	 * @param userID
	 */
	RegisteredUser(String username, String password, String firstName, String middleName, String lastName, String paymentFirstName, String PaymentMiddleName, String PaymentLastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin, int joinYear, int joinMonth, int joinDay, int paymentYear, int paymentMonth, int paymentDay, String userID) {
		super(username, password, userID);
		this.name = new Name(firstName, middleName, lastName);
		this.paymentInfo = new Payment(paymentFirstName, PaymentMiddleName, PaymentLastName, billingAddress, cardNumber, expYear, expMonth, pin);
		this.setJoinInDate(LocalDate.of(joinYear, joinMonth, joinDay));
		this.nextPaymentDate = LocalDate.of(paymentYear, paymentMonth, paymentDay);
		this.reservationList = new ArrayList<Reservation>();
		this.paymentHistory = new ArrayList<Receipt>();
		this.voucherList = new ArrayList<Voucher>();
	}
	
	public RegisteredUser(String username, String password, Name name, Payment paymentInfo) {
		this(username, password, name.getFirstName(), name.getMiddleName(), name.getLastName(), paymentInfo.getCardHolderName().getFirstName(), paymentInfo.getCardHolderName().getMiddleName(), paymentInfo.getCardHolderName().getLastName(), paymentInfo.getBillingAddress(), paymentInfo.getCardNumber(), paymentInfo.getExpirationYear(), paymentInfo.getExpirationMonth(), paymentInfo.getPin());
	}

	/**
	 * Pay account fee, deadline extend by a year
	 * @param paymentInfo
	 * @return
	 */
	public boolean payAccountFee(Payment paymentInfo) {
		if (paymentInfo == null) {
			return false;
		}
		
		LocalDate today = LocalDate.now();
		
		int monthToNextPayment = (nextPaymentDate.getYear() - today.getYear())*12 + (nextPaymentDate.getMonthValue() - today.getMonthValue());
		
		if (monthToNextPayment < 12) {
			// Extend payment by a year from due date OR from today
			if (monthToNextPayment >= 0) {
				nextPaymentDate = nextPaymentDate.plusYears(1);
			} else {
				nextPaymentDate = today.plusYears(1);
			}
			
			paymentHistory.add(new Receipt(20.0, paymentInfo.getCardHolderName().getFirstName(), paymentInfo.getCardHolderName().getMiddleName(), paymentInfo.getCardHolderName().getLastName(), paymentInfo.getBillingAddress(), paymentInfo.getCardNumber(), paymentInfo.getExpirationYear(), paymentInfo.getExpirationMonth(), paymentInfo.getPin()));
			return true;
		} else {
			return false;
		}
	}
	
	public int getJoinYear() {
		return joinInDate.getYear();
	}
	
	public int getJoinMonth() {
		return joinInDate.getMonthValue();
	}
	
	public int getJoinDay() {
		return joinInDate.getDayOfMonth();
	}
	
	public int getNextPaymentYear() {
		return nextPaymentDate.getYear();
	}
	
	public int getNextPaymentMonth() {
		return nextPaymentDate.getMonthValue();
	}
	
	public int getNextPaymentDay() {
		return nextPaymentDate.getDayOfMonth();
	}
	
	/**
	 * Add Resevation
	 * @param reservation
	 */
	public void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}
	
	/**
	 * Remove a reservation by Id
	 * @param reservationId
	 */
	public void removeReservationById(String reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID().equals(reservationID)) {
				reservationList.remove(i);
			}
		}
	}
	
	/**
	 * Add voucher to voucher list
	 * @param voucher
	 */
	public void addVoucher(Voucher voucher) {
		voucherList.add(voucher);
	}
	
	/**
	 * Remove voucher by ID
	 * @param voucherID
	 */
	void removeVoucher(String voucherID) {
		for (int i = 0; i < voucherList.size(); i++) {
			if (voucherList.get(i).getVoucherID().equals(voucherID)) {
				voucherList.remove(i);
			}
		}
	}
	
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Payment getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}
	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}
	public ArrayList<Receipt> getPaymentHistory() {
		return paymentHistory;
	}
	public void setPaymentHistory(ArrayList<Receipt> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
	public ArrayList<Voucher> getVoucherList() {
		return voucherList;
	}
	public void setVoucherList(ArrayList<Voucher> voucherList) {
		this.voucherList = voucherList;
	}

	public LocalDate getJoinInDate() {
		return joinInDate;
	}

	public void setJoinInDate(LocalDate joinInDate) {
		this.joinInDate = joinInDate;
	}

	public LocalDate getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(LocalDate nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
}
