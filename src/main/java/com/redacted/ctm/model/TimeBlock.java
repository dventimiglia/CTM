package com.redacted.ctm.model;

import java.time.format.*;

/**
 * The TimeBlock class is a {@link Bin} that represents a block of
 * time that has been scheduled into a {@link Session}, within a
 * {@link Track}, within a {@link Schedule}.  As such, it has been
 * promoted from a {@link Proposal}.
 *
 * TODO:  consider renaming this to "Talk"
 */
public class TimeBlock extends Bin {
    protected String name;	// identifier

    /**
     * Constructor of a generic TimeBlock
     *
     * @param id:  identifer
     * @param length:  duration (minutes)  @see Bin bin
     *
     * TODO:  consider removing, as I'm not sure I use this anywhere
     *
     */
    public TimeBlock (String id, long length) {
	// TODO: passing null is a code-smell, so refactor this
	super(id, null, length, length);}

    /**
     * Constructor of a TimeBlock based off of a {@link Proposal}.
     * This is the way a Proposal is promoted into a talk.
     *
     * @param proposal:  the Proposal to be promoted.
     *
     */
    public TimeBlock (Proposal proposal) {
	this(proposal.getTitle(), proposal.getLength());}

    /**
     * Alias {@link Bin#getId} to getName.
     *
     * @return String name of this TimeBlock
     *
     */
    public String getName () {
	return getId();}

    /**
     * Pretty-print this TimeBlock
     */
    @Override
    public String toString () {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
	StringBuffer sb = new StringBuffer();
	sb.append(getSize()>0 ?
		  String.format("%s %s %smin\n", getStart().format(dtf), getName(), getSize()) :
		  String.format("%s %s\n", getStart().format(dtf), getName()));
	return sb.toString();}}

