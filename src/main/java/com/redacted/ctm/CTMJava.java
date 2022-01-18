package com.redacted.ctm;

import com.redacted.ctm.model.*;
import com.redacted.ctm.parsers.*;
import com.redacted.ctm.strategies.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import static java.time.temporal.ChronoUnit.*;

/**
 * This is the bootstrap/entry-point class that has a main method and
 * runs the application.
 */
public class CTMJava {
    /**
     * Helper method to parse time strings in 24-hour format (HH:MM)
     */
    public static LocalTime parseTime (String string) {
	return LocalTime.parse(string, DateTimeFormatter.ISO_LOCAL_TIME);}

    /**
     * Helper method to get the "usage" screen
     */
    public static String getUsage () {
	StringBuffer sb = new StringBuffer();
	sb.append("Usage:  \n");
	sb.append("  java -cp <classpath> com.redacted.ctm.CTM <input file>...\n");
	sb.append("Environment Variables:\n");
	sb.append("  START - start time for each new track (HH:MM)\n");
	sb.append("  MINEND - earliest end time for each new track (HH:MM)\n");
	sb.append("  MAXEND - latest end time for each new track (HH:MM)\n");
	sb.append("  LUNCHSTART - lunch start time for each new track (HH:MM)\n");
	sb.append("  LUNCHEND - lunch end time for each new track (HH:MM)\n");
	sb.append("  CAPSTONE - name of a capstone event at the end of each track\n");
	return sb.toString();}
    
    /**
     * This is the entry-point main method, whose arguments should be
     * the names of input files to be processed.  Note that
     * "configuration parameters" (when tracks start, the
     * earliest/latest they can end, and what the track "capstone"
     * event is) are injected via named environment variables.
     */
    public static void main (String[] args) {
	if (args.length==0) {
	    System.out.println(getUsage());
	    System.exit(0);}
	try {
	    LocalTime start, minEnd, maxEnd, lunchStart, lunchEnd;
	    String capstone;
	    start = parseTime(System.getenv("START"));
	    minEnd = parseTime(System.getenv("MINEND"));
	    maxEnd = parseTime(System.getenv("MAXEND"));
	    lunchStart = parseTime(System.getenv("LUNCHSTART"));
	    lunchEnd = parseTime(System.getenv("LUNCHEND"));
	    capstone = System.getenv("CAPSTONE");
	    Track template =
		new Track("0",
			  start,
			  start.until(minEnd, MINUTES),
			  start.until(maxEnd, MINUTES));
	    template.add(new Session("Morning",
				     start.until(lunchStart, MINUTES),
				     start.until(lunchStart, MINUTES)));
	    Session midday =
		new Session("Midday",
			    lunchStart.until(lunchEnd, MINUTES),
			    lunchStart.until(lunchEnd, MINUTES));
	    midday.add(new TimeBlock("Lunch",
				     lunchStart
				     .until(lunchEnd, MINUTES)));
	    template.add(midday);
	    template.add(new Session("Afternoon",
				     lunchEnd.until(minEnd, MINUTES),
				     lunchEnd.until(maxEnd, MINUTES)));
	    Session evening = new Session("Evening", 0, 0);
	    evening.add(new TimeBlock(capstone, 0));
	    template.add(evening);
	    for (String s : args) {
		Scheduler scheduler = new Scheduler(new ProposalParser(),
						    new FirstFit(),
						    new Schedule(template));
		for (Scanner in = new Scanner(new File(s)); in.hasNextLine();)
		    scheduler.schedule(in.nextLine());
		scheduler.getSchedule().recalculate(); // jiggle the schedule
		System.out.println(scheduler.getSchedule());}}
	catch (Exception e) {
	    System.out.println(getUsage());
	    System.exit(1);}}}


