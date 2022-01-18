package com.redacted.ctm.model;

import java.time.*;
import java.time.format.*;

/**
 * Main class that represents a track in a schedule
 */
public class Track extends Bin<Session> {
    protected int id;		// identifier

    /**
     * Constructor
     *
     * @param id:  track identifier
     * @param start:  start time for this track
     * @param minLength: minimum duration before the track is
     * considered "filled"
     * @param maxLength: maximum duration after which no more items
     * (Sessions) can be added
     *
     * TODO:  consider removing this constructor
     */
    public Track (String id, LocalTime start, long minLength, long maxLength) {
	super(id, start, minLength, maxLength);}

    /**
     * Constructor
     *
     * @param id:  track identifier
     * @param template: a {@link Track} upon which to base all new
     * added Tracks (via a copy).
     *
     */
    public Track (String id, Track template) {
	this(id, template.getStart(), template.getFillMarker(), template.getCapacity());
	for (Session s : template) {
	    Session snew = new Session(s.getName(),
				       s.getFillMarker(),
				       s.getCapacity(),
				       s.isBlocked());
	    add(snew);
	    for (TimeBlock t : s) {
		TimeBlock tnew = new TimeBlock(t.getName(),
					       t.getCapacity());
		snew.add(tnew);}}
	recalculate();}

    /**
     * Alias "id" to "name"
     *
     * @return String name of this Track
     *
     */
    public String getName () {
	return getId();}

    /**
     * Pretty-print this Track.
     */
    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();	
	sb.append(String.format("Track %s\n", getName()));
	for (Session s : this)
	    for (TimeBlock t : s)
		sb.append(t);
	sb.append("\n");
	return sb.toString();}}

