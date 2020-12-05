package Domain;

public class Admin extends User {
	
	Admin(String username, String password) {
		super(username, password, true);
	}
	
	Admin(String username, String password, String userID) {
		super(username, password, true, userID);
	}
}
