import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Settings {
	public List<TimeZone> timeZones;
	public List<String> themes;
	public List<String> languages;
	public TimeZone currentTimeZone;
	public String currentTheme;
	public String currentLanguage;
	
	public Settings() {
		String[] zoneIds = TimeZone.getAvailableIDs();
		this.timeZones = new ArrayList<TimeZone>();
		for (String zoneId : zoneIds) {
			this.timeZones.add(TimeZone.getTimeZone(zoneId));
		}
		
		this.themes = Arrays.asList("light", "dark");
		this.languages = Arrays.asList("English", "Spanish");
		
		this.currentTimeZone = TimeZone.getTimeZone("PST");
		this.currentTheme = "light";
		this.currentLanguage = "English";
	}
	
	public String toString() {
		return "Settings:\n TimeZone: " + this.currentTimeZone.getDisplayName() + "\n Language: " + this.currentLanguage + "\n Theme: " + this.currentTheme;
	}
}
