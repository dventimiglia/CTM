package com.redacted.ctm.model;

import com.redacted.ctm.parsers.*;

/**
 * The Scheduler class integrates several other "pluggable" components
 * which together orchestrate the scheduling of proposals into
 * bona-fide talks within a schedule.
 */
public class Scheduler {
    /**
     * Domain-specific wrapper exeption.
     *
     * TODO: consider removing as it doesn't add much value (at
     * present)
     */
    public class SchedulingException extends Exception {
	public SchedulingException (Throwable t) {
	    super(t);}}
    
    protected ProposalParser parser;
    protected Strategy strategy;
    protected Schedule schedule;

    /**
     * Construct a new Scheduler, with key components "plugged in."
     *
     * @param parser: a {@link com.redacted.ctm.model.ProposalParser}
     * that can turn a String command (e.g., "Having fun with Java
     * 60m") into a {@link com.redacted.ctm.model.Proposal}
     * @param strategy: a {@link com.redacted.ctm.model.Strategy} that implements a
     * strategy for scheduling a Proposal into a Talk.
     * @param schedule: the {@link com.redacted.ctm.model.Schedule} into which Talks are
     * being scheduled.
     *
     */
    public Scheduler (ProposalParser parser,
		      Strategy strategy,
		      Schedule schedule) {
	this.parser = parser;
	this.strategy = strategy;
	this.schedule = schedule;}

    /**
     * @return the {@link Schedule} that has been built up so far
     */
    public Schedule getSchedule () {
	return schedule;}

    public void schedule (String command) throws SchedulingException {
	try {
	    strategy.schedule(schedule, parser.parse(command));}
	catch (Exception e) {
	    e.printStackTrace();
	    throw new SchedulingException(e);}}}
