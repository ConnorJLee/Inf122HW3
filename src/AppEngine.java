public class AppEngine {
	private Integer nextUserId;
	private Integer nextCalendarId;
	private Integer nextEventId;
	private InputHandler inputHandler;
	private ConsoleDrawer consoleDrawer;
	private StorageFacade storage;
	
	public AppEngine() {
		this.nextUserId = 0;
		this.nextCalendarId = 0;
		this.nextEventId = 0;
		this.inputHandler = InputHandler.GetInputHandler();
		this.consoleDrawer = ConsoleDrawer.GetConsoleDrawer();
		this.storage = StorageFacade.GetStorageFacade();
	}
	
	private void AddUser() {
		User newUser = this.inputHandler.GetNewUser(nextUserId);
		nextUserId++;
		
		this.storage.AddUser(newUser);
	}
	
	private Boolean CheckUsersEmpty() {
		Boolean isEmpty = this.storage.GetUsers().isEmpty();
		if (isEmpty) {
			System.out.println("There are no users, please add a user first");
		}
		
		return isEmpty;
	}
	
	private Boolean Check2PlusUsers() {
		Boolean greaterThan2 = this.storage.GetUsers().size() > 1;
		if (!greaterThan2) {
			System.out.println("There needs to be at least 2 users to share");
		}
		
		return greaterThan2;
	}
	
	private Boolean NoCalendars(User user) {
		Boolean noCalendars = user.GetCalendars().isEmpty();
		
		if (noCalendars) {
			System.out.println("There are no calendars for this user, please add a calendar first");
		}
		
		return noCalendars;
	}
	
	private void AddCalendar() {
		if (this.storage.GetUsers().isEmpty()) {
			System.out.println("There are no users, please add a user first");
			return;
		}
		
		System.out.println("Choose a User");
		this.consoleDrawer.PrintUsers(this.storage.GetUsers());
		
		Integer userIndex = this.inputHandler.GetIndex();
		
		User owner = this.storage.GetUser(userIndex);
		
		Calendar newCalendar = this.inputHandler.GetNewCalendar(this.nextCalendarId, owner);
		this.storage.AddCalendar(newCalendar, owner);
		this.nextCalendarId++;
	}
	
	private void AddEvent() {
		User owner = this.GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return;
		}
		
		if (NoCalendars(owner)) {
			return;
		}
		
		this.consoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		Calendar calendar = owner.calendars.get(calendarIndex);
		
		Event newEvent = this.inputHandler.GetNewEvent(this.nextEventId);
		this.nextEventId++;
		
		this.storage.AddEvent(newEvent, calendar);
	}
	
	private User GetUser() {
		if (CheckUsersEmpty()) {
			return User.InvalidUser;
		}
		
		System.out.println("Choose a User");
		this.consoleDrawer.PrintUsers(this.storage.GetUsers());
		Integer userIndex = this.inputHandler.GetIndex();
		
		return this.storage.GetUser(userIndex);
	}
	
	private void ViewUser() {
		User user = this.GetUser();
		if(user.username.equals("Invalid\n\n")) {
			return;
		}
		this.consoleDrawer.PrintUserInfo(user);
	}
	
	private Calendar GetCalendar() {
		User owner = this.GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return Calendar.InvalidCalendar;
		}
		
		if (NoCalendars(owner)) {
			return Calendar.InvalidCalendar;
		}
		
		this.consoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		return this.storage.GetCalendar(owner, calendarIndex);
	}
	
	private void ViewCalendar() {
		Calendar calendar = this.GetCalendar();
		if (calendar.name.equals("Invalid\n\n")) {
			return;
		}
		this.consoleDrawer.PrintCalendar(calendar);
	}
	
	private void ViewEvent() {
		Calendar calendar = this.GetCalendar();
		if (calendar.name.equals("Invalid\n\n")) {
			return;
		}
		
		System.out.println("Choose an event");
		this.consoleDrawer.PrintEvents(calendar.viewCalendarEvents());
		Integer eventIndex = this.inputHandler.GetIndex();
		
		this.consoleDrawer.PrintEvent(this.storage.GetEvent(calendar, eventIndex));
	}
	
	private void ViewSharedCalendar() {
		User owner = this.GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return;
		}
		
		this.consoleDrawer.PrintUserSharedCalendars(owner);
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		Calendar calendar = this.storage.GetSharedCalendar(owner, calendarIndex);
		this.consoleDrawer.PrintCalendar(calendar);
	}
	
	private void ViewSharedEvent() {
		User owner = this.GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return;
		}
		
		this.consoleDrawer.PrintUserSharedCalendars(owner);
		
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		Calendar calendar = this.storage.GetSharedCalendar(owner, calendarIndex);
		
		System.out.println("Choose an event");
		this.consoleDrawer.PrintEvents(calendar.viewCalendarEvents());
		Integer eventIndex = this.inputHandler.GetIndex();
		
		this.consoleDrawer.PrintEvent(this.storage.GetSharedEvent(calendar, eventIndex));
	}

	private void UpdateCalendar() {
		User owner = this.GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return;
		}
		
		if (NoCalendars(owner)) {
			return;
		}
		
		this.consoleDrawer.PrintUserInfo(owner);
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		Calendar calendar = this.storage.GetCalendar(owner, calendarIndex);
		
		System.out.println("Enter in new information: ");
		
		Calendar newCalendar = this.inputHandler.GetUpdatedCalendar(calendar.calendarId, owner);
		
		this.storage.UpdateCalendar(owner, newCalendar);
	}

	
	private void UpdateEvent() {
		Calendar calendar = this.GetCalendar();
		if (calendar.name.equals("Invalid\n\n")) {
			return;
		}
		
		System.out.println("Choose an event");
		this.consoleDrawer.PrintEvents(calendar.viewCalendarEvents());
		Integer eventIndex = this.inputHandler.GetIndex();
		Event oldEvent = this.storage.GetEvent(calendar, eventIndex);
		
		System.out.println("Enter in new information: ");
		
		Event newEvent = this.inputHandler.GetNewEvent(oldEvent.eventId);
		
		this.storage.UpdateEvent(calendar, newEvent);;
	}
	
	private void ShareCalendar() {
		if (! Check2PlusUsers()) {
			return;
		}
		
		User owner = GetUser();
		if(owner.username.equals("Invalid\n\n")) {
			return;
		}
		
		this.consoleDrawer.PrintUserInfo(owner);
		if (NoCalendars(owner)) {
			return;
		}
		
		System.out.println("Choose a calendar index");
		Integer calendarIndex = this.inputHandler.GetIndex();
		Calendar calendar = this.storage.GetCalendar(owner, calendarIndex);
		

		User receiver = GetUser();
		if(receiver.username.equals("Invalid\n\n")) {
			return;
		}
		
		this.storage.ShareCalendar(calendar, receiver);;
	}
	
	private void ShareEvent() {
		if (! Check2PlusUsers()) {
			return;
		}

		Calendar calendar = GetCalendar();

		System.out.println("Choose an event");
		this.consoleDrawer.PrintEvents(calendar.viewCalendarEvents());
		Integer eventIndex = this.inputHandler.GetIndex();
		
		Event event = this.storage.GetEvent(calendar, eventIndex);

		User receiver = GetUser();
		if(receiver.username.equals("Invalid\n\n")) {
			return;
		}
		
		this.storage.ShareEvent(event, receiver);
	}

	public static void main(String[] args) {		
		AppEngine appEngine = new AppEngine();
				
		while (true) {
			System.out.println("Commands:\nView User\nView Calendar\nView Event\nView Shared Calendar\nView Shared Event\nAdd User\nAdd Calendar\nAdd Event\nUpdate Calendar\nUpdate Event\nShare Calendar\nShare Event");
			String selection = appEngine.inputHandler.GetSelection();
			
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
