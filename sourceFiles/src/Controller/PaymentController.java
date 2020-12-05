package Controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Domain.Payment;
import Domain.Voucher;
import Presentation.GetPaymentGUI;

public class PaymentController {
	private static TheatreProgram prog = TheatreProgram.getInstance();
	
	public static void addPaymentButtonPressed(String firstname, String middleName, String lastName, String billingAddress, String cardnumber, String expYear, String expMonth, String pin, String registerID, String seatRow, String seatCol) {
		Payment payment;
		try {
			payment = new Payment(firstname, middleName, lastName, billingAddress, Integer.parseInt(cardnumber), Integer.parseInt(expYear), Integer.parseInt(expMonth), Integer.parseInt(pin));
			
			prog.setCurrentPayment(payment);
			
			MenuController.selectSeatGUISelectSeatButtonPressed(registerID, seatRow, seatCol);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error !", "Error, card number, exp year, month and pin have to be integers!", JOptionPane.ERROR_MESSAGE);
			new GetPaymentGUI(registerID, seatRow, seatCol);
		}
	}
	
	public static String parseVoucherList(ArrayList<Voucher> voucherList) {
		StringBuilder str  = new StringBuilder(); 
		
		if (voucherList == null || voucherList.size() == 0) {
			str.append("You have available no voucher associated with your account " + "\n");
		}
		
		for (int i = 0; i < voucherList.size(); i++) {
			Voucher voucher =  voucherList.get(i);
			
			str.append("Voucher credit: " + voucher.getCredit() + "$" + "\n");
			str.append("Voucher expiration date: " + voucher.getExpirationDate() + "\n");
			str.append("Voucher ID: " + voucher.getVoucherID() + "\n");
			str.append("____________________________________________\n\n");
		}
		return new String(str);
	}
}
