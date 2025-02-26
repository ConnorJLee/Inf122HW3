import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ConsoleDrawer {
	public static void PrintCalendar(Calendar calendar) {
		String view = calendar.calendarView;
		System.out.println(calendar.name + " events:");
		
		if (view.equals("day")) {
			PrintByDay(calendar.events);
		}
		else if (view.equals("week")) {
			PrintByWeek(calendar.events);
		}
		else if (view.equals("month")) {
			PrintByMonth(calendar.events);
		}
		else if (view.equals("year")) {
			PrintByYear(calendar.events);
		}
		else {
			System.out.println("Invalid view name");
		}
	}
	
	public static void PrintByDay(List<Event> events) {
		if (events.size() < 1) {
			System.out.println("No events");
			return;
		}
		events.sort(null);
		LocalDate currentDay = events.get(0).startDate.toLocalDate();
		System.out.print(currentDay + ": ");
		for (Event event : events) {
			if (event.startDate.toLocalDate().isAfter(currentDay)) {
				currentDay = event.startDate.toLocalDate();
				System.out.print("\n" + currentDay + ": ");
				
			}
			System.out.print(event.name + ", ");
		}
		System.out.println();
	}
	
	public static void PrintByWeek(List<Event> events) {
		if (events.size() < 1) {
			System.out.println("No events");
			return;
		}
		events.sort(null);
		LocalDate currentDay = events.get(0).startDate.toLocalDate();
		System.out.print(currentDay + " to " + currentDay.plusDays(7) + ": ");
		for (Event event : events) {
			if (event.startDate.toLocalDate().isAfter(currentDay.plusDays(7))) {
				currentDay = event.startDate.toLocalDate();
				System.out.print("\n" + currentDay + " to " + currentDay.plusDays(7) + ": ");
				
			}
			System.out.print(event.name + ", ");
		}
		System.out.println();
	}
	
	public static void PrintByMonth(List<Event> events) {
		if (events.size() < 1) {
			System.out.println("No events");
			return;
		}
		events.sort(null);
		Month currentMonth = events.get(0).startDate.toLocalDate().getMonth();
		int currentYear = events.get(0).startDate.toLocalDate().getYear();
		System.out.print(currentMonth + " of " + currentYear + ": ");
		for (Event event : events) {
			if (currentMonth.compareTo(event.startDate.getMonth()) != 0) {
				currentMonth = event.startDate.getMonth();
				currentYear = event.startDate.getYear();
				System.out.print("\n" + currentMonth + " of " + currentYear + ": ");
				
			}
			System.out.print(event.name + ", ");
		}
		System.out.println();
	}
	
	public static void PrintByYear(List<Event> events) {
		if (events.size() < 1) {
			System.out.println("No events");
			return;
		}
		events.sort(null);
		int currentYear = events.get(0).startDate.toLocalDate().getYear();
		System.out.print(currentYear + ":");
		for (Event event : events) {
			if (currentYear < event.startDate.getYear()) {
				currentYear = event.startDate.getYear();
				System.out.print("\n" + currentYear + ":");
				
			}
			System.out.print(event.name + ", ");
		}
		System.out.println();
	}
	
	public static void PrintUserInfo(User user) {
		System.out.println(user);
	}
	
	public static void PrintUserSharedCalendars(User user) {
		StringBuilder calendarStringBuilder = new StringBuilder();
		int curCalendarIndex = 0;
		for (Calendar calendar : user.sharedCalendars) {
			calendarStringBuilder.append(curCalendarIndex + ": " + calendar.name + "\n");
			curCalendarIndex ++;
		}
		
		System.out.println("Shared Calendars:\n" + calendarStringBuilder.toString());
	}
	
	public static void PrintUsers(List<User> users) {
		int curUserIndex = 0;
		for (User user : users) {
			System.out.println(curUserIndex + ": " + user.username);
			curUserIndex ++;
		}
	}
	
	public static void PrintEvents(List<Event> events) {
		int curEventIndex = 0;
		for (Event event : events) {
			System.out.println(curEventIndex + ": " + event.name);
			curEventIndex ++;
		}
	}
	
	public static void PrintEvent(Event event) {
		System.out.println(event.name + "\nCreated on: " + event.dateCreated + "\nStart date: " + event.startDate + "\nEnd date: " + event.endDate);
	}
}
