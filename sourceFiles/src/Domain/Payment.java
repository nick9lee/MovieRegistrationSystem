package Domain;

public class Payment {
	private Name cardHolderName;
	private String billingAddress;
	private int cardNumber;
	private int expirationYear;
	private int expirationMonth;
	private int pin;
	
	public Payment (String firstName, String middleName, String lastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin) {
		this.cardHolderName = new Name(firstName, middleName, lastName);
		this.billingAddress = billingAddress;
		this.cardNumber = cardNumber;
		this.setExpirationYear(expYear);
		this.setExpirationMonth(expMonth);
		this.pin = pin;
	}
	
	public Name getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(Name cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
}
