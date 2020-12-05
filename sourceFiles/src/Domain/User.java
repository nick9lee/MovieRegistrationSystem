package Domain;

import java.util.UUID;

public class User {
	protected String username;
	protected String password;
	protected boolean isAdmin;
	protected String userID;
	
	User(String username, String password) {
		this.username = username;
		this.password = password;
		isAdmin = false;
		userID = UUID.randomUUID().toString();
	}
	
	User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	User(String username, String password, boolean isAdmin, String userID) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.userID = userID;
	}
	
	User(String username, String password, String userID) {
		this.username = username;
		this.password = password;
		this.isAdmin = false;
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
