package Domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;



/**
 * Singleton class
 * 
 * @author 10993
 *
 */
public class Database {
	private ArrayList<User> userList;
	private ArrayList<News> newsList;
	private ArrayList<Reservation> reservationList;
	private ArrayList<Movie> movieList;
	private ArrayList<Register> registerList;
	private ArrayList<Theatre> theatreList;
	private ArrayList<Voucher> voucherList;
	
	/*
	 * Paths
	 */
	private String userPath;
//	private String newsPath;
	private String reservationPath;
	private String moviePath;
	private String registerPath;
	private String theatrePath;
	private String voucherPath;

	private static Database database_instance = null;

	/**
	 * static method to create instance of Singleton class
	 * 
	 * @return
	 */
	public static Database getInstance() {
		if (database_instance == null)
			database_instance = new Database();

		return database_instance;
	}

	Database() {
		userList = new ArrayList<User>();
		newsList = new ArrayList<News>();
		reservationList = new ArrayList<Reservation>();
		movieList = new ArrayList<Movie>();
		registerList = new ArrayList<Register>();
		theatreList = new ArrayList<Theatre>();
		voucherList = new ArrayList<Voucher>();
		
		// Database path
		userPath = "userFile.txt";
		reservationPath = "reservationFile.txt";
		moviePath = "movieFile.txt";
		registerPath = "registerFile.txt";
	    theatrePath = "theatreFile.txt";
		voucherPath = "voucherFile.txt";
		
		loadFromDatabase();
	}

	/**
	 * Load data from local database
	 */
	public void loadFromDatabase() {
		// Clear the database first
		userList = new ArrayList<User>();
		newsList = new ArrayList<News>();
		reservationList = new ArrayList<Reservation>();
		movieList = new ArrayList<Movie>();
		registerList = new ArrayList<Register>();
		theatreList = new ArrayList<Theatre>();
		voucherList = new ArrayList<Voucher>();

		// LOAD data from online database
		
		// LOAD data from file
		try {
			loadUserData(userPath);
			loadMovieData(moviePath);
			loadTheatreData(theatrePath);
		    loadRegisterData(registerPath);
		    loadReservationData(reservationPath);
			loadVoucherData(voucherPath);
		} catch (Exception e) {
			System.err.println("Error during reading file");
		}		
		
		
		// Add the movie, theatre and sample data to the database
		if (movieList.size() == 0 && theatreList.size() == 0 && registerList.size() == 0) {
			samplePopulateDatabase();
		}
		
//		signUp("user", "password", new Name("John", "J", "Smith"), new Payment(null, null, null, null, 0, 0, 0, 0));
//		
//		signUp("1", "1", new Name("John", "J", "Smith"), new Payment(null, null, null, null, 0, 0, 0, 0));
		
//		this.addMovie("Epic Battle (2020)", 2013, 12, 22, "11 out of 10","The most epic of battles, GRAND!\nSo epic - Danidite 2013");
//		this.addMovie("Epic Battle (2021)", 2014, 5, 4, "0.2 out of 10", "Remake of the best movie ever!\nSo epic - Danidite 2014");
//		
//		this.addThreater("Daniel's Landing", "Daniel's mind SW");
//		this.addThreater("Nicks nightmare", "13214 Puniate way SE");
		
//		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 45, 10, 12, 2021, 4, 3, 6, 2020, 12, 2);
//		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 32, 3, 12, 2021, 4, 3, 6, 2021, 4, 2);
//		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 32, 3, 12, 2021, 4, 3, 6, 2020, 4, 2);
//		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(1).getTheatreID(), 32, 8, 12, 2021, 4, 3, 6, 2021, 4, 2);
	}
	
	// Add some sample data to it (like movie, theatre and register)
	private void samplePopulateDatabase() {
		// Populate movie ----------------------------------
		this.addMovie("The Godfather", 1972, 3, 14, "9.2/10 - IMDb", "This is the epic tale of a 1940s New York Mafia family\nand their struggle to protect their empire from rival \nfamilies as the leadership switches from the father to his youngest son.", "godfather");
		this.addMovie("The Matrix", 1999, 3, 31, "8.7/10 - IMDb", "Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), \nan elusive figure considered to be the most dangerous man alive, \ncan answer his question -- What is the Matrix? Neo is contacted by Trinity \n(Carrie-Anne Moss), a beautiful stranger who leads him into an underworld where he meets Morpheus. \nThey fight a brutal battle for their lives against a cadre of viciously intelligent secret agents. \nIt is a truth that could cost Neo something more precious than his life.", "matrix");
		this.addMovie("Epic Code Battle", 2020, 12, 1, "11 out of 10 - From 4 User Review", "This is the story of an epic ENSF 480 group\ndesperately trying to finish a assignment. Featuring the most epic coding moments, \ncomputer issues and unexpected events, the group must find a way to finish a huge assignment. \nCan they do it? Or will they fall? Watch to find out!", "epic");
		
		// Populate Theater ----------------------------------
		this.addThreater("Landmark Cinemas Country Hills", "300-388 Country Hills Blvd NE, Calgary, AB T3K 5J6", "LM");
		this.addThreater("Cineplex Odeon Sunridge Spectrum Cinemas", " 2555 32 St NE #400, Calgary, AB T1Y 7J6", "CC");
		this.addThreater("Daniel's Landing", "Daniel's Street Mindom Blvd SW, Topia, DS I8X 4T0", "DL");
		this.addThreater("Nicks nightmare", "Nightmare Fort NW, Topia, Ds Y2C 2I9", "NN");
		
		// Add register (show times) ----------------------------------
		
		// Godfather
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 11.5, 5, 5, 2020, 12, 3, 14, 2020, 11, 30, "GLC");
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 12.5, 4, 5, 2020, 12, 10, 14, 2020, 11, 30, "GLA");
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(0).getTheatreID(), 16.2, 4, 5, 2021, 3, 4, 16, 2021, 1, 12, "GLB");
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(1).getTheatreID(), 13.2, 3, 6, 2020, 12, 28, 7, 2020, 12, 15, "GCA");
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(2).getTheatreID(), 11.8, 2, 4, 2020, 12, 30, 14, 2020, 11, 4, "GDA");
		this.addRegister(movieList.get(0).getMovieID(), theatreList.get(3).getTheatreID(), 14.2, 4, 5, 2021, 2, 3, 8, 2021, 1, 4, "GNA");
		
		// Matrix
		this.addRegister(movieList.get(1).getMovieID(), theatreList.get(0).getTheatreID(), 15.5, 4, 5, 2020, 12, 10, 14, 2020, 11, 30, "MLA");
		this.addRegister(movieList.get(1).getMovieID(), theatreList.get(0).getTheatreID(), 10.2, 4, 5, 2021, 3, 4, 16, 2021, 1, 12, "MLB");
		this.addRegister(movieList.get(1).getMovieID(), theatreList.get(1).getTheatreID(), 12.6, 3, 6, 2020, 12, 28, 7, 2020, 12, 15, "MCA");
		this.addRegister(movieList.get(1).getMovieID(), theatreList.get(2).getTheatreID(), 13.5, 2, 4, 2020, 12, 30, 14, 2020, 11, 4, "MDA");
		this.addRegister(movieList.get(1).getMovieID(), theatreList.get(3).getTheatreID(), 11.5, 4, 5, 2021, 2, 3, 8, 2021, 1, 4, "MNA");
		
		// Epic Code battle
		this.addRegister(movieList.get(2).getMovieID(), theatreList.get(2).getTheatreID(), 30.8, 2, 4, 2021, 12, 30, 14, 2020, 11, 4, "EDA");
		this.addRegister(movieList.get(2).getMovieID(), theatreList.get(2).getTheatreID(), 42.8, 2, 4, 2021, 12, 30, 14, 2021, 10, 3, "EDB");
		this.addRegister(movieList.get(2).getMovieID(), theatreList.get(3).getTheatreID(), 32.2, 4, 5, 2021, 2, 3, 8, 2021, 1, 4, "ENA");
	}

	public void loadUserData(String userPath) {
		try {
			File userFile= new File(userPath);
			InputStreamReader userReader=new InputStreamReader(new FileInputStream(userFile));
			BufferedReader userBr=new BufferedReader(userReader);
			String userLine="";
			userLine=userBr.readLine();
			while(userLine!=null) {
				String[]s=userLine.split(" ~!@!~ ");
				
				User user = null;
				if (Boolean.getBoolean(s[2]) == true) {
					//Is admin
					user = new Admin(s[0],s[1],s[20]);
				} else {
					// Is normal user
					String middleName=s[4];
					if(middleName.equals(s[4])) {
						middleName=null;
					}
					
					user = new RegisteredUser(s[0],s[1], s[3], middleName, s[5], s[6], s[7], s[8], s[9],Integer.parseInt(s[10]),Integer.parseInt(s[11]), Integer.parseInt(s[12]),Integer.parseInt(s[13]),Integer.parseInt(s[14]),Integer.parseInt(s[15]),Integer.parseInt(s[16]),Integer.parseInt(s[17]),Integer.parseInt(s[18]),Integer.parseInt(s[19]), s[20]);
//					String username, String password, String firstName, String middleName, String lastName, String paymentFirstName, String PaymentMiddleName, String PaymentLastName, String billingAddress, int cardNumber, int expYear, int expMonth, int pin, int joinYear, int joinMonth, int joinDay, int paymentYear, int paymentMonth, int paymentDay, String userID
				}
				userList.add(user);
				userLine=userBr.readLine();
			}
			userBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading user file");
		}
	}
	
	public void loadMovieData(String moviePath) {
		try {
			//load movie data
			File movieFile= new File(moviePath);
			InputStreamReader movieReader=new InputStreamReader(new FileInputStream(movieFile));
			BufferedReader movieBr=new BufferedReader(movieReader);
			String movieLine="";
			movieLine=movieBr.readLine();
			while(movieLine!=null) {
				String[]s=movieLine.split(" ~!@!~ ");
				String[]descopy=s[5].split(" ~!~ ");
				String des="";
				for(int i=0;i<descopy.length;i++) {
					des=des+descopy[i]+"\n";
				}
				
				Movie movie = new Movie(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), s[4],des, s[6]);
				movieList.add(movie);
				movieLine=movieBr.readLine();
			}
			movieBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading movie file");
		}
	}
	
	public void loadTheatreData(String theatrePath) {
		try {
			//load Theatre data
			File theatreFile= new File(theatrePath);
			InputStreamReader theatreReader=new InputStreamReader(new FileInputStream(theatreFile));
			BufferedReader theatreBr=new BufferedReader(theatreReader);
			String theatreLine="";
			theatreLine=theatreBr.readLine();
			while(theatreLine!=null) {
				String[]s=theatreLine.split(" ~!@!~ ");
				Theatre theatre = new Theatre(s[0], s[1], s[2]);
				theatreList.add(theatre);
				theatreLine=theatreBr.readLine();
			}
			theatreBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading theatre file");
		}
	}
	
	public void loadRegisterData(String registerPath) {
		try {
			//load register data
			File registerFile= new File(registerPath);
			InputStreamReader registerReader=new InputStreamReader(new FileInputStream(registerFile));
			BufferedReader registerBr=new BufferedReader(registerReader);
			String registerLine="";
			registerLine=registerBr.readLine();
			while(registerLine!=null) {
				String[]s=registerLine.split(" ~!@!~ ");
//				Movie movie=getMovieByID(s[0]);
//				
//				Register register = new Register(movie, getTheatreByID(s[1]), Double.parseDouble(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), 
//						Boolean.getBoolean(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]), Integer.parseInt(s[9]), 
//						Integer.parseInt(s[10]), Integer.parseInt(s[11]), Integer.parseInt(s[12]), s[13]);
//				movie.addRegister(register);
//				registerList.add(register);
				
				boolean success = addRegister(s[0], s[1], Double.parseDouble(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), 
						 Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]), Integer.parseInt(s[9]), 
						Integer.parseInt(s[10]), Integer.parseInt(s[11]), Integer.parseInt(s[12]), s[13]);
				
				if (success == false) {
					System.err.println("Error adding register! From load Register Data");
				}
				
//				boolean earlyBooking = false;
//
//				// If announces in the future
//				if (isEarly(LocalDate.of(Integer.parseInt(s[10]), Integer.parseInt(s[11]), Integer.parseInt(s[12])))) {
//					earlyBooking = true;
//				}
//				
				//add news of this register
//				if(earlyBooking) {
//					File newsFile= new File(newsPath);
//					InputStreamReader newsReader=new InputStreamReader(new FileInputStream(newsFile));
//					BufferedReader newsBr=new BufferedReader(newsReader);
//					String newsLine="";
//					newsLine=newsBr.readLine();
//					while(newsLine!=null) {
//						String[]n=newsLine.split(" ~!@!~ ");
//						News news = new News(register, n[0]);
//						newsList.add(news);
//						newsLine=newsBr.readLine();
//						
//					}
//					newsBr.close();
//				}
				registerLine=registerBr.readLine();
			}
			registerBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading register file");
		}
	}
	
	/**
	 * Helper function for adding register with register ID
	 * @param movieID
	 * @param theatreID
	 * @param price
	 * @param seatRow
	 * @param seatColumn
	 * @param showYear
	 * @param showMonth
	 * @param showDay
	 * @param showHour
	 * @param announcementYear
	 * @param announcementMonth
	 * @param announcementDay
	 * @param registerID
	 * @return
	 */
	private boolean addRegister(String movieID, String theatreID, double price, int seatRow, int seatColumn,
			int showYear, int showMonth, int showDay, int showHour, int announcementYear, int announcementMonth,
			int announcementDay, String registerID) {
		Movie movie = getMovieByID(movieID);
		Theatre theatre = getTheatreByID(theatreID);

		if (movie == null || theatre == null) {
			return false;
		}

		boolean earlyBooking = false;
		
		if (isEarly(LocalDate.of(announcementYear, announcementMonth, announcementDay))) {
			earlyBooking = true;
		}

		Register newRegister = new Register(movie, theatre, price, seatRow, seatColumn, earlyBooking, showYear,
				showMonth, showDay, showHour, announcementYear, announcementMonth, announcementDay, registerID);
		
		
		// If announces in the future
		if (isEarly(LocalDate.of(announcementYear, announcementMonth, announcementDay))) {
			addNews(newRegister, movie.getMovieDescription());
		}		
		
		movie.addRegister(newRegister);
		
		registerList.add(newRegister);

		// Add a new news (if upcoming)

		return true;
	}
	
	
	
	public void loadReservationData(String reservationPath) {
		try {
			//load reservation data
			File reservationFile= new File(reservationPath);
			InputStreamReader reservationReader=new InputStreamReader(new FileInputStream(reservationFile));
			BufferedReader reservationBr=new BufferedReader(reservationReader);
			String reservationLine="";
			reservationLine=reservationBr.readLine();
			while(reservationLine!=null) {
				String[]s=reservationLine.split(" ~!@!~ ");
				String middleName=s[5];
				if(middleName.equals(s[5])) {
					middleName=null;
				}
				String userID = s[12];
				if (userID.equals("null")) {
					userID = null;
				}
				Reservation reservation = new Reservation( Integer.parseInt(s[0]),  Integer.parseInt(s[1]), getRegisterByID(s[2]), Double.parseDouble(s[3]), 
						s[4], middleName, s[6], s[7], Integer.parseInt(s[8]), Integer.parseInt(s[9]), Integer.parseInt(s[10]), Integer.parseInt(s[11]), userID, s[13]);
				RegisteredUser ru= (RegisteredUser) this.getUserByID(s[12]);
				if(ru!=null) {
					ru.addReservation(reservation);
				}
				Register register=this.getRegisterByID(s[2]);
				if(register!=null) {
					register.occupySeat(Integer.parseInt(s[0]),Integer.parseInt(s[1]),isEarly(register.getAnnouncementDate()));
				}else {
					System.err.println("Error happen in loading reservation data due register");
				}
				
				
				reservationList.add(reservation);
				reservationLine=reservationBr.readLine();
				
			}
			reservationBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading reservation file");
		}
	}
	
	public void loadVoucherData(String voucherPath) {
		try {
			//load voucher data
			File voucherFile= new File(voucherPath);
			InputStreamReader voucherReader=new InputStreamReader(new FileInputStream(voucherFile));
			BufferedReader voucherBr=new BufferedReader(voucherReader);
			String voucherLine="";
			voucherLine=voucherBr.readLine();
			while(voucherLine!=null) {
				String[]s=voucherLine.split(" ~!@!~ ");
				String userId=s[2];
				if(s[2].equals("null")) {
					userId=null;
				} 
				Voucher voucher = new Voucher(Double.parseDouble(s[0]),userId,s[1], Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5]));
				
				if (voucher.canBeUsed() == false) {
					continue;
				}
				
				if (userId != null) {
					RegisteredUser user = (RegisteredUser) this.getUserByID(userId);
					if (user != null) {
						user.addVoucher(voucher);
					}
				}
				
				voucherList.add(voucher);
				voucherLine=voucherBr.readLine();
			}
			voucherBr.close();
		}catch (Exception e) {
			System.err.println("Error during reading voucher file");
		}
		
	}
	
	

	/**
	 * Save data to database
	 */
	
	public void saveToDatabase() throws IOException{
		// SAVE data to online database
		//saving user data
	    {
		
		FileWriter userWriter = new FileWriter(userPath);
		String uData="";
		String line="";
		for(int i=0;i<userList.size();i++){
			User current=userList.get(i);
			if(current.isAdmin()==true) {
				Admin cur=(Admin)current;
				line=cur.getUsername()+" ~!@!~ "+cur.getPassword()+" ~!@!~ "+cur.isAdmin()+" ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ null ~!@!~ "+cur.getUserID()+"\n";
			}else {
				RegisteredUser cur=(RegisteredUser)current;
				line=cur.getUsername()+" ~!@!~ "+cur.getPassword()+" ~!@!~ "+cur.isAdmin()+" ~!@!~ "+cur.getName().getFirstName()+" ~!@!~ "+cur.getName().getMiddleName()+" ~!@!~ "+cur.getName().getLastName()+" ~!@!~ "+cur.getPaymentInfo().getCardHolderName().getFirstName()+" ~!@!~ "+cur.getPaymentInfo().getCardHolderName().getMiddleName()+" ~!@!~ "+cur.getPaymentInfo().getCardHolderName().getLastName()+" ~!@!~ "+cur.getPaymentInfo().getBillingAddress()+" ~!@!~ "+cur.getPaymentInfo().getCardNumber()+" ~!@!~ "+cur.getPaymentInfo().getExpirationYear()+" ~!@!~ "+cur.getPaymentInfo().getExpirationMonth()+" ~!@!~ "+cur.getPaymentInfo().getPin()+" ~!@!~ "+cur.getJoinYear()+" ~!@!~ "+cur.getJoinMonth()+" ~!@!~ "+cur.getJoinDay()+" ~!@!~ "+cur.getNextPaymentYear()+" ~!@!~ "+cur.getNextPaymentMonth()+" ~!@!~ "+cur.getNextPaymentDay()+" ~!@!~ "+cur.getUserID()+"\n";
			}
			uData+=line;
		}
		userWriter.write(uData);
		userWriter.close();
		}
		
	
		{
		//saving movie data
		FileWriter movieWriter=new FileWriter(moviePath);
		String mData="";
		for(int i=0;i<movieList.size();i++){
			Movie current=movieList.get(i);
			String[] description=current.getMovieDescription().split("\n");
			String s="";
			for(int m=0; m<description.length;m++) {
				s+=description[m]+" ~!~ ";
			}
			String line=current.getMovieName()+" ~!@!~ "+current.getReleaseYear()+" ~!@!~ "+current.getReleaseMonth()+" ~!@!~ "+current.getReleaseDay()+" ~!@!~ "+current.getRating()+" ~!@!~ "+s+" ~!@!~ "+current.getMovieID()+"\n";
			mData+=line;
		}
		movieWriter.write(mData);
		movieWriter.close();
		}
			
	    {
		//saving Theatre data
		FileWriter theatreWriter=new FileWriter(theatrePath);
		String tData="";
		for(int i=0;i<theatreList.size();i++){
			Theatre current=theatreList.get(i);
			String line=current.getTheatreName()+" ~!@!~ "+current.getTheatreAddress()+" ~!@!~ "+current.getTheatreID()+"\n";
			tData+=line;
		}
		theatreWriter.write(tData);
		theatreWriter.close();
		}
	
		{
		//saving register data
		FileWriter registerWriter= new FileWriter(registerPath);
		String regData="";
		for(int i=0;i<registerList.size();i++){
			Register current=registerList.get(i);
			String line=current.getMovie().getMovieID()+" ~!@!~ "+current.getTheatre().getTheatreID()+" ~!@!~ "+current.getPrice()+" ~!@!~ "+current.getSeatRow()+" ~!@!~ "+current.getSeatColumn()+" ~!@!~ "+current.isEarly()+" ~!@!~ "+current.getShowYear()+" ~!@!~ "+current.getShowMonth()+" ~!@!~ "+current.getShowDay()+" ~!@!~ "+current.getShowHourInInteger()+" ~!@!~ "+current.getAnnouncementYear()+" ~!@!~ "+current.getAnnouncementMonth()+" ~!@!~ "+current.getAnnouncementDay()+" ~!@!~ "+current.getRegisterID()+"\n";
			regData+=line;
		}
		registerWriter.write(regData);
		registerWriter.close();
		}
	
		{
		//saving reservation data
		FileWriter reservationWriter= new FileWriter(reservationPath);
		String resData="";
		for(int i=0;i<reservationList.size();i++){
			Reservation current=reservationList.get(i);
			String middle=current.getReceipt().getPaymentInfo().getCardHolderName().getMiddleName();
			if(middle==null) {
				middle="null";
			}
			String line=current.getTicket().getSeatRow()+" ~!@!~ "+current.getTicket().getSeatColumn()+" ~!@!~ "+current.getTicket().getRegister().getRegisterID()+" ~!@!~ "+current.getReceipt().getCost()+" ~!@!~ "+current.getReceipt().getPaymentInfo().getCardHolderName().getFirstName()+" ~!@!~ "+middle+" ~!@!~ "+current.getReceipt().getPaymentInfo().getCardHolderName().getLastName()+" ~!@!~ "+current.getReceipt().getPaymentInfo().getBillingAddress()+" ~!@!~ "+current.getReceipt().getPaymentInfo().getCardNumber()+" ~!@!~ "+current.getTicket().getPaymentYear()+" ~!@!~ "+current.getTicket().getPaymentMonth()+" ~!@!~ "+current.getTicket().getPaymentDay()+" ~!@!~ "+current.getUserID()+" ~!@!~ "+current.getReservationID()+"\n";
			resData+=line;
		}
		reservationWriter.write(resData);
		reservationWriter.close();
		}
		
		{
			//Save voucher data
            FileWriter voucherWriter= new FileWriter(voucherPath);
            String voucherData="";
            for(int i=0;i<voucherList.size();i++){
                Voucher current=voucherList.get(i);
                String userID = current.getUserID();
                if(userID==null) {
                    userID="null";
                }
                String line=current.getCredit()+" ~!@!~ "+current.getVoucherID()+" ~!@!~ "+userID +" ~!@!~ " + current.getExpirationDate().getYear() +" ~!@!~ " + current.getExpirationDate().getMonthValue() + " ~!@!~ " + current.getExpirationDate().getDayOfMonth() + "\n";
                
                voucherData+=line;
            }
            voucherWriter.write(voucherData);
            voucherWriter.close();    
        }
	}

	/**
	 * Return the registers of an movie, return null if no movie can be found
	 * 
	 * @param movieID
	 * @return
	 */
	public ArrayList<Register> getRegisterListByMovieID(String movieID) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovieID().equals(movieID)) {
				return movieList.get(i).getRegisterList();
			}
		}
		return null;
	}
	
	/**
	 * get register list by theater Id and movie ID
	 * @param theatreID
	 * @param movieID
	 * @return
	 */
	public ArrayList<Register> getRegisterListOfMovie(String theatreID, String movieID) {
		ArrayList<Register> newRegisterList = new ArrayList<Register>();
		
		for (int i = 0; i < registerList.size(); i++) {
			if (registerList.get(i).getTheatre().getTheatreID().equals(theatreID) && registerList.get(i).getMovie().getMovieID().equals(movieID)) {
				newRegisterList.add(registerList.get(i));
			}
		}
		return newRegisterList;
	}

	/**
	 * Get register by ID, return null if cannot be found
	 * 
	 * @param registerID
	 * @return
	 */
	public Register getRegisterByID(String registerID) {
		for (int i = 0; i < registerList.size(); i++) {
			if (registerList.get(i).getRegisterID().equals(registerID)) {
				return registerList.get(i);
			}
		}
		return null;
	}

	/**
	 * Create a register object
	 * 
	 * @param movieID
	 * @param theatreID
	 * @param seatRow
	 * @param seatColumn
	 * @param showYear
	 * @param showMonth
	 * @param showDay
	 * @param showHour
	 * @param announcementYear
	 * @param announcementMonth
	 * @param announcementDay
	 * @return
	 */
	public boolean addRegister(String movieID, String theatreID, double price, int seatRow, int seatColumn,
			int showYear, int showMonth, int showDay, int showHour, int announcementYear, int announcementMonth,
			int announcementDay) {
		Movie movie = getMovieByID(movieID);
		Theatre theatre = getTheatreByID(theatreID);

		if (movie == null || theatre == null) {
			return false;
		}

		boolean earlyBooking = false;
		
		if (isEarly(LocalDate.of(announcementYear, announcementMonth, announcementDay))) {
			earlyBooking = true;
		}

		Register newRegister = new Register(movie, theatre, price, seatRow, seatColumn, earlyBooking, showYear,
				showMonth, showDay, showHour, announcementYear, announcementMonth, announcementDay);
		
		
		// If announces in the future
		if (isEarly(LocalDate.of(announcementYear, announcementMonth, announcementDay))) {
			addNews(newRegister, movie.getMovieDescription());
		}		
		
		movie.addRegister(newRegister);
		
		registerList.add(newRegister);

		// Add a new news (if upcoming)

		return true;
	}

	/**
	 * See if early, the days between today and new days are greater than 0
	 * 
	 * @param newDate
	 * @return
	 */
	public boolean isEarly(LocalDate newDate) {
		LocalDate today = LocalDate.now();

		// If announces in the future
		if (ChronoUnit.DAYS.between(today, newDate) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create a theatre object, return false if already exist
	 * 
	 * @param theatreName
	 * @param theatreAddress
	 * @param string 
	 * @return
	 */
	public boolean addThreater(String theatreName, String theatreAddress) {
		if (getTheatreByNameAndAddress(theatreName, theatreAddress) == null) {
			theatreList.add(new Theatre(theatreName, theatreAddress));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add theatre with specified ID
	 * @param theatreName
	 * @param theatreAddress
	 * @param string
	 * @return
	 */
	public boolean addThreater(String theatreName, String theatreAddress, String theatreID) {
		if (getTheatreByNameAndAddress(theatreName, theatreAddress) == null) {
			theatreList.add(new Theatre(theatreName, theatreAddress, theatreID));
			return true;
		} else {
			return false;
		}
	}

	public Theatre getTheatreByNameAndAddress(String theatreName, String theatreAddress) {
		for (int i = 0; i < theatreList.size(); i++) {
			if (theatreList.get(i).getTheatreName().equals(theatreName)) {
				if (theatreList.get(i).getTheatreAddress().equals(theatreAddress)) {
					return theatreList.get(i);
				}
			}
		}
		return null;
	}

	public Theatre getTheatreByID(String theatreID) {
		for (int i = 0; i < theatreList.size(); i++) {
			if (theatreList.get(i).getTheatreID().equals(theatreID)) {
				return theatreList.get(i);
			}
		}
		return null;
	}

	/**
	 * Create a movie object and add movie to the list
	 * @param movieName
	 * @param year
	 * @param month
	 * @param day
	 * @param rating
	 * @param movieDescription
	 * @return
	 */
	public boolean addMovie(String movieName, int year, int month, int day, String rating, String movieDescription) {
		if (getMovieByName(movieName) == null) {
			movieList.add(new Movie(movieName, year, month, day, rating, movieDescription));
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Add movie with movie ID
	 * @param movieName
	 * @param year
	 * @param month
	 * @param day
	 * @param rating
	 * @param movieDescription
	 * @param movieID
	 * @return
	 */
	public boolean addMovie(String movieName, int year, int month, int day, String rating, String movieDescription, String movieID) {
		if (getMovieByName(movieName) == null) {
			movieList.add(new Movie(movieName, year, month, day, rating, movieDescription, movieID));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get movie by movie name
	 * 
	 * @param movieName
	 * @return
	 */
	public Movie getMovieByName(String movieName) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovieName().equals(movieName)) {
				return movieList.get(i);
			}
		}
		return null;
	}

	/**
	 * Return a movie with the argument ID, return null if cannot be found
	 * 
	 * @param movieID
	 * @return
	 */
	public Movie getMovieByID(String movieID) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getMovieID().equals(movieID)) {
				return movieList.get(i);
			}
		}
		return null;
	}

	/**
	 * Remove register by ID, return true if successful, return false if it cannot
	 * be found
	 * 
	 * @param registerID
	 * @return
	 */
	public boolean removeRegisterByID(String registerID) {
		for (int i = 0; i < registerList.size(); i++) {
			if (registerList.get(i).getRegisterID().equals(registerID)) {
				registerList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * return if the current user can use this voucher (if no user then return true)
	 * @param voucherID
	 * @return
	 */
	public boolean canVoucherBeUsed(String voucherID, User user) {
		Voucher voucher = getVoucherByID(voucherID);
		
		if (voucher == null) {
			return false;
		}
		
		if (voucher.getUserID() == null) {
			return true;
		}
		
		RegisteredUser ru = (RegisteredUser) getUserByID(voucher.getUserID());
		
		if (ru == null) {
			return true;
		}
		
		if (user == null) {
			return false;
		}
		
		if (ru.getUserID().equals(user.getUserID())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Add a new reservation and also return the reservationID (Also occupy the
	 * seat)
	 * 
	 * @param registerID
	 * @param seatRow
	 * @param seatColumn
	 * @param userID
	 * @param voucherID
	 * @param paymentInfo
	 * @return
	 */
	public String addReservation(String registerID, int seatRow, int seatColumn, String userID, String voucherID,
			Payment paymentInfo) {
		RegisteredUser user = null;
		if (userID != null) {
			user = (RegisteredUser) getUserByID(userID);
		}
		
		// Check if we even have any payment info
		if (paymentInfo == null) {
			if (user == null) {
				return null;
			}
			paymentInfo = user.getPaymentInfo();
		}

		// Get register
		Register currentRegister = getRegisterByID(registerID);

		if (currentRegister == null) {
			return null;
		}

		// See if it is early reservation
		boolean isEarly = isEarly(currentRegister.getAnnouncementDate());

		// Check if the current seat can be occupied
		if (currentRegister.canOccupySeat(seatRow, seatColumn, isEarly) == false) {
			return null;
		}

		// Only registered user are allowed to do early reservation, if not registered,
		// fail to early register
		if (isEarly == true && user == null) {
			return null;
		}

//		String userID = null;
//		if (user != null) {
//			userID = user.getUserID();
//		}

		// Get the price of register
		double price = currentRegister.getPrice();

		Voucher currentVoucher = getVoucherByID(voucherID);

		// Use voucher credits
		if (currentVoucher != null) {
			if (currentVoucher.getUserID() == null) {
				double voucherCredit = currentVoucher.getCredit();

				double remainingCredit = 0;

				if (voucherCredit > price) {
					remainingCredit = voucherCredit - price;
				}

				price -= voucherCredit;

				if (price < 0) {
					price = 0;
				}

				// Check if voucher could be removed
				if (remainingCredit == 0) {
					removeVoucherByID(voucherID);
				} else {
					currentVoucher.setCredit(remainingCredit);
				}
			} else {
				if (userID != null) {
					if (currentVoucher.getUserID().equals(userID)) {
						double voucherCredit = currentVoucher.getCredit();

						double remainingCredit = 0;

						if (voucherCredit > price) {
							remainingCredit = voucherCredit - price;
						}

						price -= voucherCredit;

						if (price < 0) {
							price = 0;
						}

						// Check if voucher could be removed
						if (remainingCredit == 0) {
							removeVoucherByID(voucherID);
							user.removeVoucher(voucherID);
						} else {
							currentVoucher.setCredit(remainingCredit);
						}
					}
				}
			}
		}

		// Create reservation
		Reservation reservation = new Reservation(seatRow, seatColumn, currentRegister, price, userID, paymentInfo);

		// Occupy Seat
		if (currentRegister.occupySeat(seatRow, seatColumn, isEarly) == false) {
			System.err.println("Error occupying seat!, fix it!");
			System.exit(1);
		}
		
		// Add to user
		if (user != null) {
			user.addReservation(reservation);
		}
		
		reservationList.add(reservation);

		return reservation.getReservationID();
	}

	/**
	 * Get reservation by ID, return null otherwise
	 * 
	 * @param reservationID
	 * @return
	 */
	public Reservation getReservationByID(String reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID().equals(reservationID)) {
				return reservationList.get(i);
			}
		}

		return null;
	}

	/**
	 * Remove a reservation by ID
	 * 
	 * @param reservationID
	 * @return
	 */
	public boolean removeReservationByID(String reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID().equals(reservationID)) {
				reservationList.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * Verify and return account
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User verifyAccount(String username, String password) {
		User current = getUserWithUsername(username);
		if (current != null) {
			if (current.getPassword().equals(password)) {
				return current;
			}
		}

		return null;
	}

	/**
	 * Get user with username, return null otherwise
	 * 
	 * @param username
	 * @return
	 */
	public User getUserWithUsername(String username) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equals(username)) {
				return userList.get(i);
			}
		}
		return null;
	}

	/**
	 * Get user with userID, return null otherwise
	 * 
	 * @param userID
	 * @return
	 */
	public User getUserByID(String userID) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserID().equals(userID)) {
				return userList.get(i);
			}
		}
		return null;
	}

	/**
	 * Sign up for new account, return null if fail
	 * 
	 * @param userName
	 * @param password
	 * @param name
	 * @param paymentInfo
	 * @return
	 */
	public User signUp(String userName, String password, Name name, Payment paymentInfo) {
		if (getUserWithUsername(userName) == null) {
			User newUser = new RegisteredUser(userName, password, name, paymentInfo);
			userList.add(newUser);
			return newUser;
		}
		return null;
	}

	/**
	 * Create voucher and return that voucher
	 * 
	 * @param amount
	 * @param currentUser
	 * @return
	 */
	public Voucher addVoucher(double amount, RegisteredUser currentUser) {
		Voucher newVoucher;
		if (currentUser != null) {
			newVoucher = new Voucher(amount, currentUser.getUserID());
			currentUser.addVoucher(newVoucher);
		} else {
			newVoucher = new Voucher(amount, null);
		}
		voucherList.add(newVoucher);
		return newVoucher;
	}

	/**
	 * Return voucher with ID
	 * 
	 * @param voucherID
	 * @return
	 */
	public Voucher getVoucherByID(String voucherID) {
		
		if(voucherID==null) {
			return null;
		}
		
		for (int i = 0; i < voucherList.size(); i++) {
			if (voucherList.get(i).getVoucherID().equals(voucherID)){
				return voucherList.get(i);
			}
		}
		return null;
	}

	/**
	 * Remove voucher, return true if successful, false of voucher cannot be found
	 * 
	 * @param voucherID
	 * @return
	 */
	public boolean removeVoucherByID(String voucherID) {
		for (int i = 0; i < voucherList.size(); i++) {
			if (voucherList.get(i).getVoucherID().equals(voucherID)) {
				voucherList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Get seat list by registerID, return null if register cannot be found
	 * 
	 * @param registerID
	 * @return
	 */
	public char[][] getSeatListByRegisterID(String registerID) {
		Register register = getRegisterByID(registerID);
		if (register == null) {
			return null;
		} else {
			return register.getSeatListInChar();
		}
	}

	/**
	 * Get news by its Register ID
	 * 
	 * @param registerID
	 * @return
	 */
	public News getNewsByRegisterID(String registerID) {
		for (int i = 0; i < registerList.size(); i++) {
			if (newsList.get(i).getRegisterID().equals(registerID)) {
				return newsList.get(i);
			}
		}
		return null;
	}

	/**
	 * Remove news by register ID
	 * 
	 * @param registerID
	 * @return
	 */
	public News removeNewsByRegisterID(String registerID) {
		for (int i = 0; i < registerList.size(); i++) {
			if (newsList.get(i).getRegisterID().equals(registerID)) {
				return newsList.remove(i);
			}
		}
		return null;
	}

	/**
	 * Add a news
	 * 
	 * @param register
	 * @param information
	 * @return
	 */
	public News addNews(Register register, String information) {
		News news = new News(register, information);
		this.newsList.add(news);
		return news;
	}

	/*
	 * Getters and savvers
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(ArrayList<News> newsList) {
		this.newsList = newsList;
	}

	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public ArrayList<Register> getRegisterList() {
		return registerList;
	}

	public void setRegisterList(ArrayList<Register> registerList) {
		this.registerList = registerList;
	}

	public ArrayList<Theatre> getTheatreList() {
		return theatreList;
	}

	public void setTheatreList(ArrayList<Theatre> theatreList) {
		this.theatreList = theatreList;
	}

	public ArrayList<Voucher> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(ArrayList<Voucher> voucherList) {
		this.voucherList = voucherList;
	}

	
}
