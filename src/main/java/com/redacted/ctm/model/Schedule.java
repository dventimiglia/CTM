package com.redacted.ctm.model;

import java.util.*;

/**
 * Main container class for tracking all the information about the
 * Tracks, Sessions, and Talks that we're trying to schedule.  NOTE:
 * unlike several of the other domain classes, a Schedule is not a Bin
 * (though, I suppose it could be).
 */
public class Schedule {
    protected String id;	// an identifier
    protected List<Track> tracks = new ArrayList<>(); // contained Tracks
    protected Track template;			      // basis for new Tracks

    
    /**
     * One-argument constructor
     *
     * @param template: a Track that is meant to be copied (somehow)
     * whenever a new Track has to be added.
     */
    public Schedule (Track template) {
	this.template = template;}

    /**
     * @return the template Track
     */
    public Track getTemplate () {
	return template;}

    /**
     * Add a Track to this schedule
     *
     * @param track:  the Track to be added
     */
    public boolean add (Track track) {
	return tracks.add(track);}

    /**
     * Iterable over the contained tracks
     */
    public Iterable<Track> tracks () {
	return tracks;}

    /**
     * Iterable over the contained sessions (over all the tracks).
     *
     * TODO: consider removing this, as I'm not sure if I even use it
     * anywhere
     */
    public Iterable<Session> sessions () {
	List<Iterator<Session>> sources = new ArrayList<>();
	for (Track t : tracks())
	    sources.add(t.iterator());
	return new Iterable<Session> () {
	    @Override
	    public Iterator<Session> iterator () {
		return new ChainedIterator<>(sources);}};}

    /**
     * Recalculate the whole schedule, by delegating recalculating
     * operations to all the contained tracks.  Since a Track is a
     * Bin, See @com.redacted.ctm.model.Bin#recalculate to
     * understand what it means to do a recalculation.
     */
    public void recalculate () {
	for (Track t : tracks)
	    t.recalculate();}

    /**
     * Pretty-print this schedule
     */
    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();
	for (Track t : tracks) sb.append(t.toString());
	return sb.toString();}}
	    
