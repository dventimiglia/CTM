package com.redacted.ctm.model;

import java.time.*;
import java.util.*;

/**
 * The Bin class is meant to be a basic data structure for
 * "bin-packing" algorithms.  It's like a "bag" though technically its
 * elements are ordered.  Its main characteristics are that it's
 * nestable (you can put Bins inside of Bins), and that when a new
 * item is added it's placed "adjacent" to the most recently added
 * item.  "Adjacent" means that the added item has its start time
 * (these are "temporal" bins) is adjusted to be the end time of the
 * previously added item.  There's also a recalculate method to reset
 * all the start times accordingly.  Another property that a bin has
 * "capacity", which is like its "size."  As bins are added to a bin,
 * it "fills up."  If adding an item would calls the total size of all
 * the items to exceed the capacity of the containing bin, then the
 * add operation does not occur and instead returns a boolean false.
 * A Bin also has a fillmarker and an isFilled method.  The fillmarker
 * may be less than the capacity.  This is to indicate a minimum
 * threshold after which a Bin is considered "filled", even if it has
 * some room left and could still have more items added to it.  The
 * fillmarker, capacity, and bin high water mark together influence a
 * computation of the bin's size, in the following way.  First, a
 * partially-filled or unfilled or even empty bin still has a minimum
 * finite wize, which is determined by the fillmarker.  Once a bin has
 * been filled past its fillmarker, however, its size is its current
 * high water mark, which is the sum of all the contained items.
 * However, a bin's capacity puts a hard limit on a bin's maximum
 * size, since the high water mark can't go past the capacity.  Beyond
 * that, it's up to clients of Bin to decide how they want to use the
 * fact that a bin is filled.  Finally, a Bin is also an Iterable over
 * the items (other Bins) that it contains.
 */
public class Bin<B extends Bin> implements Iterable<B> {
    public String id;		// bin identifier
    public long fillmarker, capacity, highwater; // various size-related data
    public LocalTime start;			 // start time of this bin
    public List<B> bins = new ArrayList<>();	 // contained Bins
    public boolean blocked;			 // TODO:  remove this vestigial code

    /**
     * Bin constructor
     *
     * @param id:  identifier
     * @param start: start time
     * @param fillmarker: duration (minutes) when isFilled retures
     * true
     * @param capacity: duration (minutes) when Bin no longer accepts
     * new items
     *
     */
    public Bin (String id, LocalTime start, long fillmarker, long capacity) {
	this.id = id;
	this.start = start;
	this.fillmarker = fillmarker;
	this.capacity = capacity;}

    /**
     * Bin constructor
     *
     * @param id:  identifier
     * @param start: start time
     * @param fillmarker: duration (minutes) when isFilled retures
     * true
     * @param capacity: duration (minutes) when Bin no longer accepts
     * new items
     * @param blocked: boolean indicates that no items can be added
     * (TODO: remove vestigial code)
     *
     */
    public Bin (String id, LocalTime start, long fillmarker, long capacity, boolean blocked) {
	this(id, start, fillmarker, capacity);
	this.blocked = blocked;}

    /**
     * @return bin identifier
     */
    public String getId () {
	return id;}

    /**
     * Add a new Bin to this bin, if possible.
     *
     * @return boolean indicating if the add operation occurred.
     */
    public boolean add (B b) {
	if (blocked) return false;
	b.recalculate();
	if (getHighWater() + b.getSize() > capacity) return false;
	if (bins.add(b)) {
	    b.setStart(start.plusMinutes(getHighWater()));
	    highwater+=b.getSize();
	    return true;}
	return false;}

    /**
     * @return boolean indicating if enough items have been added for
     * this bin to be considered "filled"
     */
    public boolean isFilled () {
	return highwater > fillmarker;}

    /**
     * Mutating method that recalculates the start times of all the
     * items (bins) contained within this bin, so that they're all
     * "adjacent."  "Adjacent" means that in the sequence of contained
     * items (they have an order), the start time of bin i is equal to
     * the start time of bin i-1 plus the duration of bin i-1.  Note
     * that the order of bins is not changed, just their start times.
     * Also this bin's current "high water" mark is also updated
     * (cached).
     */
    public void recalculate () {
	long hw = 0;
	for (B b : bins) {
	    b.setStart(start.plusMinutes(hw));
	    b.recalculate();
	    hw+=b.getSize();}
	highwater = hw;}

    /**
     * Set this bin's start time.
     *
     * @param start:  the new start time
     *
     */
    public void setStart (LocalTime start) {
	this.start = start;}

    /**
     * @return the bin start time
     */
    public LocalTime getStart () {
	return start;}

    /**
     * @return the bin end time, which is the start time plus the
     * bin's "size" (i.e., capacity).
     *
     */
    public LocalTime getEnd () {
	return start.plusMinutes(fillmarker);}

    /**
     * @return the bin "high water mark" which is the duration (in
     * minutes) from the start time (whatever that is) over which all
     * of the contained item bins occur.
     */
    public long getHighWater () {
	return highwater;}

    /**
     * @return the bin fill marker (described above)
     */
    public long getFillMarker () {
	return fillmarker;}

    /**
     * @return the bin capacity (described above)
     */
    public long getCapacity () {
	return capacity;}

    /**
     * @return the bin size, which is the larger of the fill marker or
     * the capacity (the reasoning for this is described above).
     */
    public long getSize () {
	return Math.max(getFillMarker(), getHighWater());}

    /**
     * Set whether this bin can or cannot have items added to
     * it. (TODO: remove vestigial code)
     */
    public void setBlocked (boolean blocked) {
	this.blocked = blocked;}

    /**
     * @return boolean indicating if this bin is blocked (TODO:
     * remove)
     */
    public boolean isBlocked () {
	return blocked;}

    /**
     * Iterator over the contained item bins
     */
    public Iterator<B> iterator () {
	return bins.iterator();}

    /**
     * Pretty-print the Bin state, mostly for debugging purposes.
     * (TODO: consider removing)
     */
    @Override
    public String toString () {
    	StringBuffer sb = new StringBuffer();
	sb.append(String.format("\t%s->%s: %s (%s%s):\n",
				start,
				getEnd(),
				getId(),
				isBlocked() ? "blocked " : "",
				getClass().getSimpleName()));
	for (Bin b : this)
	    sb.append(b.toString().replace("\t", "\t\t"));
    	return sb.toString();}}
