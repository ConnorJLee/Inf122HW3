import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;
	public Settings settings;
	public String username;
	public List<Calendar> calendars;
	public List<Calendar> sharedCalendars;
	public List<Calendar> deletedCalendars;
	
	public User(int id, String name) {
		this.userId = id;
		this.username = name;
		
		this.calendars = new ArrayList<Calendar>();
		this.sharedCalendars = new ArrayList<Calendar>();
		this.deletedCalendars = new ArrayList<Calendar>();
		this.settings = new Settings();
		
		this.sharedCalendars.add(new Calendar(-1, "Shared Events", this, false, this.settings.currentTimeZone));
	}
	
	public void addCalendar(Calendar calendar) {
		this.calendars.add(calendar);
	}

	public void updateCalendar(Calendar calendar) {
		for (int i = 0; i < this.calendars.size(); i++) {
			Calendar curCalendar = this.calendars.get(i);
			if (curCalendar.calendarId == calendar.calendarId) {
				curCalendar.name = calendar.name;
				curCalendar.isPublic = calendar.isPublic;
				curCalendar.calendarView = calendar.calendarView;
			}
		}
	}
	
	public void deleteCalendar(Calendar calendar) {
		this.calendars.remove(calendar);
	}
	
	public List<Calendar> viewCalendars(){
		return this.calendars;
	}
	
	public String downloadUser() {
		return "Replace with user info";
	}
	
	public void importUserInfo() {
		
	}
	
	public String toString() {
		StringBuilder calendarStringBuilder = new StringBuilder();
		int curCalendarIndex = 0;
		for (Calendar calendar : this.calendars) {
			calendarStringBuilder.append(curCalendarIndex + ": " + calendar.name + "\n");
			curCalendarIndex ++;
		}
		
		return "Name: " + this.username + "\n" + this.settings.toString() + "\nCalendars:\n" + calendarStringBuilder.toString();
	}
}
