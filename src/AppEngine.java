import java.util.ArrayList;
import java.util.List;

public class AppEngine {
	
	private List<User> users;
	private List<Calendar> publicCalendars;
	private int nextUserId;
	private int nextCalendarId;
	private int nextEventId;
	
	public AppEngine() {
		this.users = new ArrayList<User>();
		this.publicCalendars = new ArrayList<Calendar>();
		this.nextUserId = 0;
		this.nextCalendarId = 0;
		this.nextEventId = 0;
	}
	
	private void AddUser() {
		User newUser = InputHandler.GetNewUser(nextUserId);
		nextUserId++;
		
		this.users.add(newUser);
	}
	
	private void AddCalendar() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		Calendar newCalendar = InputHandler.GetNewCalendar(this.nextCalendarId, owner);
		owner.addCalendar(newCalendar);
		if (newCalendar.isPublic) {
			publicCalendars.add(newCalendar);
		}
		this.nextCalendarId++;
	}
	
	private void AddEvent() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		ConsoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		Event newEvent = InputHandler.GetNewEvent(this.nextEventId);
		this.nextEventId++;
		
		calendar.addEvent(newEvent);
	}
	
	private void ViewUser() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user to view users");
			return;
		}
		
		System.out.println("What user would you like to view?");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();

		ConsoleDrawer.PrintUserInfo(this.users.get(userIndex));
	}
	
	private void ViewCalendar() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		ConsoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		ConsoleDrawer.PrintCalendar(calendar);
	}
	
	private void ViewEvent() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		ConsoleDrawer.PrintUserInfo(owner);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		System.out.println("Choose an event");
		ConsoleDrawer.PrintEvents(calendar.events);
		int eventIndex = InputHandler.GetIndex();
		
		ConsoleDrawer.PrintEvent(calendar.events.get(eventIndex));
	}
	
	private void ViewSharedCalendar() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		ConsoleDrawer.PrintUserSharedCalendars(owner);
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.sharedCalendars.get(calendarIndex);
		ConsoleDrawer.PrintCalendar(calendar);
	}
	
	private void ViewSharedEvent() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		ConsoleDrawer.PrintUserSharedCalendars(owner);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.sharedCalendars.get(calendarIndex);
		
		System.out.println("Choose an event");
		ConsoleDrawer.PrintEvents(calendar.events);
		int eventIndex = InputHandler.GetIndex();
		
		ConsoleDrawer.PrintEvent(calendar.events.get(eventIndex));
	}

	private void UpdateCalendar() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		ConsoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		System.out.println("Enter in new information: ");
		
		Calendar newCalendar = InputHandler.GetUpdatedCalendar(calendar.calendarId, owner);
		
		owner.updateCalendar(newCalendar);
	}

	
	private void UpdateEvent() {
		if (this.users.isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		ConsoleDrawer.PrintUserInfo(owner);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		System.out.println("Choose an event");
		ConsoleDrawer.PrintEvents(calendar.events);
		int eventIndex = InputHandler.GetIndex();
		Event oldEvent = calendar.events.get(eventIndex);
		
		System.out.println("Enter in new information: ");
		
		Event newEvent = InputHandler.GetNewEvent(oldEvent.eventId);
		
		calendar.updateEvent(newEvent);
	}
	
	private void ShareCalendar() {
		if (this.users.size() < 2) {
			System.out.println("There needs to be at least 2 users to share");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		ConsoleDrawer.PrintUserInfo(owner);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		System.out.println("Choose a User to share with");
		ConsoleDrawer.PrintUsers(this.users);
		userIndex = InputHandler.GetIndex();
		
		User receiver = this.users.get(userIndex);
		
		calendar.shareCalendar(receiver);
	}
	
	private void ShareEvent() {
		if (this.users.size() < 2) {
			System.out.println("There needs to be at least 2 users to share");
			return;
		}
		
		System.out.println("Choose a User");
		ConsoleDrawer.PrintUsers(this.users);
		int userIndex = InputHandler.GetIndex();
		
		User owner = this.users.get(userIndex);
		
		ConsoleDrawer.PrintUserInfo(owner);
		if (owner.calendars.isEmpty()) {
			System.out.println("There are no calendars for this user, please add a calendar first");
			return;
		}
		
		System.out.println("Choose a calendar index");
		int calendarIndex = InputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);

		System.out.println("Choose an event");
		ConsoleDrawer.PrintEvents(calendar.events);
		int eventIndex = InputHandler.GetIndex();
		
		Event event = calendar.events.get(eventIndex);
		
		System.out.println("Choose a User to share with");
		ConsoleDrawer.PrintUsers(this.users);
		userIndex = InputHandler.GetIndex();
		
		User receiver = this.users.get(userIndex);
		
		event.shareEvent(receiver);
	}

	public static void main(String[] args) {		
		AppEngine appEngine = new AppEngine();
				
		while (true) {
			System.out.println("Commands:\nView User\nView Calendar\nView Event\nView Shared Calendar\nView Shared Event\nAdd User\nAdd Calendar\nAdd Event\nUpdate Calendar\nUpdate Event\nShare Calendar\nShare Event");
			String selection = InputHandler.GetSelection();
			
			if (selection.equals("View User")) {
				appEngine.ViewUser();
			}
			else if (selection.equals("View Calendar")) {
				appEngine.ViewCalendar();
			}
			else if (selection.equals("View Event")) {
				appEngine.ViewEvent();
			}
			else if (selection.equals("View Shared Calendar")) {
				appEngine.ViewSharedCalendar();
			}
			else if (selection.equals("View Shared Event")) {
				appEngine.ViewSharedEvent();
			}
			else if (selection.equals("Add User")) {
				appEngine.AddUser();
			}
			else if (selection.equals("Add Calendar")) {
				appEngine.AddCalendar();
			}
			else if (selection.equals("Add Event")) {
				appEngine.AddEvent();
			}
			else if (selection.equals("Update Calendar")) {
				appEngine.UpdateCalendar();
			}
			else if (selection.equals("Update Event")) {
				appEngine.UpdateEvent();
			}
			else if (selection.equals("Share Calendar")) {
				appEngine.ShareCalendar();
			}
			else if (selection.equals("Share Event")) {
				appEngine.ShareEvent();
			}
			else {
				System.out.println("Invalid option, try again");
			}
		}
	}

}
