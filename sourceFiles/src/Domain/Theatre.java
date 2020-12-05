package Domain;

import java.util.UUID;

public class Theatre {
	private String theatreName;
	private String theatreAddress;
	private String theatreID;
	
	public Theatre(String theatreName, String theatreAddress) {
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatreID =  UUID.randomUUID().toString();
	}
	
	public Theatre(String theatreName, String theatreAddress, String theatreID) {
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatreID =  theatreID;
	}
	
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreAddress() {
		return theatreAddress;
	}
	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}
	public String getTheatreID() {
		return theatreID;
	}
	public void setTheatreID(String theatreID) {
		this.theatreID = theatreID;
	}
}
