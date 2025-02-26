import java.util.List;

public interface ICalendar {
	public void addEvent(Event event);
	
	public void deleteEvent(Event event);
	
	public void updateEvent(Event event);
	
	public void updateAllEventTimezones();
	
	public void shareCalendar(User user);
	
	public List<Event> viewCalendarEvents();
}
