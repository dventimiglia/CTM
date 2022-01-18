package com.redacted.ctm.strategies;

import com.redacted.ctm.model.*;

/**
 * An implementation of a {@link Strategy} that determines how a
 * {@link Proposal} will be scheduled into a {@link Schedule} (and
 * promoted into a {@link TimeBlock} in the process).  Remember that a
 * TimeBlock represents a talk.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Bin_packing_problem#First-fit_algorithm">First-fit algorithm</a>
 *
 */
public class FirstFit implements Strategy {
    // might as well start with 1
    protected int id = 1;

    @Override
    public void schedule (Schedule schedule, Proposal proposal) {
	for (Track t : schedule.tracks())
	    for (Session s : t)
		if (s.add(new TimeBlock(proposal)))
		    return;
	schedule.add(new Track(""+id++, schedule.getTemplate()));
	schedule(schedule, proposal);}}
