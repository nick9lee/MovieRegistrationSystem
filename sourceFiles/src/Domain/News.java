package Domain;

public class News {
	private Register register;
	private String information;
	
	News(Register register, String information) {
		this.register = register;
		this.information = information;
	}
	
	/**
	 * Returns the register id
	 * @return
	 */
	public String getRegisterID() {
		return register.getRegisterID();
	}
	
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}
}
