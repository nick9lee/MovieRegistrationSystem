package Domain;

public class Receipt {
	private double cost;
	private Payment paymentInfo;
	
	Receipt(double cost, String firstName, String middleName, String lastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin) {
		this.cost = cost;
		this.paymentInfo = new Payment(firstName, middleName, lastName, billingAddress, cardNumber, expYear, expMonth, pin);
	}
	
	public Receipt(double price, Payment paymentInfo) {
		this.cost = price;
		this.paymentInfo = new Payment(paymentInfo.getCardHolderName().getFirstName(), paymentInfo.getCardHolderName().getMiddleName(), paymentInfo.getCardHolderName().getLastName(),paymentInfo.getBillingAddress() ,paymentInfo.getCardNumber(), paymentInfo.getExpirationYear(), paymentInfo.getExpirationMonth(), paymentInfo.getPin());
	}

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Payment getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
}
