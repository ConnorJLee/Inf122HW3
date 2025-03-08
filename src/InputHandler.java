import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
	private  Scanner inputScanner;
	private static InputHandler inputHandler;
	
	private InputHandler() {
		this.inputScanner = new Scanner(System.in);
	}
	
	private String getStringInput() {
		return inputScanner.nextLine();
	}
	
	private Integer getNumInput() {
		Integer nextInt = inputScanner.nextInt();
		if(inputScanner.hasNextLine()) {
			inputScanner.nextLine();
		}
		return nextInt;
	}
	
	private Boolean getVisibilityInput() {
		String inputStr = "";
		while (inputStr.isEmpty() || (! inputStr.equals("private") && ! inputStr.equals("public"))) {
			System.out.println("Is this calendar \"private\" or \"public\"");
			inputStr = getStringInput();
		}
		
		return inputStr.equals("public");
	}
	
	public static InputHandler GetInputHandler() {
		if (InputHandler.inputHandler == null) {
			InputHandler.inputHandler = new InputHandler();
		}
		
		return InputHandler.inputHandler;
	}
	
	public  Integer GetIndex() {
		return getNumInput();
	}
	
	public  String GetName() {
		String inputStr = "";
		while (inputStr.isEmpty()) {
			System.out.println("Enter a name:");
			inputStr = getStringInput();
		}
		
		return inputStr;
	}
	
	public  Calendar GetNewCalendar(Integer id, User owner) {
		System.out.println("Enter a name for the calendar:");
		String name = getStringInput();
		
		Boolean isPublic = getVisibilityInput();
		
		return new Calendar(id, name, owner, isPublic, owner.settings.currentTimeZone);
	}

	public  Calendar GetUpdatedCalendar(Integer id, User owner) {
		System.out.println("Enter a name for the calendar:");
		String name = getStringInput();
		
		Boolean isPublic = getVisibilityInput();
		
		System.out.println("Enter calendar view (day, week, month, year):");
		String view = getStringInput();
		Calendar newCalendar = new Calendar(id, name, owner, isPublic, owner.settings.currentTimeZone);
		newCalendar.calendarView = view;
		
		return newCalendar;
	}
	
	public  User GetNewUser(Integer id) {
		System.out.println("Enter a username:");
		String name = getStringInput();
		
		return new User(id, name);
	}
	
	public  Event GetNewEvent(Integer id) {
		System.out.println("Enter a name for the event:");
		String name = getStringInput();
		
		LocalDate startDate = null;
		while (startDate == null) {
			System.out.println("Enter a start date in the format \"2024-01-20\"");
			String inputStr = getStringInput();
			
			try {
				startDate = LocalDate.parse(inputStr);
			}
			catch (DateTimeParseException e){
				System.out.println("Invalid date");
			}	
		}
		
		Integer startHour = -1;
		while (startHour < 0 || startHour > 23) {
			System.out.println("Enter the starting hour in 24 hour format");
			startHour = getNumInput();
		}

		Integer startMin = -1;
		while (startMin < 0 || startMin > 59) {
			System.out.println("Enter the starting minute (0-59)");
			startMin = getNumInput();
		}
		
		LocalDate endDate = null;
		while (endDate == null) {
			System.out.println("Enter an end date in the format \"2024-01-20\"");
			String inputStr = getStringInput();
			
			try {
				endDate = LocalDate.parse(inputStr);
			}
			catch (DateTimeParseException e){
				System.out.println("Invalid date");
			}
		}
		
		Integer endHour = -1;
		while (endHour < 0 || endHour > 23) {
			System.out.println("Enter the ending hour in 24 hour format");
			endHour = getNumInput();
		}

		Integer endMin = -1;
		while (endMin < 0 || endMin > 59) {
			System.out.println("Enter the ending minute (0-59)");
			endMin = getNumInput();
		}
		
		return new Event(id, name, startDate.atTime(startHour, startMin), endDate.atTime(endHour, endMin));
	}
	
	public  String GetSelection() {
		return getStringInput();
	}
}
