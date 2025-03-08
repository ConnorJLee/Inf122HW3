import java.util.ArrayList;
import java.util.List;

public class StorageFacade {
	private List<User> users;
	private List<Calendar> publicCalendars;
	
	private static StorageFacade storage;
	
	private StorageFacade() {
		this.users = new ArrayList<User>();
		this.publicCalendars = new ArrayList<Calendar>();
	}
	
	public static StorageFacade GetStorageFacade() {
		if (StorageFacade.storage == null) {
			StorageFacade.storage = new StorageFacade();
		}
		
		return StorageFacade.storage;
	}
	
	public void AddUser(User newUser) {
		this.users.add(newUser);
	}
	
	public void AddCalendar(Calendar newCalendar, User owner) {
		owner.addCalendar(newCalendar);
		if (newCalendar.isPublic) {
			publicCalendars.add(newCalendar);
		}
	}
	
	public void AddEvent(Event newEvent, Calendar calendar) {
		calendar.addEvent(newEvent);
	}
	
	public User GetUser(Integer index) {
		return this.users.get(index);
	}
	
	public List<User> GetUsers(){
		return this.users;
	}
	
	public Calendar GetCalendar(User owner, Integer calendarIndex) {
		return owner.GetCalendar(calendarIndex);
	}
	
	public Event GetEvent(Calendar calendar, Integer eventIndex) {
		return calendar.GetEvent(eventIndex);
	}
	
	public Calendar GetSharedCalendar(User owner, Integer calendarIndex) {
		return owner.GetSharedCalendar(calendarIndex);
	}
	
	public Event GetSharedEvent(Calendar calendar, Integer eventIndex) {
		return calendar.GetEvent(eventIndex);
	}

	public void UpdateCalendar(User owner, Calendar newCalendar) {
		owner.updateCalendar(newCalendar);
	}

	
	public void UpdateEvent(Calendar calendar, Event newEvent) {
		calendar.updateEvent(newEvent);
	}
	
	public void ShareCalendar(Calendar calendar, User receiver) {
		calendar.shareCalendar(receiver);
	}
	
	public void ShareEvent(Event event, User receiver) {
		event.shareEvent(receiver);
	}
}
