package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Movie {
	private String movieName;
	private LocalDate releaseDate;
	private String movieDescription;
	private String rating;
	private ArrayList<Register> registerList;
	private ArrayList<Theatre> theatreList;
	private String movieID;
	
	public Movie(Movie movie) {
		this(movie.getMovieName(), movie.getReleaseYear(), movie.getReleaseMonth(), movie.getReleaseDay(), movie.getRating(), movie.getMovieDescription());
	}
	
	public Movie(String movieName, int year, int month, int day, String rating, String movieDescription) {
		this(movieName, year, month, day, rating, movieDescription, UUID.randomUUID().toString());
	}
	
	Movie(String movieName, int year, int month, int day, String rating, String movieDescription, String movieID) {
		this.movieName = movieName;
		this.releaseDate = LocalDate.of(year, month, day);
		this.rating = rating;
		this.movieDescription = movieDescription;
		this.registerList = new ArrayList<Register>();
		this.theatreList = new ArrayList<Theatre>();
		this.movieID = movieID;
	}
	
	/**
	 * Add register to list
	 * @param register
	 */
	public void addRegister(Register register) {
		registerList.add(register);
		
		if (isTheatreInList(register.getTheatre().getTheatreID()) == false) {
			addTheatre(register.getTheatre());
		}
	}
	
	/**
	 * Add theatre to list
	 * @param theatre
	 */
	public void addTheatre(Theatre theatre) {
		theatreList.add(theatre);
	}
	
	// Return if the theatre ID is in list
	public boolean isTheatreInList(String theatreID) {
		for (int i = 0; i < theatreList.size(); i++) {
			if (theatreList.get(i).getTheatreID().equals(theatreID)) {
				return true;
			}
		}
		return false;
	}
  	
	/**
	 * Remove register from list
	 * @param registerID
	 */
	void removeRegister(String registerID) {
		for (int i = 0; i < registerList.size(); i++) {
			if (registerList.get(i).getRegisterID().equals(registerID)) {
				registerList.remove(i);
			}
		}
	}
	
	public int getReleaseYear() {
		return releaseDate.getYear();
	}
	
	public int getReleaseMonth() {
		return releaseDate.getMonthValue();
	}
	
	public int getReleaseDay() {
		return releaseDate.getDayOfMonth();
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieDescription() {
		return movieDescription;
	}
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	public ArrayList<Register> getRegisterList() {
		return registerList;
	}
	public void setRegisterList(ArrayList<Register> registerList) {
		this.registerList = registerList;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ArrayList<Theatre> getTheatreList() {
		return theatreList;
	}

	public void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
