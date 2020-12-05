import Controller.TheatreProgram;
import Presentation.GuestMenuGUI;

public class TheatreApp {

//	
//	TheatreApp() {
////		database = Database.getInstance();
////		prog = new TheatreProgram(database);
//		prog = TheatreProgram.getInstance();
//	}
//	
	public void startApp()
	{
		//Preload the file
		TheatreProgram.getInstance();
		new GuestMenuGUI();
	}
	
	public static void main(String[] args) {
		
		TheatreApp app = new TheatreApp();
		
		app.startApp();
	}
}
