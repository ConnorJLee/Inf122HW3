import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event implements Comparable<Event> {
	public int eventId;
	public String name;
	public LocalDateTime dateCreated;
	public LocalDateTime dateDeleted;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public List<User> usersSharedTo;
	
	public Event(int id, String name, LocalDateTime start, LocalDateTime end) {
		this.eventId = id;
		this.name = name;
		this.startDate = start;
		this.endDate = end;
		
		this.dateCreated = LocalDateTime.now();
		this.dateDeleted = null;
		this.usersSharedTo = new ArrayList<User>();
	}
	
	public void shareEvent(User user) {
		this.usersSharedTo.add(user);
		user.sharedCalendars.get(0).addEvent(this); //The events not shared in a calendar go into a default shared calendar in the receiving user
	}

	@Override
	public int compareTo(Event o) {
		if (this.startDate.isBefore(o.startDate)) {
			return -1;
		}
		else if (this.startDate.isAfter(o.startDate)) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public String toString() {
		return this.name + "\nCreated on: " + this.dateCreated + "\nStart date: " + this.startDate + "\nEnd date: " + this.endDate;
	}
}
