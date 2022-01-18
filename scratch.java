void print(boolean b) { System.out.print(b); }
void print(char c) { System.out.print(c); }
void print(int i) { System.out.print(i); }
void print(long l) { System.out.print(l); }
void print(float f) { System.out.print(f); }
void print(double d) { System.out.print(d); }
void print(char s[]) { System.out.print(s); }
void print(String s) { System.out.print(s); }
void print(Object obj) { System.out.print(obj); }
void println() { System.out.println(); }
void println(boolean b) { System.out.println(b); }
void println(char c) { System.out.println(c); }
void println(int i) { System.out.println(i); }
void println(long l) { System.out.println(l); }
void println(float f) { System.out.println(f); }
void println(double d) { System.out.println(d); }
void println(char s[]) { System.out.println(s); }
void println(String s) { System.out.println(s); }
void println(Object obj) { System.out.println(obj); }
void printf(java.util.Locale l, String format, Object... args) { System.out.printf(l, format, args); }
void printf(String format, Object... args) { System.out.printf(format, args); }
import com.redacted.ctm.model.*;
import com.redacted.ctm.parsers.*;
import com.redacted.ctm.strategies.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
Track template = new Track("0", LocalTime.parse("09:00", DateTimeFormatter.ISO_LOCAL_TIME), 7*60, 8*60);
template.add(new Session("Morning", 3*60, 3*60));
Session midday = new Session("Midday", 1*60, 1*60);
midday.add(new TimeBlock("Lunch", 60));
template.add(midday);
template.add(new Session("Afternoon", 3*60, 4*60));
Session evening = new Session("Evening", 0, 0);
evening.add(new TimeBlock("Networking Event", 0*60));
template.add(evening);
Scheduler scheduler = new Scheduler(new ProposalParser(), new FirstFit(), new Schedule(template));
scheduler.schedule("Writing Fast Tests Against Enterprise Rails 60min");
scheduler.schedule("Overdoing it in Python 45min");
scheduler.schedule("Lua for the Masses 30min");
scheduler.schedule("Ruby Errors from Mismatched Gem Versions 45min");
scheduler.schedule("Common Ruby Errors 45min");
scheduler.schedule("Rails for Python Developers lightning");
scheduler.schedule("Communicating Over Distance 60min");
scheduler.schedule("Accounting-Driven Development 45min");
scheduler.schedule("Woah 30min");
scheduler.schedule("Sit Down and Write 30min");
scheduler.schedule("Pair Programming vs Noise 45min");
scheduler.schedule("Rails Magic 60min");
scheduler.schedule("Ruby on Rails: Why We Should Move On 60min");
scheduler.schedule("Clojure Ate Scala (on my project) 45min");
scheduler.schedule("Programming in the Boondocks of Seattle 30min");
scheduler.schedule("Ruby vs. Clojure for Back-End Development 30min");
scheduler.schedule("Ruby on Rails Legacy App Maintenance 60min");
scheduler.schedule("A World Without HackerNews 30min");
scheduler.schedule("User Interface CSS in Rails Apps 30min");
scheduler.getSchedule().recalculate();
print(scheduler.getSchedule());
