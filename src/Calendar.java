import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Calendar implements ICalendar{
	public int calendarId;
	public String name;
	public LocalDateTime dateCreated;
	public LocalDateTime dateDeleted;
	public User owner;
	public boolean isPublic;
	public String calendarView;
	public TimeZone timeZone;
	public List<Event> events;
	public List<Event> deletedEvents;
	public List<User> usersSharedTo;
	
	public Calendar(int id, String name, User owner, boolean publicVisibility, TimeZone timeZone) {
		this.calendarId = id;
		this.name = name;
		this.owner = owner;
		this.isPublic = publicVisibility;
		this.timeZone = timeZone;

		this.calendarView = "year";
		this.dateCreated = LocalDateTime.now();
		this.dateDeleted = null;
		this.events = new ArrayList<Event>();
		this.deletedEvents = new ArrayList<Event>();
		this.usersSharedTo = new ArrayList<User>();
	}

	public void addEvent(Event event) {
		this.events.add(event);
	}

	public void deleteEvent(Event event) {
		this.events.remove(event);
	}

	public void updateEvent(Event event) {
		for (int i = 0; i < this.events.size(); i++) {
			Event curEvent = this.events.get(i);
			if (curEvent.eventId == event.eventId) {
				curEvent.name = event.name;
				curEvent.startDate = event.startDate;
				curEvent.endDate = event.endDate;
			}
		}
	}

	public void updateAllEventTimezones() {
		for (int i = 0; i < this.events.size(); i++) {
			this.events.get(i).startDate.atZone(this.timeZone.toZoneId());
			this.events.get(i).endDate.atZone(this.timeZone.toZoneId());
		}
	}

	public void shareCalendar(User user) {
		user.sharedCalendars.add(this);
		
	}

	public List<Event> viewCalendarEvents() {
		return this.events;
	}

	
	public String toString() {
		StringBuilder eventStringBuilder = new StringBuilder();
		int curEventIndex = 0;
		for (Event event : this.events) {
			eventStringBuilder.append(curEventIndex + ": " + event.name + "\n");
			curEventIndex ++;
		}
		
		String isPublic = this.isPublic ? "public" : "private";
		
		return this.name + "\nCreated: " + this.dateCreated.toLocalDate() + "Owner: " + this.owner.username + "\nPublic: " + isPublic + "\nView: " + this.calendarView + "\nTimeZone: " + this.timeZone.getDisplayName() + "\nEvents:\n" + eventStringBuilder.toString();
	}
}
